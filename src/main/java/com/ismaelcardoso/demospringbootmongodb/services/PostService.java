package com.ismaelcardoso.demospringbootmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaelcardoso.demospringbootmongodb.domain.Post;
import com.ismaelcardoso.demospringbootmongodb.repository.PostRepository;
import com.ismaelcardoso.demospringbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text) {
//		return repo.findByTitleContainingIgnoreCase(text);
		return repo.searchTitle(text);
	}
	
	public List<Post> findTitleByRangeDate(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.findTitleByRangeDate(text, minDate, maxDate);
	}
}
