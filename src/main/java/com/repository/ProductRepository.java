package com.repository;

import com.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long>{

    @Query("select p from Product p where p.name like  ?1")
    Optional<Product> findByName(String name);
}
