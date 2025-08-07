package com.company.inventory.inventory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.company.inventory.controller.CategoryRestController;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CategoryRestControllerTest {

    @InjectMocks
    private CategoryRestController categoryRestController; // El controlador real que se prueba

    @Mock
    private ICategoryService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTest() {
        // Simula una petición HTTP
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Datos de prueba
        Category category = new Category();
        category.setId(1L);
        category.setName("Lacteos");
        category.setDescription("productos derivados de la leche");

        CategoryResponseRest responseRest = new CategoryResponseRest();
        // Aquí puedes añadir más datos si quieres verificar el cuerpo

        // Simula el comportamiento del servicio
        when(service.save(any(Category.class))).thenReturn(
                new ResponseEntity<>(responseRest, HttpStatus.OK));

        // Llamada real al método del controlador
        ResponseEntity<CategoryResponseRest> response = categoryRestController.save(category);

        // Verificación
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}
