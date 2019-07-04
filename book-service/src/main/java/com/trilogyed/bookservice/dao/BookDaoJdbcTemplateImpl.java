package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {

    private static final String INSERT_BOOK =
            "insert into book (title, author) values (?, ?)";
    private static final String SELECT_BOOK =
            "select * from book where book_id = ?";
    private static final String SELECT_ALL_BOOKS =
            "select * from book";
    private static final String UPDATE_BOOK =
            "update book set title = ?, author = ? where book_id = ?";
    private static final String DELETE_BOOK =
            "delete from book where book_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        jdbcTemplate.update(
                INSERT_BOOK,
                book.getTitle(),
                book.getAuthor()
        );
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        book.setBook_id(id);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books =
                jdbcTemplate.query(
                        SELECT_ALL_BOOKS,
                        this::mapRowToBook
                );
        return books;
    }

    @Override
    public Book getBook(int book_id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK, this::mapRowToBook, book_id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateBook(Book book) {
        try {
            jdbcTemplate.update(UPDATE_BOOK,
                    book.getTitle(),
                    book.getAuthor(),
                    book.getBook_id()
            );
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("An error occurred while updating a book:" + ex.getMessage());
        }
    }

    @Override
    public void deleteBook(int book_id) {
        try {
            jdbcTemplate.update(DELETE_BOOK, book_id);
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("An error occurred while deleting a book:" + ex.getMessage());
        }
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBook_id(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        return book;
    }

}
