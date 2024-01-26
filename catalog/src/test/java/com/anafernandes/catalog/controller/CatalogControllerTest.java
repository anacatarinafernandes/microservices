package com.anafernandes.catalog.controller;

import com.anafernandes.catalog.dto.AuthorDto;
import com.anafernandes.catalog.dto.BookDto;
import com.anafernandes.catalog.service.CatalogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CatalogController.class)
//@Import(SecurityConfig.class)
//@AutoConfigureMockMvc(addFilters = false)
public class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogService catalogService;

//    @MockBean
//    private JwtDecoder jwtDecoder;

    @Test
    @WithMockUser("user")
    void getAllBooks_ListHasBooks_ReturnsStatusOk() throws Exception {

        BookDto bookDto = BookDto.builder()
                .title("Book1")
                .originalTitle("Book1")
                .language("Portuguese")
                .isbn(123456)
                .stockAvailable(2)
                .price(10.0)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();

        List<BookDto> bookDtos = new ArrayList<>();

        bookDtos.add(bookDto);

        when(catalogService.GetAllBooks()).thenReturn(bookDtos);

        this.mockMvc.perform(get("/api/v1/catalog/books"))
                .andExpect(status().is(200));
    }


    @Test
    @WithMockUser("user")
    void getAllBooks_ListIsEmpty_ReturnsStatusNotFound() throws Exception {

        List<BookDto> bookDtos = new ArrayList<>();

        when(catalogService.GetAllBooks()).thenReturn(bookDtos);

        this.mockMvc.perform(get("/api/v1/catalog/books"))
                .andExpect(status().is(404));
    }

    @Test
    @WithMockUser("admin")
    void registerBook() throws Exception {

        ArrayList<String> authors = new ArrayList<>();
        authors.add("Author1");

        BookRequest bookRequest = BookRequest.builder()
                .title("Book1")
                .originalTitle("Book1")
                .language("Portuguese")
                .isbn(123456)
                .stockAvailable(2)
                .price(10.0)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .authors(authors)
                .category("Category1")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String bookRequestAsJson = objectMapper.writeValueAsString(bookRequest);

        this.mockMvc.perform(post("/api/v1/catalog/book").with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookRequestAsJson))
                .andExpect(status().is(200));
    }


    @Test
    void getBookByTitle() {
    }

    @Test
    void filterBooks() {
    }

    @Test
    void registerAuthors() {
    }

    @Test
    void registerAuthor() {
    }

    @Test
    @WithMockUser("admin")
    void getAuthorByName_AuthorExists_ReturnsStatusOk() throws Exception {

        AuthorDto authorDto = AuthorDto.builder()
                .name("Author1").build();

        when(catalogService.GetAuthorByName("Author1")).thenReturn(authorDto);

        this.mockMvc.perform(get("/api/v1/catalog/author/Author1"))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser("admin")
    void getAuthorByName_AuthorDoesNotExists_ReturnsStatusNotFound() throws Exception {

        this.mockMvc.perform(get("/api/v1/catalog/author/Author1"))
                .andExpect(status().is(404));
    }

    @Test
    @WithMockUser("admin")
    void addCategory() throws Exception {

        this.mockMvc.perform(post("/api/v1/catalog/category").with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Category1"))
                .andExpect(status().is(200));

    }
}