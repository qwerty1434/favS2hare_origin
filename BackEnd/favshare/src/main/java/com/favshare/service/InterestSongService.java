package com.favshare.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.IdolDto;
import com.favshare.dto.InterestSongDto;
import com.favshare.dto.SongDto;
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

	@Autowired
	private ModelMapper modelMapper;

	public void addSongFavorite(int userId, int songId) {
		SongEntity songEntity = songRepository.findById(songId).get();
		UserEntity userEntity = userRepository.findById(userId).get();

		int duplicate = interestSongRepository.findBySongIdUserId(userEntity.getId(), songEntity.getId());
		if (duplicate >= 1) {
		} else {
			InterestSongEntity result = new InterestSongEntity(songEntity, userEntity);
			interestSongRepository.save(result);
		}

	}

	public List<Integer> findSongListById(int userId) {
		List<InterestSongEntity> songEntityList = interestSongRepository.findAllByUserId(userId);
		List<Integer> songList = new ArrayList<Integer>();
		for (int i = 0; i < songEntityList.size(); i++) {
			songList.add(songEntityList.get(i).getSongEntity().getId());

		}
		return songList;
	}

	public void deleteByUserId(int userId) {
		interestSongRepository.deleteByUserId(userId);
	}
}
