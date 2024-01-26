package com.anafernandes.catalog.dto;

import com.anafernandes.catalog.dto.AuthorDto.AuthorDtoBuilder;
import com.anafernandes.catalog.dto.BookDto.BookDtoBuilder;
import com.anafernandes.catalog.dto.CategoryDto.CategoryDtoBuilder;
import com.anafernandes.catalog.model.Author;
import com.anafernandes.catalog.model.Author.AuthorBuilder;
import com.anafernandes.catalog.model.Book;
import com.anafernandes.catalog.model.Book.BookBuilder;
import com.anafernandes.catalog.model.Category;
import com.anafernandes.catalog.model.Category.CategoryBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T11:02:55+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDtoBuilder bookDto = BookDto.builder();

        bookDto.title( book.getTitle() );
        bookDto.originalTitle( book.getOriginalTitle() );
        bookDto.isbn( book.getIsbn() );
        bookDto.language( book.getLanguage() );
        bookDto.stockAvailable( book.getStockAvailable() );
        bookDto.price( book.getPrice() );
        bookDto.dateCreated( book.getDateCreated() );
        bookDto.dateUpdated( book.getDateUpdated() );
        bookDto.authors( authorSetToAuthorDtoSet( book.getAuthors() ) );
        bookDto.category( categoryToCategoryDto( book.getCategory() ) );

        return bookDto.build();
    }

    @Override
    public Book toEntity(BookDto book) {
        if ( book == null ) {
            return null;
        }

        BookBuilder book1 = Book.builder();

        book1.title( book.getTitle() );
        book1.originalTitle( book.getOriginalTitle() );
        book1.isbn( book.getIsbn() );
        book1.language( book.getLanguage() );
        book1.stockAvailable( book.getStockAvailable() );
        book1.price( book.getPrice() );
        book1.dateCreated( book.getDateCreated() );
        book1.dateUpdated( book.getDateUpdated() );
        book1.authors( authorDtoSetToAuthorSet( book.getAuthors() ) );
        book1.category( categoryDtoToCategory( book.getCategory() ) );

        return book1.build();
    }

    @Override
    public List<BookDto> toDtoList(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( books.size() );
        for ( Book book : books ) {
            list.add( toDto( book ) );
        }

        return list;
    }

    protected AuthorDto authorToAuthorDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDtoBuilder authorDto = AuthorDto.builder();

        authorDto.id( author.getId() );
        authorDto.name( author.getName() );
        authorDto.fullName( author.getFullName() );
        authorDto.birthDate( author.getBirthDate() );
        authorDto.birthPlace( author.getBirthPlace() );
        authorDto.deathDate( author.getDeathDate() );
        authorDto.deathPlace( author.getDeathPlace() );
        authorDto.about( author.getAbout() );

        return authorDto.build();
    }

    protected Set<AuthorDto> authorSetToAuthorDtoSet(Set<Author> set) {
        if ( set == null ) {
            return null;
        }

        Set<AuthorDto> set1 = new HashSet<AuthorDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Author author : set ) {
            set1.add( authorToAuthorDto( author ) );
        }

        return set1;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.name( category.getName() );

        return categoryDto.build();
    }

    protected Author authorDtoToAuthor(AuthorDto authorDto) {
        if ( authorDto == null ) {
            return null;
        }

        AuthorBuilder author = Author.builder();

        author.id( authorDto.getId() );
        author.name( authorDto.getName() );
        author.fullName( authorDto.getFullName() );
        author.birthDate( authorDto.getBirthDate() );
        author.birthPlace( authorDto.getBirthPlace() );
        author.deathDate( authorDto.getDeathDate() );
        author.deathPlace( authorDto.getDeathPlace() );
        author.about( authorDto.getAbout() );

        return author.build();
    }

    protected Set<Author> authorDtoSetToAuthorSet(Set<AuthorDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Author> set1 = new HashSet<Author>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AuthorDto authorDto : set ) {
            set1.add( authorDtoToAuthor( authorDto ) );
        }

        return set1;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        CategoryBuilder category = Category.builder();

        category.name( categoryDto.getName() );

        return category.build();
    }
}
