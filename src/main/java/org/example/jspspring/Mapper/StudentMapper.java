package org.example.jspspring.Mapper;

import org.example.jspspring.Model.Student;
import org.example.jspspring.DTO.StudentDTO;
import org.example.jspspring.Model.Student;

public class StudentMapper {

    public static StudentDTO toDTO(Student entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setNotes(entity.getNotes());
        return dto;
    }

    public static Student toEntity(StudentDTO dto) {
        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setNotes(dto.getNotes());
        return entity;
    }
}
