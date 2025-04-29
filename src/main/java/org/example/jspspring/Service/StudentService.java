package org.example.jspspring.Service;

import org.example.jspspring.DTO.StudentDTO;
import org.example.jspspring.Model.Student;
import org.example.jspspring.Mapper.StudentMapper;
import org.example.jspspring.Repository.StudentRepoimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private  StudentRepoimpl studentRepo;
    @Autowired
    public StudentService(StudentRepoimpl studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Méthode pour ajouter un étudiant
    public void addStudent(StudentDTO studentDTO) {
        Student studentEntity = StudentMapper.toEntity(studentDTO);
        studentRepo.save(studentEntity);
    }

    // Méthode pour récupérer tous les étudiants
    public List<StudentDTO> getAllStudents() {
        List<Student> students = (List<Student>) studentRepo.getAll();
        return students.stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
