package com.example.LearningMapStruct.service;

import com.example.LearningMapStruct.dto.CourseDTO;
import com.example.LearningMapStruct.entity.Course;
import com.example.LearningMapStruct.entity.Teacher;
import com.example.LearningMapStruct.mapper.CourseMapper;
import com.example.LearningMapStruct.repository.CourseRepository;
import com.example.LearningMapStruct.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    
    @Autowired
    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }
    
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Teacher teacher = teacherRepository.findById(courseDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + courseDTO.getTeacherId()));
        
        Course course = CourseMapper.INSTANCE.toEntity(courseDTO);
        course.setTeacher(teacher);
        Course savedCourse = courseRepository.save(course);
        return CourseMapper.INSTANCE.toDTO(savedCourse);
    }
    
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return CourseMapper.INSTANCE.toDTO(course);
    }
    
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
    
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        
        Teacher teacher = teacherRepository.findById(courseDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + courseDTO.getTeacherId()));
        
        course.setName(courseDTO.getName());
        course.setCode(courseDTO.getCode());
        course.setCredits(courseDTO.getCredits());
        course.setTeacher(teacher);
        Course updatedCourse = courseRepository.save(course);
        return CourseMapper.INSTANCE.toDTO(updatedCourse);
    }
    
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        courseRepository.delete(course);
    }
    
}
