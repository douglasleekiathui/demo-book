package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.dto.validator.AtLeastOne;
import com.example.demo.model.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@AtLeastOne
public class AuthorDTO {
	private Long id;
	private String name;
	private LocalDate dob;
	public AuthorDTO() {
		super();
	}
	public AuthorDTO(Author author) {
		super();
		this.id = author.getId();
		this.name = author.getName();
		this.dob = author.getDob();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "AuthorDTO [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
}
