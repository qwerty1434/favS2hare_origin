package com.favshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.entity.SongEntity;
import com.favshare.repository.SongRepository;

@Service
public class SongService {
	@Autowired
	private SongRepository songRepository;

	public List<SongEntity> getSongList() {
		return songRepository.findAll();

	}

}
