package com.controller;


import com.model.Category;
import com.model.Product;
import com.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll(){
        Iterable<Category> categoryIterable = categoryService.findAll();
        return new ResponseEntity<>(categoryIterable, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/edit")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody Category category){
        Optional<Category> categories = categoryService.findById(id);
        if (!categories.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            categoryService.findById(categories.get().getId());
            return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteProduct(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(categoryOptional.get(),HttpStatus.NO_CONTENT);
    }

}
