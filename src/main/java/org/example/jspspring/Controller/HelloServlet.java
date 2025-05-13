package org.example.jspspring.Controller;

import jakarta.validation.Valid;
import org.example.jspspring.DTO.StudentDTO;
import org.example.jspspring.Model.Student;
import org.example.jspspring.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/students")
public class HelloServlet {
    public HelloServlet(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    private StudentService studentService;
  @GetMapping("")
  public List<StudentDTO> getStudents() {
     return studentService.getAllStudents();
  }
  @GetMapping("/hello")
  public String hello() {
      return "hello";
  }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void  ajouterStudent(@RequestBody @Valid StudentDTO studentDTO) {
         studentService.addStudent(studentDTO);
    }
    @GetMapping("/name/{name}")
    public Optional<Student> findByName(@PathVariable("name") String name)
    {
        return studentService.findByName(name);
    }
    @GetMapping("/email/{email}")
    public Optional<Student> findByEmail(@PathVariable("email") String email)
    {
        return studentService.findByEmail(email);
    }
    @GetMapping("/id/{id}")
    public Student findById(@PathVariable("id") int id)
    {
        return studentService.findById(id);
    }
    public HelloServlet() {
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
