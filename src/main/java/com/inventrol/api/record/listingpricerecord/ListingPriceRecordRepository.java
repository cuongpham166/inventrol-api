package com.inventrol.api.record.listingpricerecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingPriceRecordRepository  extends JpaRepository <ListingPriceRecord,Long>{

}