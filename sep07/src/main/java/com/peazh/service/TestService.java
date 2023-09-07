package com.peazh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peazh.entity.Test;
import com.peazh.repository.TestRepository;

@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepository;

	public List<Test> list() {
		return testRepository.findAll();
	}

}
