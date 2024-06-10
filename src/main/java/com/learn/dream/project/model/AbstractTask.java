package com.learn.dream.project.model;

import org.hibernate.mapping.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class AbstractTask {

    
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;
    
    @NotBlank(message = "Onwer is mandatory")
    private Person onwer;

    private boolean isConclude;

    public abstract void start();

    public abstract void conclude();


    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
