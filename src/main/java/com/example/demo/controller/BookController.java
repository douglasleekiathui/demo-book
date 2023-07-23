package com.example.demo.controller;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.DeleteBookDTO;
import com.example.demo.dto.SearchBookDTO;
import com.example.demo.error.DemoApplicationException;
import com.example.demo.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@PostMapping("add")
	public BookDTO addBook(@Valid @RequestBody BookDTO bookDTO) throws DemoApplicationException {
		return bookService.add(bookDTO);
	}
	
	@PostMapping("update")
	public BookDTO updateBook(@Valid @RequestBody BookDTO bookDTO) throws DemoApplicationException {
		return bookService.update(bookDTO);
	}
	
	@PostMapping("find")
	public List<BookDTO> findBook(@Valid @RequestBody SearchBookDTO searchBookDTO) {
		return bookService.find(searchBookDTO);
	}
	
	@PostMapping("delete")
	@Secured("ROLE_DELETER")
	public void deleteBook(@Valid @RequestBody DeleteBookDTO deleteBookDTO) throws DemoApplicationException {
		bookService.delete(deleteBookDTO.getIsbn());
	}
}
