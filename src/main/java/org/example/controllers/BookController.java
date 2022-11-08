package org.example.controllers;

import org.example.Models.Book;
import org.example.Models.Person;
import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String indexBook(Model model){
        model.addAttribute("books", bookDAO.indexBook());
        return "books/indexBook";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id, @ModelAttribute("person1") Person person){
        model.addAttribute("book", bookDAO.showBook(id));
        model.addAttribute("person", personDAO.findPerson(id));
        model.addAttribute("people", personDAO.index());
        return "books/showBook";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/newBook";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("person") Book book){
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id){
        bookDAO.updateBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/set")
    public String setPerson(@ModelAttribute("person")Person person, @PathVariable("id") int id){
        bookDAO.setPerson(person.getId(), id);
        return "redirect:/books/" + id;
    }
}
