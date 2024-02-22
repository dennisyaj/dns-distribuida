package com.distribuida.appauthors.controller;


import com.distribuida.appauthors.model.Author;
import com.distribuida.appauthors.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private IAuthorRepository iAuthorRepository;

    @GetMapping
    public List<Author> findAll() {
        System.out.println("**************************");
        return (List<Author>) iAuthorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        return iAuthorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return iAuthorRepository.save(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable Integer id, @RequestBody Author author) {
        Author existingAuthor = iAuthorRepository.findById(id).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setFirstName(author.getFirstName());
            existingAuthor.setLastName(author.getLastName());
            iAuthorRepository.save(existingAuthor);
            return ResponseEntity.ok(existingAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        iAuthorRepository.deleteById(id);
    }
}

