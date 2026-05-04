package com.example.LearningMapStruct.mapper;

import com.example.LearningMapStruct.dto.CourseDTO;
import com.example.LearningMapStruct.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(target = "students", ignore = true)
    CourseDTO toDTO(Course course);
    
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "students", ignore = true)
    Course toEntity(CourseDTO courseDTO);
}
