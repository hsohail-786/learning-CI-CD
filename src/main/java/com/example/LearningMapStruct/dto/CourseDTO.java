package com.example.LearningMapStruct.dto;

import lombok.*;
import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    
    private Long id;
    private String name;
    private String code;
    private Integer credits;
    private Long teacherId;
    private Set<Long> studentIds = new HashSet<>();
    
}
