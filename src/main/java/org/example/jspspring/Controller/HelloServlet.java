package org.example.jspspring.Controller;

import jakarta.validation.Valid;
import org.example.jspspring.DTO.StudentDTO;
import org.example.jspspring.Model.Student;
import org.example.jspspring.Service.Helloservice;
import org.example.jspspring.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/hello")
public class HelloServlet {
    private Helloservice helloservice;
    public HelloServlet(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    private StudentService studentService;
    @GetMapping("")
    public String hello() {
        return "Hello from StudentController!";
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String ajouterStudent(@RequestBody @Valid StudentDTO studentDTO) {
         studentService.addStudent(studentDTO);
         return studentDTO.toString();
    }

    public HelloServlet() {
    }
    public Helloservice getHelloservice() {
        return helloservice;
    }

    public void setHelloservice(Helloservice helloservice) {
        this.helloservice = helloservice;
    }
}
