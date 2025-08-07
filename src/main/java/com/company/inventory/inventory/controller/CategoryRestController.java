package com.company.inventory.inventory.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.inventory.model.Category;
import com.company.inventory.inventory.response.CategoryResponseRest;
import com.company.inventory.inventory.services.ICategoryService;
import com.company.inventory.inventory.util.CategoryExcelExport;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = { "*" }) // Puerto de angular
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryService service;

    /**
     * get all categories
     * 
     * @return
     */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories() {

        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;

    }

    /*
     * get categories by id
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;

    }

    /**
     * Save categories
     * 
     * @param category
     * @return
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {

        ResponseEntity<CategoryResponseRest> response = service.save(category);
        return response;

    }

    /**
     * Update Categories
     * 
     * @param category
     * @param id
     * @return
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = service.update(category, id);
        return response;

    }

    /**
     * Delete categories
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = service.deleteById(null, id);
        return response;

    }

    /**
     * export to excel file
     * 
     * @param response
     * @throws IOException
     */
    @GetMapping("/categories/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerkey = "Content-Disposition";
        String headerValue = "attachment; filename=result_category.xlsx";
        response.setHeader(headerkey, headerValue);

        ResponseEntity<CategoryResponseRest> categoryResponse = service.search();

        CategoryExcelExport excelExport = new CategoryExcelExport(
                categoryResponse.getBody().getCategoryResponse().getCategory());

        excelExport.export(response);

    }

}
