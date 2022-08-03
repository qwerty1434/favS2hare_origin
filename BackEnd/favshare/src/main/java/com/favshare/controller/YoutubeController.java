package com.favshare.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.service.YoutubeService;
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
import com.google.api.services.youtube.model.Thumbnail;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

	@Autowired
	private YoutubeService youtubeService;

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

	@ApiOperation(value = "사용자에게 맞는 유튜브 리스트", response = List.class)
	@GetMapping("/{userId}")
	public ResponseEntity<List<YoutubeDto>> showYoutubeList(@PathVariable("userId") int userId) {

		String queryTerm = "슈카";
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
			search.setQ(queryTerm);
			searchResponse = search.execute();
//			System.out.println(temp.get(j).getmUrl());
			List<SearchResult> searchResultList = searchResponse.getItems();
			if (searchResultList != null) {
				System.out.println(searchResultList);
				prettyPrint(searchResultList.iterator(), queryTerm);

			}

		} catch (GoogleJsonResponseException e) {
			// TODO: handle exception
			System.out.println("service err");
		} catch (Exception e) {
			
		}

		// 알고리즘을 통해 유튜브 리스트 반환
		return null;
	}

	@ApiOperation(value = "유튜브 관련 정보", response = YoutubeDetailDto.class)
	@GetMapping("/detail/{youtubeId}")
	public ResponseEntity<YoutubeDetailDto> showYoutubeDetil(@PathVariable("youtubeId") int youtubeId) {
		try {
			YoutubeDetailDto youtubeDetailDto = youtubeService.getDetailById(youtubeId);
			return new ResponseEntity<YoutubeDetailDto>(youtubeDetailDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<YoutubeDetailDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

	    System.out.println("\n=============================================================");
	    System.out.println(
	        "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
	    System.out.println("=============================================================\n");

	    if (!iteratorSearchResults.hasNext()) {
	      System.out.println(" There aren't any results for your query.");
	    }

	    while (iteratorSearchResults.hasNext()) {

	      SearchResult singleVideo = iteratorSearchResults.next();
	      ResourceId rId = singleVideo.getId();

	      // Double checks the kind is video.
	      if (rId.getKind().equals("youtube#video")) {
	        Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

	        System.out.println(" Video Id : " + rId.getVideoId());
	        System.out.println(" Title : " + singleVideo.getSnippet().getTitle());
	        System.out.println(" Thumbnail : " + thumbnail.getUrl());
	        System.out.println("\n-------------------------------------------------------------\n");
	      }
	    }
	  }

}
