package com.example.LearningMapStruct.service;

import com.example.LearningMapStruct.dto.StudentDTO;
import com.example.LearningMapStruct.entity.Student;
import com.example.LearningMapStruct.mapper.StudentMapper;
import com.example.LearningMapStruct.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.INSTANCE.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.INSTANCE.toDTO(savedStudent);
    }
    
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return StudentMapper.INSTANCE.toDTO(student);
    }
    
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
    
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.INSTANCE.toDTO(updatedStudent);
    }
    
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.delete(student);
    }
    
}
