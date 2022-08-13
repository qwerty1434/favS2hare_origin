package com.favshare.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.favshare.entity.IdolEntity;
import com.favshare.entity.InterestIdolEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.IdolRepository;
import com.favshare.repository.InterestIdolRepository;
import com.favshare.repository.UserRepository;

@Service
public class InterestIdolService {
	@Autowired
	private InterestIdolRepository interestIdolRepository;

	@Autowired
	private IdolRepository idolRepository;

	@Autowired
	private UserRepository userRepository;

	public void addIdolFavorite(int userId, int idolId) {
		IdolEntity idolEntity = idolRepository.findById(idolId).orElseThrow(() -> new NullPointerException());
		UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NullPointerException());
		interestIdolRepository.save(new InterestIdolEntity(idolEntity, userEntity));
	}

}
