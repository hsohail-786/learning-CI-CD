package com.example.LearningMapStruct.mapper;

import com.example.LearningMapStruct.dto.StudentDTO;
import com.example.LearningMapStruct.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    
    StudentDTO toDTO(Student student);
    
    @Mapping(target = "courses", ignore = true)
    Student toEntity(StudentDTO studentDTO);
}
//echo "# learning-CI-CD" >> README.md
//git init
//git add README.md
//git commit -m "first commit"
//git branch -M main
//git remote add origin https://github.com/hsohail-786/learning-CI-CD.git
//git push -u origin main