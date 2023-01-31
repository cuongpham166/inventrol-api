package com.inventrol.api.search;

import org.springframework.stereotype.Service;
import com.meilisearch.sdk.*;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.model.IndexesQuery;
import com.meilisearch.sdk.model.Results;

@Service
public class SearchService implements SearchInterface {	
	public Results<Index> getAllIndexes () {
		 Client client = new Client(new Config("http://localhost:7700", "masterKey"));
		 IndexesQuery query = new IndexesQuery();
		 Results<Index> result = new Results<Index>();
		 try {
			
			result = client.getIndexes(query);
		 } catch (MeilisearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 return result;
	}
	
	
}
