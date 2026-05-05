package com.example.demo.service;

import com.example.demo.dto.RecipeDto;
import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RecipeServices {
    private final RecipeRepository recipeRepository;

    public Recipe create(RecipeDto request, String userId) {
        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setIngredients(request.getIngredients());
        recipe.setPrepTime(request.getPrepTime());
        recipe.setCategory(request.getCategory());
        recipe.setUserId(userId);
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAll(String userId) {
        return recipeRepository.findByUserId(userId);
    }

    public Recipe oneRecipe(String id, String userId) {
        Recipe recipe  = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (!recipe.getUserId().equals(userId)) {
            throw new RuntimeException("Forbidden- ");
        }
        return recipe;
    }

    public Recipe updateRecipe(String id, RecipeDto request, String userId) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (!recipe.getUserId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }

        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setIngredients(request.getIngredients());
        recipe.setPrepTime(request.getPrepTime());
        recipe.setCategory(request.getCategory());

        return recipeRepository.save(recipe);
    }

    public void delete(String id, String userId) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (!recipe.getUserId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }

        recipeRepository.delete(recipe);
    }
}
