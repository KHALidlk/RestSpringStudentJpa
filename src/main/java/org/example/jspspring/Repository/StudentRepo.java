package org.example.jspspring.Repository;

import org.example.jspspring.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
public Student findByName(String name);
public Student findByEmail(String email);
}
