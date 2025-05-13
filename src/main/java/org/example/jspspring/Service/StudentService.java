package org.example.jspspring.Service;

import org.example.jspspring.DTO.StudentDTO;
import org.example.jspspring.Model.Student;
import org.example.jspspring.Mapper.StudentMapper;
import org.example.jspspring.Repository.StudentRepoimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private  StudentRepoimpl studentRepo;
    @Autowired
    public StudentService(StudentRepoimpl studentRepo) {
        this.studentRepo = studentRepo;
    }
    public Optional<Student> findByName(String name)
    {
        return studentRepo.findByName(name);
    }
    public Optional<Student> findByEmail(String email)
    {
        return studentRepo.findByEmail(email);
    }    // Méthode pour ajouter un étudiant
    public void addStudent(StudentDTO studentDTO) {
        Student studentEntity = StudentMapper.toEntity(studentDTO);
        studentRepo.save(studentEntity);
    }
    public Student findById(int id)
    {
        return studentRepo.findById(id);
    }
   public void deleteStudent(Student student) {
        studentRepo.delete(student);
    }
    public void updateStudent(Student student) {
        studentRepo.update(student);
    }

    // Méthode pour récupérer tous les étudiants
    public List<StudentDTO> getAllStudents() {
        List<Student> students = (List<Student>) studentRepo.getAll();
        return students.stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

}
