package com.example.LearningMapStruct.mapper;

import com.example.LearningMapStruct.dto.TeacherDTO;
import com.example.LearningMapStruct.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {
    
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
    
    TeacherDTO toDTO(Teacher teacher);
    
    @Mapping(target = "courses", ignore = true)
    Teacher toEntity(TeacherDTO teacherDTO);
}
