package com.wulf.systems.mes.controller;

import com.wulf.systems.mes.entity.Product;
import com.wulf.systems.mes.service.ProductService;
import com.wulf.systems.mes.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ValidationErrorService validationErrorService;
    @GetMapping
    public List<Product> getProducts() {
        return productService.findAll();
    }
    //If you want to add more info about api
    //@ApiOperation(value = "Find Product by id",notes = "search by id in db",response = Product.class)
    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors!=null)
            return errors;
        return new ResponseEntity<>(productService.addOrUpdate(product), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors!=null)
            return errors;
        product.setId(id);
        return new ResponseEntity<>(productService.addOrUpdate(product), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}