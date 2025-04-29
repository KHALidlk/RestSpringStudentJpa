package org.example.jspspring.Repository;

import org.example.jspspring.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Student> getAll() {
       return  studentRepo.findAll();
    }

    public Student findByName(String name)
    {
        return studentRepo.findByName(name);
    }
    public Student findByEmail(String email)
    {
        return studentRepo.findByEmail(email);
    }



}
