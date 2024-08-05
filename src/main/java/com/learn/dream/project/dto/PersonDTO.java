package com.learn.dream.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    
    private Long id;
    @NotNull(message = "Data not be null")  
    private String name;
}
