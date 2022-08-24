package com.favshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.entity.IdolEntity;
import com.favshare.entity.SongEntity;
import com.favshare.repository.IdolRepository;

@Service
public class IdolService {
	@Autowired
	private IdolRepository idolRepository;

	public IdolEntity getIdolByName(String name) {
		return idolRepository.findByName(name);
	}

	public List<IdolEntity> getIdolContains(String name) {
		return idolRepository.findByKeywordContains(name);
	}

	public List<IdolEntity> getIdolList() {
		return idolRepository.findAll();
	}

}
