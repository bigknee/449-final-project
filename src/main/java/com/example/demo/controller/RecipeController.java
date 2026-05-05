package com.example.demo.controller;

import com.example.demo.dto.RecipeDto;
import com.example.demo.entity.Recipe;
import com.example.demo.service.RecipeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeServices recipeServices;

    @PostMapping
    public ResponseEntity<Recipe> create(@RequestBody RecipeDto request,
                                         Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recipeServices.create(request, userId));
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAll(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(recipeServices.getAll(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getOne(@PathVariable String id,
                                         Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(recipeServices.oneRecipe(id, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable String id, @RequestBody RecipeDto request, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(recipeServices.updateRecipe(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        recipeServices.delete(id, userId);
        return ResponseEntity.noContent().build();
    }
}