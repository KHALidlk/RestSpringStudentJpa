package org.example.jspspring.Controller;

import org.example.jspspring.Model.Prof;
import org.example.jspspring.Service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfController {
    private final ProfService profService;

    @Autowired
    public ProfController(ProfService profService) {
        this.profService = profService;
    }

    @GetMapping
    public List<Prof> getAllProfessors() {
        return profService.findAll();
    }

    @GetMapping("/{id}")
    public Prof getProfessorById(@PathVariable("id") int id) {
      return profService.findById(id);
    }

    @GetMapping("/name/{name}")
    public Prof getProfessorByName(@PathVariable("name") String name) {
    return profService.findByName(name);
    }

    @GetMapping("/email/{email}")
    public Prof getProfessorByEmail(@PathVariable("email") String email) {
       return profService.findByEmail(email);

    }

    @PostMapping
    public ResponseEntity<Void> createProfessor(@RequestBody Prof prof) {
        profService.save(prof);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public Prof updateProfessor(@PathVariable("id") int id) {
       return profService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Prof deleteProfessor(@PathVariable("id") int id) {
      return  profService.findById(id);

    }
}