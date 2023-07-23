package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.validator.WhitelistedValue;
import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {
	@NotNull
	@Pattern(regexp="[0-9]{13}")
	private String isbn;
	@NotNull
	private String title;
	@Min(1600)
	@Max(2100)
	private Integer publishYear;
	@Min(0)
	private BigDecimal price;
	@NotNull
	@WhitelistedValue(enumVal=Genre.class)
	private String genre;
	private List<AuthorDTO> authors;
	public BookDTO() {}
	public BookDTO(Book book) {
		this.isbn = book.getIsbn();
		if (book.getGenre() != null) {
			this.genre = book.getGenre().toString();
		}
		this.title = book.getTitle();
		this.price = book.getPrice();
		this.publishYear = book.getPublishYear();
		if (book.getAuthors() != null) {
			this.authors = book.getAuthors().stream().map(x -> new AuthorDTO(x)).collect(Collectors.toList());
		}
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(Integer publishYear) {
		this.publishYear = publishYear;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public List<AuthorDTO> getAuthors() {
		return authors;
	}
	public void setAuthors(List<AuthorDTO> authors) {
		this.authors = authors;
	}
	@Override
	public String toString() {
		return "BookDTO [isbn=" + isbn + ", title=" + title + ", publishYear=" + publishYear + ", price=" + price
				+ ", genre=" + genre + ", authors=" + authors + "]";
	}
}
