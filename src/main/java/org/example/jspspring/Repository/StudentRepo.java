package org.example.jspspring.Repository;

import org.example.jspspring.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    Optional<Student> findByName(String name);
    Optional<Student> findByEmail(String email);
public Student findById(int id);
}
