package com.controller;

import com.model.Product;
import com.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findOne(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(id).get(),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){
        Optional<Product> products = productService.findById(id);
        if (!products.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            product.setId(id);
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(productOptional.get(),HttpStatus.NO_CONTENT);
    }

}
