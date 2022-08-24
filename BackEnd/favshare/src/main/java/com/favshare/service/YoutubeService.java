package com.favshare.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.sound.midi.Soundbank;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.FeedDto;
import com.favshare.dto.PopDto;
import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeInfoDto;
import com.favshare.dto.YoutubeUserIdDto;
import com.favshare.entity.InterestIdolEntity;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;
import com.favshare.repository.IdolRepository;
import com.favshare.repository.InterestIdolRepository;
import com.favshare.repository.StoreYoutubeRepository;
import com.favshare.repository.UserRepository;
import com.favshare.repository.YoutubeRepository;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

@Service
public class YoutubeService {

	@Autowired
	YoutubeRepository youtubeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	InterestIdolRepository interestIdolRepository;

	@Autowired
	IdolRepository idolRepository;

	@Autowired
	StoreYoutubeRepository storeYoutubeRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final String API_KEY = "AIzaSyAjABtMPYPbfd8-_MCPfbRpxFHWfsnpRjw";

	/** Global instance properties filename. */
	private static String PROPERTIES_FILENAME = "youtube.properties";

	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	/**
	 * Global instance of the max number of videos we want returned (50 = upper
	 * limit per page).
	 */
	private static final long NUMBER_OF_VIDEOS_RETURNED = 50;

	private static YouTube youtube;

	public boolean hasInterestIdol(int userId) {
		List<InterestIdolEntity> interestIdolEntityList;
		interestIdolEntityList = interestIdolRepository.findAllByUserId(userId);

		if (interestIdolEntityList.size() == 0)
			return false;
		else
			return true;

	}

	public YoutubeDetailDto getDetailByUrl(YoutubeUserIdDto youtubeUSerIdDto) {
		YoutubeEntity youtubeEntity;
		youtubeEntity = youtubeRepository.findByUrl(youtubeUSerIdDto.getYoutubeUrl());

		List<PopDto> popList;
		YoutubeDetailDto result;
		int youtubeId = youtubeRepository.findByUrl(youtubeUSerIdDto.getYoutubeUrl()).getId();
		boolean isBookmarked;

		if (storeYoutubeRepository.isBookmarked(youtubeUSerIdDto.getUserId(), youtubeId) == 1) {
			isBookmarked = true;
		} else {
			isBookmarked = false;
		}

		result = new YoutubeDetailDto(youtubeUSerIdDto.getYoutubeUrl(), isBookmarked);

		if (youtubeEntity == null) {
			popList = null;
		} else {
			popList = Arrays.asList(modelMapper.map(youtubeEntity.getPopList(), PopDto[].class));
		}

		result.setPopList(popList);
		return result;
	}

	public String getAlgoUrlByUserId(int userId) {
		List<InterestIdolEntity> interestIdolEntityList;
		interestIdolEntityList = interestIdolRepository.findAllByUserId(userId);

		List<String> urlList = new ArrayList<String>();
		String queryList = "";

		// 검색어 쿼리 만드는 반복문
		for (int i = 0; i < interestIdolEntityList.size(); i++) {
			queryList += idolRepository.findById(interestIdolEntityList.get(i).getIdolEntity().getId()).get().getName();
			if (i < interestIdolEntityList.size() - 1)
				queryList += "or";
		}

		return queryList;

	}

	// 로그인 하지 않은 사용자 or 선호하는 아이돌이 없는 경우에 사용되는 api
	public String getAlgoUrlByNoId() {

		List<String> urlList = new ArrayList<String>();

		Random r = new Random();
		int[] idList = new int[5];
		String queryList = "";
		int size = idolRepository.findAll().size();

		// 랜덤으로 아이돌 추출해서 검색어 쿼리 만드는 반복문
		for (int i = 0; i < idList.length; i++) {
			idList[i] = r.nextInt(size) + 1;
			for (int j = 0; j < i; j++) {
				if (idList[i] == idList[j])
					i--;
			}
		}

		for (int i = 0; i < idList.length; i++) {
			queryList += idolRepository.findById(idList[i]).get().getName();
			if (i < idList.length - 1)
				queryList += "or";
		}
		return queryList;

	}

	private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, List<String> urlList) {

		if (!iteratorSearchResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}

		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();

			if (rId.getKind().equals("youtube#video")) {

				urlList.add(rId.getVideoId());

			}
		}

	}

	public YoutubeInfoDto getEditInfoByUrl(YoutubeUserIdDto youtubeUserIdDto) {
		UserEntity userEntity = userRepository.findById(youtubeUserIdDto.getUserId()).get();
		YoutubeEntity youtubeEntity = youtubeRepository.findByUrl(youtubeUserIdDto.getYoutubeUrl());

		YoutubeInfoDto result = new YoutubeInfoDto(userEntity, youtubeUserIdDto.getYoutubeUrl());
		List<FeedDto> feedList = Arrays.asList(modelMapper.map(userEntity.getFeedList(), FeedDto[].class));

		result.setFeedList(feedList);
		return result;
	}

	public List<String> getUrlByKeyword(String keyword) {
		List<String> urlList = new ArrayList<String>();

		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				@Override
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("youtube-cmdline-search-sample").build();

			SearchListResponse searchResponse = null;

			YouTube.Search.List search = youtube.search().list("id,snippet");

			search.setKey(API_KEY);
			search.setType("video");
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
			search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			search.setQ(keyword);

			searchResponse = search.execute();

			List<SearchResult> searchResultList = searchResponse.getItems();
			if (searchResultList != null) {
				prettyPrint(searchResultList.iterator(), urlList);
			}

		} catch (GoogleJsonResponseException e) {
			System.err.println(
					"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}

		Collections.shuffle(urlList);

		return urlList;
	}

}
