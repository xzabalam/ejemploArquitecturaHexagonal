package org.example.adapters;

import org.example.data.BookDto;
import org.example.entity.Book;
import org.example.mappers.BookMapper;
import org.example.repository.BookRepository;
import org.example.spi.BookPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookJpaAdapter implements BookPersistencePort {
    private final BookRepository bookRepository;

    public BookJpaAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        Book bookSaved = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDto(bookSaved);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return addBook(bookDto);
    }

    @Override
    public List<BookDto> getBooks() {
        List<Book> bookList = bookRepository.findAll();
        return BookMapper.INSTANCE.bookListToBookDtoList(bookList);
        // return bookList.stream().map(book -> BookDto.builder().id(book.getId()).description(book.getDescription()).title(book.getTitle()).price(book.getPrice()).build()).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isPresent()) {
            return BookMapper.INSTANCE.bookToBookDto(book.get());
        }

        return null;
    }
}
