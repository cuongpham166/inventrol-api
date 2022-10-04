package com.inventrol.api.retailpricerecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailPriceRecordRepository extends JpaRepository <RetailPriceRecord,Long>{

}
