package org.example.dao;

import org.example.Models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullname, birthyear) VALUES(?, ?)", person.getFullName(), person.getBirthYear());
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE Person SET fullname=?, birthyear=? WHERE id=?", person.getFullName(), person.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Person findPerson(int id) {
        return jdbcTemplate.query("select * from person WHERE id=(SELECT personid from books where id=?)",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}
