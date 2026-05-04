package com.example.LearningMapStruct.service;

import com.example.LearningMapStruct.dto.TeacherDTO;
import com.example.LearningMapStruct.entity.Teacher;
import com.example.LearningMapStruct.mapper.TeacherMapper;
import com.example.LearningMapStruct.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    
    private final TeacherRepository teacherRepository;
    
    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = TeacherMapper.INSTANCE.toEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.INSTANCE.toDTO(savedTeacher);
    }
    
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        return TeacherMapper.INSTANCE.toDTO(teacher);
    }
    
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(TeacherMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
    
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setSpecialization(teacherDTO.getSpecialization());
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.INSTANCE.toDTO(updatedTeacher);
    }
    
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacherRepository.delete(teacher);
    }
    
}
