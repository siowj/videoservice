package com.ncs.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.validator.constraints.URL;

@Table(name="VIDEOS")
@Entity
public class Video {
	
	@Id
	@GeneratedValue
	@Column(name="VIDEOID")
	private Integer videoId;
	
//	@NotNull (message = "Video title cannot be null!")
//	@NotEmpty (message = "Video title cannot be empty!")
//	@Size( max = 80, message = "Description must be not more than 80 characters")
	@Column(name="VIDEOTITLE")
	private String title;
	
	@URL(regexp = "^(http|ftp).*")
	@Column(name="VIDEOLINK")
	private String link;
	
	
	@Column(name="DESCRIPTION")
	private String description;
	
	
	//private String tags[];
	@Column(name="TAGS")
	@Convert(converter = ListToStringConverter.class)
	private List<String> tags;

	
	
	@Column(name="CREATEDDATE")
	private LocalDate createdDate;
	
	@Column(name="UPDATEDDATE")
	private LocalDate updatedDate;

	

	

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public String[] getTags() {
//		return tags;
//	}
//
//	public void setTags(String[] tags) {
//		this.tags = tags;
//	}
	
	

	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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
	
	

	
	









//	public Video(Integer id,
//			@NotNull(message = "Video title cannot be null!") @NotEmpty(message = "Video title cannot be empty!") @Size(max = 80, message = "Description must be not more than 80 characters") String title,
//			@URL(protocol = "http") String link,
//			@Size(min = 5, max = 250, message = "Description must be between 5-250 characters") String description,
//			String[] tags, @Past LocalDate createdDate, LocalDate updatedDate) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.link = link;
//		this.description = description;
//		this.tags = tags;
//		this.createdDate = createdDate;
//		this.updatedDate = updatedDate;
//	}

	public Video() {
		super();
		
	}

public Video(Integer videoId, String title, @URL(regexp = "^(http|ftp).*") String link, String description,
		List<String> tags, LocalDate createdDate, LocalDate updatedDate) {
	super();
	this.videoId = videoId;
	this.title = title;
	this.link = link;
	this.description = description;
	this.tags = tags;
	this.createdDate = createdDate;
	this.updatedDate = updatedDate;
}

@Override
public String toString() {
	return "Video [videoId=" + videoId + ", title=" + title + ", link=" + link + ", description=" + description
			+ ", tags=" + tags + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
}
	
	





	

	
	

}
