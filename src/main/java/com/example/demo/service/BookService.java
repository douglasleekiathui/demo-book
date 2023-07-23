package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.BookDTO;
import com.example.demo.dto.SearchBookDTO;
import com.example.demo.error.DemoApplicationException;
import com.example.demo.error.ErrorConstants;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

import jakarta.persistence.criteria.Predicate;

@Service
@Transactional
public class BookService {
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
		super();
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	public BookDTO add(BookDTO bookDTO) throws DemoApplicationException {
		Optional<Book> optional = bookRepository.findById(bookDTO.getIsbn());
		if (optional.isPresent()) {
			throw new DemoApplicationException(ErrorConstants.BOOK_ALREADY_EXISTS);
		}
		return new BookDTO(bookRepository.save(toEntity(bookDTO)));
	}

	public BookDTO update(BookDTO bookDTO) throws DemoApplicationException {
		Optional<Book> optional = bookRepository.findById(bookDTO.getIsbn());
		if (optional.isPresent()) {
			return new BookDTO(bookRepository.save(toEntity(bookDTO)));
		} else {
			throw new DemoApplicationException(ErrorConstants.BOOK_NOT_FOUND);
		}
	}
	
	public Book toEntity(BookDTO dto) throws DemoApplicationException {
		Book book = new Book();
		if (StringUtils.hasText(dto.getGenre())) {
			book.setGenre(Genre.valueOf(dto.getGenre()));
		}
		book.setIsbn(dto.getIsbn());
		book.setPrice(dto.getPrice());
		book.setPublishYear(dto.getPublishYear());
		book.setTitle(dto.getTitle());
		if (dto.getAuthors() != null & !dto.getAuthors().isEmpty()) {
			book.setAuthors(new ArrayList<>());
			for (AuthorDTO a: dto.getAuthors()) {
				Author author = null;
				if (a.getId() != null) {
					Optional<Author> optional = authorRepository.findById(a.getId());
					if (optional.isPresent()) {
						author = optional.get();
					} else {
						throw new DemoApplicationException(ErrorConstants.AUTHOR_NOT_FOUND);
					}
				} else {
					Optional<Author> optional = authorRepository.findByNameAndDob(a.getName(), a.getDob());
					if (optional.isPresent()) {
						author = optional.get();
					} else {
						author = new Author();
						author.setName(a.getName());
						author.setDob(a.getDob());
					}
				}
				book.getAuthors().add(author);
			}
		}
		return book;
	}

	@Transactional(readOnly=true)
	public List<BookDTO> find(SearchBookDTO searchBookDTO) {
		return bookRepository.findAll(buildSpecification(searchBookDTO)).stream().map(x -> {
			return new BookDTO(x);
		}).collect(Collectors.toList());
	}
	
	private Specification<Book> buildSpecification(SearchBookDTO searchBookDTO){
		  return (root, query, criteriaBuilder) 
		      -> {
		    	  List<Predicate> predicates = new ArrayList<>();
		    	  if (StringUtils.hasText(searchBookDTO.getTitle())) {
		    		  predicates.add(criteriaBuilder.equal(root.get("title"), searchBookDTO.getTitle()));  
		    	  }
		    	  if (StringUtils.hasText(searchBookDTO.getName())) {
		    		  predicates.add(criteriaBuilder.equal(root.get("authors").get("name"), searchBookDTO.getName()));
		    	  }
		    	  return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		      };
		}

	public void delete(String isbn) throws DemoApplicationException {
		Optional<Book> optional = bookRepository.findById(isbn);
		if (optional.isPresent()) {
			Book book = optional.get();
			book.setAuthors(null);
			bookRepository.delete(book);
		} else {
			throw new DemoApplicationException(ErrorConstants.BOOK_NOT_FOUND);
		}
	}
}
