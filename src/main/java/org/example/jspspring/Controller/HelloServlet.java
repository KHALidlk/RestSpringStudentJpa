package org.example.jspspring.Controller;

import jakarta.validation.Valid;
import org.example.jspspring.DTO.StudentDTO;
import org.example.jspspring.Model.Student;
import org.example.jspspring.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@EnableWebSecurity

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/students")
public class HelloServlet {
    public HelloServlet(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    private StudentService studentService;
    @Autowired
    private org.springframework.context.ApplicationContext applicationContext;

    @GetMapping("")
    public List<StudentDTO> getStudents() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        System.out.println("Beans in IoC container:");
        for (String beanName : beanNames) {
            System.out.println("Bean: " + beanName);
        }
        return studentService.getAllStudents();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")  // Changed to match security config
    public void ajouterStudent(@RequestBody @Valid StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
    }

    @GetMapping("/name/{name}")
    public Optional<Student> findByName(@PathVariable("name") String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/email/{email}")
    public Optional<Student> findByEmail(@PathVariable("email") String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping("/id/{id}")
    public Student findById(@PathVariable("id") int id) {
        return studentService.findById(id);
    }

    @GetMapping("/profile")
    public String viewProfile() {
        return "User profile page";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student != null) {
            studentService.deleteStudent(student);
        }
    }

    @PutMapping("/update/{id}")
    public void updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        Student existingStudent = studentService.findById(id);
        if (existingStudent != null) {
            student.setId(id);
            studentService.updateStudent(student);
        }
    }
}