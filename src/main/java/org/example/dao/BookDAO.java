package org.example.dao;

import org.example.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> indexBook() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void updateBook(Book book, int id) {
        jdbcTemplate.update("UPDATE books SET name=?, authorname=?, year=? WHERE id=?", book.getName(), book.getAuthorName(), book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }

    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO books(name, authorname, year) VALUES(?, ?, ?)", book.getName(), book.getAuthorName(), book.getYear());
    }

    public List<Book> findBook(int personId){
        return jdbcTemplate.query("SELECT * FROM books WHERE personid=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE books SET personid=0 WHERE id=?", id);
    }

    public void setPerson(int idPerson, int id) {
        jdbcTemplate.update("UPDATE books SET personid=? WHERE id=?", idPerson, id);
    }
}
