package com.favshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.entity.InterestSongEntity;
import com.favshare.entity.SongEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.InterestSongRepository;
import com.favshare.repository.SongRepository;
import com.favshare.repository.UserRepository;

@Service
public class InterestSongService {
	@Autowired
	private InterestSongRepository interestSongRepository;
	
	@Autowired
	private SongRepository songRepository;	
	
	@Autowired
	private UserRepository userRepository;	
	
	public void addSongFavorite(int userId, int songId) {
		SongEntity songEntity = songRepository.findById(songId).get();
		UserEntity userEntity = userRepository.findById(userId).get();
		interestSongRepository.save(new InterestSongEntity(songEntity, userEntity));
	}
}
