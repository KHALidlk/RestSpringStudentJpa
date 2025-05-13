package org.example.jspspring.Repository;

import org.example.jspspring.Model.Prof;
import org.example.jspspring.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfRepo  extends JpaRepository<Prof, Long> {
    Optional<Prof> findByName(String name);
    Optional<Prof> findByEmail(String email);
    public Prof findById(int id);
}
