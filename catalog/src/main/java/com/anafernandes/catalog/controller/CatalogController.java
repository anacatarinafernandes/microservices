package com.anafernandes.catalog.controller;

import com.anafernandes.catalog.dto.AuthorDto;
import com.anafernandes.catalog.dto.BookDto;
import com.anafernandes.catalog.exception.BookNotFoundException;
import com.anafernandes.catalog.service.CatalogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/catalog")
@SecurityRequirement(name = "custom")

public class CatalogController {
    private final CatalogService catalogService;
    private static final Logger logger = LogManager.getLogger(CatalogController.class);


    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping(path = "/books")
    @RolesAllowed("admin")
    public void registerBooks(@RequestBody List<BookRequest> bookRequests) {
        for (BookRequest bookRequest : bookRequests) {
            catalogService.registerBook(bookRequest);
        }
    }

    @PostMapping(path = "/book")
    @RolesAllowed("admin")
    public ResponseEntity<BookDto> registerBook(@RequestBody BookRequest bookRequest) {
        logger.info("new book registration {}" + bookRequest);


        BookDto book = catalogService.registerBook(bookRequest);

        return new ResponseEntity<>(book, HttpStatus.OK);

    }


    @GetMapping(path = "/books")
    @RolesAllowed("admin")
    public ResponseEntity<List<BookDto>> GetAllBooks() {

        List<BookDto> books = catalogService.GetAllBooks();

        if (books.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @GetMapping(path = "/books/available")
    @RolesAllowed("user")
    public ResponseEntity<List<BookDto>> GetAvailableBooks() {

        List<BookDto> books = catalogService.GetAvailableBooks();

        if (books.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @GetMapping(path = "/book/{bookTitle}")
    @RolesAllowed("user")
    public ResponseEntity<BookDto> getBookByTitle(@PathVariable("bookTitle") String bookTitle) throws BookNotFoundException {

        BookDto book = catalogService.GetBookDetails(bookTitle);

        if (book != null) {

            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        throw new BookNotFoundException("Book with title " + bookTitle + " not found");
    }


    @GetMapping(path = "/book/filter")
    @RolesAllowed("user")
    public ResponseEntity<List<BookDto>> filterBooks(@RequestParam(name = "author", required = false) String author,
                                                     @RequestParam(name = "category", required = false) String category,
                                                     @RequestParam(name = "maxPrice", required = false) Double maxPrice) {

        List<BookDto> books = catalogService.filterBooks(author, category, maxPrice);

        return new ResponseEntity<>(books, HttpStatus.OK);


    }

    @PostMapping(path = "/authors")
    @RolesAllowed("admin")
    public void registerAuthors(@RequestBody List<AuthorDto> authorRequests) {
        catalogService.addAuthors(authorRequests);
    }

    @PostMapping(path = "/author")
    @RolesAllowed("admin")
    public ResponseEntity<AuthorDto> registerAuthor(@RequestBody AuthorDto authorRequest) {
        logger.info("new author registration {}" + authorRequest);
        AuthorDto author = catalogService.addAuthor(authorRequest);
        return new ResponseEntity<>(author, HttpStatus.OK);

    }

    @GetMapping(path = "/author/{authorName}")
    @RolesAllowed("admin")
    public ResponseEntity<AuthorDto> getAuthorByName(@PathVariable("authorName") String authorName) {

        AuthorDto author = catalogService.GetAuthorByName(authorName);

        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);

    }

    @PostMapping(path = "/category")
    @RolesAllowed("admin")
    public void addCategory(@RequestBody String categoryName) {
        catalogService.addCategory(categoryName);
    }

    @GetMapping(path = "/book/{bookId}/stock")
    @RolesAllowed("admin")
    public ResponseEntity<Integer> getBookStock(@PathVariable("bookId") Integer bookId) {

        Integer bookStock = catalogService.getBookStock(bookId);

        if (bookStock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookStock, HttpStatus.OK);

    }
}
