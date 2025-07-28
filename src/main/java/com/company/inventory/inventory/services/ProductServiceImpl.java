package com.company.inventory.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.inventory.dao.ICategoryDao;
import com.company.inventory.inventory.dao.IProductDao;
import com.company.inventory.inventory.model.Category;
import com.company.inventory.inventory.model.Product;
import com.company.inventory.inventory.response.ProductResponseRest;
import com.company.inventory.inventory.util.Util;

@Service
public class ProductServiceImpl implements IProductService {

    private ICategoryDao categoryDao;

    private IProductDao productDao;

    // Inyección mediante constructor
    public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            // search category to set in the product object
            Optional<Category> category = categoryDao.findById(categoryId);

            if (category.isPresent()) {
                product.setCategory(category.get());
            } else {
                response.setMetada("resupuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // Save the product
            Product productSaved = productDao.save(product);

            if (productSaved != null) {
                list.add(productSaved);
                response.getProduct().setProducts(list);
                response.setMetada("respuesta ok", "00", "Producto guardado");
            } else {
                response.setMetada("resupuesta nok", "-1", "Producto no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMetada("resupuesta nok", "-1", "Error al guardar");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long id) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            // search product to set in the product object
            Optional<Product> product = productDao.findById(id);

            if (product.isPresent()) {
                byte[] imageDescompressed = Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imageDescompressed);
                list.add(product.get());
                response.getProduct().setProducts(list);
                response.setMetada("Respuesta ok", "00", "Producto encontrado");
            } else {
                response.setMetada("resupuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetada("resupuesta nok", "-1", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchByName(String name) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            // search by name
            listAux = productDao.findByNameContainingIgnoreCase(name);

            if (listAux.size() > 0) {
                list.stream().forEach((p) -> {
                    byte[] imageDescompressed = Util.decompressZLib(p.getPicture());
                    p.setPicture(imageDescompressed);
                    list.add(p);
                });

                response.getProduct().setProducts(list);
                response.setMetada("Respuesta ok", "00", "Productos encontrados");
            } else {
                response.setMetada("resupuesta nok", "-1", "Productos no encontrados");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetada("resupuesta nok", "-1", "Error al buscar producto por nombre");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

}
