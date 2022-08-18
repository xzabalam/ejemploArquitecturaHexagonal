package org.example.configuration;

import org.example.adapters.BookJpaAdapter;
import org.example.api.BookServicePort;
import org.example.repository.BookRepository;
import org.example.service.BookServiceImpl;
import org.example.spi.BookPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public BookPersistencePort bookPersistence(){
        return new BookJpaAdapter(bookRepository);
    }

    @Bean
    public BookServicePort bookService(){
        return new BookServiceImpl(bookPersistence());
    }
}
