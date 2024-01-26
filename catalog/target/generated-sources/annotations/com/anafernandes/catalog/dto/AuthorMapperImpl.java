package com.anafernandes.catalog.dto;

import com.anafernandes.catalog.dto.AuthorDto.AuthorDtoBuilder;
import com.anafernandes.catalog.model.Author;
import com.anafernandes.catalog.model.Author.AuthorBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T11:02:55+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDto toDto(Author author) {
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

    @Override
    public Author toEntity(AuthorDto author) {
        if ( author == null ) {
            return null;
        }

        AuthorBuilder author1 = Author.builder();

        author1.id( author.getId() );
        author1.name( author.getName() );
        author1.fullName( author.getFullName() );
        author1.birthDate( author.getBirthDate() );
        author1.birthPlace( author.getBirthPlace() );
        author1.deathDate( author.getDeathDate() );
        author1.deathPlace( author.getDeathPlace() );
        author1.about( author.getAbout() );

        return author1.build();
    }

    @Override
    public List<AuthorDto> toDtoList(List<Author> authors) {
        if ( authors == null ) {
            return null;
        }

        List<AuthorDto> list = new ArrayList<AuthorDto>( authors.size() );
        for ( Author author : authors ) {
            list.add( toDto( author ) );
        }

        return list;
    }

    @Override
    public List<Author> toEntityList(List<AuthorDto> authors) {
        if ( authors == null ) {
            return null;
        }

        List<Author> list = new ArrayList<Author>( authors.size() );
        for ( AuthorDto authorDto : authors ) {
            list.add( toEntity( authorDto ) );
        }

        return list;
    }
}
