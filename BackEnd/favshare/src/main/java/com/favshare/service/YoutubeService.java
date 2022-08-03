package com.favshare.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
	private ModelMapper modelMapper;

	private static final String API_KEY = "AIzaSyCj3WWR7IRe4nXKGzX5QppcRImaewZuqlk";

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

	public YoutubeDetailDto getDetailById(int id) {
		YoutubeEntity youtubeEntity;
		youtubeEntity = youtubeRepository.findById(id).get();

		YoutubeDetailDto result = new YoutubeDetailDto(youtubeEntity);
		List<PopDto> popList = Arrays.asList(modelMapper.map(youtubeEntity.getPopList(), PopDto[].class));
		result.setPopList(popList);
		return result;
	}

	public List<String> getAlgoUrlByUserId(int userId) {
		List<InterestIdolEntity> interestIdolEntityList;
		interestIdolEntityList = interestIdolRepository.findAllByUserId(userId);

		List<String> urlList = new ArrayList<String>();
		for (int i = 0; i < interestIdolEntityList.size(); i++) {
			String idolName = idolRepository.findById(interestIdolEntityList.get(i).getIdolEntity().getId()).get()
					.getName();
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
				search.setQ(idolName);

				searchResponse = search.execute();

				List<SearchResult> searchResultList = searchResponse.getItems();
				if (searchResultList != null) {
//					System.out.println(searchResultList);
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
		}

		Collections.shuffle(urlList);

		return urlList;
	}

	private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, List<String> urlList) {

//		System.out.println("\n=============================================================");
//		System.out.println("   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
//		System.out.println("=============================================================\n");

		if (!iteratorSearchResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}

		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();

			// Double checks the kind is video.
			if (rId.getKind().equals("youtube#video")) {
//				Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

				urlList.add(rId.getVideoId());
//				System.out.println(" Video Id : " + rId.getVideoId());
//				System.out.println(" Title : " + singleVideo.getSnippet().getTitle());
//				System.out.println(" Thumbnail : " + thumbnail.getUrl());
//				System.out.println("\n-------------------------------------------------------------\n");
			}
		}

	}

	public YoutubeInfoDto getEditInfoById(YoutubeUserIdDto youtubeUserIdDto) {
		UserEntity userEntity = userRepository.findById(youtubeUserIdDto.getUserId()).get();
		YoutubeEntity youtubeEntity = youtubeRepository.findById(youtubeUserIdDto.getYoutubeId()).get();

		YoutubeInfoDto result = new YoutubeInfoDto(userEntity, youtubeEntity);
		List<FeedDto> feedList = Arrays.asList(modelMapper.map(userEntity.getFeedList(), FeedDto[].class));
		result.setFeedList(feedList);
		return result;
	}

}
