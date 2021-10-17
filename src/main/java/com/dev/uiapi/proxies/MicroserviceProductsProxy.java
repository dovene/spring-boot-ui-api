package com.dev.uiapi.proxies;

import com.dev.uiapi.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-products", url = "localhost:1000")
public interface MicroserviceProductsProxy {

    @GetMapping(value = "/products")
    List<Product> getAllProducts();

    @GetMapping(value = "/products/{reference}")
    Product getProductById(@PathVariable String reference);

    @PostMapping(value = "/products")
    Product createProduct(@RequestBody  Product product);

    @PutMapping(value = "/products")
    Product updateProduct(@RequestBody Product product);

    @DeleteMapping(value = "/products/{reference}")
    void deleteProduct(@PathVariable String reference);

}
