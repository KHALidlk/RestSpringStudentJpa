package org.example.jspspring.Repository;

import org.example.jspspring.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepoimpl {
    private  StudentRepo studentRepo;
    @Autowired
    public StudentRepoimpl(StudentRepo studentRepo) {
         this.studentRepo = studentRepo;
    }
    public void save(Student student) {
        studentRepo.save(student);
    }
    public void delete(Student student) {
        studentRepo.delete(student);
    }
    public List<Student> getAll() {
       return  studentRepo.findAll();
    }
    public Optional<Student> findByName(String name)
    {
        try {
            return studentRepo.findByName(name);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
  public void update(Student student) {
        studentRepo.save(student);
    }


    public Optional<Student> findByEmail(String email)
    {
        try {
            return studentRepo.findByEmail(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    public Student findById(int id)
    {
        return studentRepo.findById(id);
    }
}
