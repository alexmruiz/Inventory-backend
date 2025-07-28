package com.company.inventory.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventory.inventory.model.Product;
import java.util.List;

public interface IProductDao extends CrudRepository <Product, Long>{

    //@Query("select p from Product p where p.name like %?1%")
    //List<Product> findByNameLike(String name);

    //Métodos de JPA Ver la web
    List<Product> findByNameContainingIgnoreCase(String name);

}
