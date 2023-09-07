package com.peazh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.peazh.entity.Board;
import com.peazh.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public List<Board> list() {
		return boardRepository.findAll();
	}

	public void save(Board board) {
		boardRepository.save(board);
	}
	
	public Optional<Board> findById(String id) {
		return boardRepository.findById(id);
	}

	public Page<Board> findAll(PageRequest of) {
		return boardRepository.findAll(of);
	}

	public void deleteById(String id) {
		boardRepository.deleteById(id);
	}

	public int count() {
		return (int) boardRepository.count();
	}
}
