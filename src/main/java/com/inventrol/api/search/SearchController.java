package com.inventrol.api.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.Results;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@GetMapping("/search/index/all")
	public ResponseEntity<Results<Index>>getAllSearchIndexes(){
		try {
			Results<Index> result = searchService.getAllIndexes();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.printf(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	




}
