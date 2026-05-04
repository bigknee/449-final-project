//updating/creating recipes
package com.example.demo.dto;
import lombok.Data;

@Data
public class RecipeDto {
    private String title;
    private String description;
    private String ingredients;
    private int prepTime;
    private String category;
}
