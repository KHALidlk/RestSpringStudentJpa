package org.example.jspspring.Service;

import org.example.jspspring.Model.Prof;
import org.example.jspspring.Repository.ProfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfService {
    private final ProfRepo profRepo;
    @Autowired
    public ProfService(ProfRepo profRepo) {
        this.profRepo = profRepo;
    }
    public void save(Prof prof) {
        profRepo.save(prof);
    }
    public void delete(Prof prof) {
        profRepo.delete(prof);
    }
    public Prof findById(int id) {
        return profRepo.findById(id);
    }

    public Prof findByName(String name) {
        return profRepo.findByName(name).orElse(null);
    }
    public Prof findByEmail(String email) {
        return profRepo.findByEmail(email).orElse(null);
    }
    public List<Prof> findAll() {
        return profRepo.findAll();
    }

}
