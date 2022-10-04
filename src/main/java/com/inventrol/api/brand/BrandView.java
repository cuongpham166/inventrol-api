package com.inventrol.api.brand;

import java.time.LocalDate;

public interface BrandView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
}
