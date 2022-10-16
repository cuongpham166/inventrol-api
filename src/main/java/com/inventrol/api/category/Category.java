package com.inventrol.api.category;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.subcategory.Subcategory;

@Entity
@Table(name = "category")
public class Category {
	public Category(Set<Subcategory> subcategory, String name, String notice, boolean deleted, String tagColor,
			LocalDate createdDate, LocalDate updatedDate) {
		super();
		this.subcategory = subcategory;
		this.name = name;
		this.notice = notice;
		this.deleted = deleted;
		this.tagColor = tagColor;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Category() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy ="category",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Subcategory>subcategory = new HashSet<Subcategory>();
	
	@Column(name = "name",unique=true)
	private String name;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;
	
	@Column(name="tag_color")
	private String tagColor;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<Subcategory> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Set<Subcategory> subcategory) {
		this.subcategory = subcategory;
	}

	public String getTagColor() {
		return tagColor;
	}

	public void setTagColor(String tagColor) {
		this.tagColor = tagColor;
	}


	
}
