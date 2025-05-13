package org.example.jspspring.Repository;

import org.example.jspspring.Model.Admin;
import org.example.jspspring.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findByName(String name);
    Optional<Admin> findByEmail(String email);
    public Admin findById(int id);
}
