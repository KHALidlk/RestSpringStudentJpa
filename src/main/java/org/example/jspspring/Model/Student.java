package org.example.jspspring.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Le nom est requis")
    @Size(min = 2, max = 30, message = "Le nom doit contenir entre 2 et 30 caractères")
    private String name;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "student_notes", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "note")
    private List<Double> notes;

//    @ManyToMany(mappedBy = "students")
//    private List<Module> modules;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "admin_id", nullable = false)
//    private Admin admin;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", notes=" + notes +
                '}';
    }
}