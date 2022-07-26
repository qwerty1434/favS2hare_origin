package com.favshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.repository.PopRepository;

@Service
public class PopService {

	@Autowired
	private PopRepository popRepository;
	
	public int getPopCount(int userId) {
		int result = popRepository.countPopByUserId(userId);
		return result;
	} 
}
