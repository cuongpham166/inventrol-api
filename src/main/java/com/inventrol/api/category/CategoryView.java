package com.inventrol.api.category;

import java.time.LocalDate;

public interface CategoryView {
	long getId();
	String getName();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	 boolean isDeleted();
}
