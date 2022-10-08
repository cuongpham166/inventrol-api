package com.inventrol.api.atrribute;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.attributevalue.AttributeValue;

@Entity
@Table(name = "attribute")
public class Attribute {
	public Attribute(Set<AttributeValue> attributevalue, String name, String notice, String tagColor, boolean deleted,
			LocalDate createdDate, LocalDate updatedDate) {
		super();
		this.attributevalue = attributevalue;
		this.name = name;
		this.notice = notice;
		this.tagColor = tagColor;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Attribute() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy ="attribute",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<AttributeValue>attributevalue = new HashSet<AttributeValue>();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name="tag_color")
	private String tagColor;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;

	public long getId() {
		return id;
	}

	public Set<AttributeValue> getAttributevalue() {
		return attributevalue;
	}

	public void setAttributevalue(Set<AttributeValue> attributevalue) {
		this.attributevalue = attributevalue;
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

	public String getTagColor() {
		return tagColor;
	}

	public void setTagColor(String tagColor) {
		this.tagColor = tagColor;
	}
}
