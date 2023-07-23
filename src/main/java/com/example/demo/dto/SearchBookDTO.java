package com.example.demo.dto;

import com.example.demo.dto.validator.AtLeastOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@AtLeastOne
public class SearchBookDTO {
	private String title;
	private String name;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SearchBookDTO [title=" + title + ", name=" + name + "]";
	}
}
