package com.example.spring_app.services.mappers;


import com.example.spring_app.entities.Book;
import com.example.spring_app.models.BookDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    static ModelMapper modelMapper = new ModelMapper();

    public static Book mapBookDtoToBook(BookDto bookDto) {
        TypeMap<BookDto, Book> xxx = modelMapper.getTypeMap(BookDto.class, Book.class);
        if (xxx == null) {
            TypeMap<BookDto, Book> bookDtoToBookTypeMap = modelMapper.createTypeMap(BookDto.class, Book.class);
            bookDtoToBookTypeMap.addMappings(mapper -> {
                mapper.skip((Book::setId));
                mapper.map(BookDto::getAuthorId, (destination, value) -> destination.getAuthor().setId((String) value));
            });
        }
        return modelMapper.map(bookDto, Book.class);
    }


}
