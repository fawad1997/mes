package com.wulf.systems.mes.service;

import com.wulf.systems.mes.entity.Order;
import com.wulf.systems.mes.entity.Product;
import com.wulf.systems.mes.exception.GenericIdException;
import com.wulf.systems.mes.repository.OrderRepository;
import com.wulf.systems.mes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return  product.get();
        }else
            throw new GenericIdException("Product with id "+id+" does not exists");
    }

    public Product addOrUpdate(Product Product) {
        productRepository.save(Product);
        return Product;
    }

    public void deleteById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
        }else
            throw new GenericIdException("Product with id "+id+" does not exists");
    }
}
