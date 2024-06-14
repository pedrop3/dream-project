package com.learn.dream.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PersonDTO {
    
    private Long id;
    @NotNull(message = "Data not be null")  
    private String name;
}