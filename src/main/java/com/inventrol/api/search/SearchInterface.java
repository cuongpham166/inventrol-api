package com.inventrol.api.search;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.Results;

public interface SearchInterface {
	public Results<Index> getAllIndexes ();
}
