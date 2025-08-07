package com.company.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.inventory.model.Category;

public interface ICategoryDao extends JpaRepository<Category, Long>{

}
