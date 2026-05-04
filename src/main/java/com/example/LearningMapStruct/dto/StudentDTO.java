package com.example.LearningMapStruct.dto;

import lombok.*;
import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Long> courseIds = new HashSet<>();
    
}
