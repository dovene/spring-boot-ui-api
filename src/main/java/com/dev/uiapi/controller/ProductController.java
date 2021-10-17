package com.dev.uiapi.controller;

import com.dev.uiapi.model.Product;
import com.dev.uiapi.proxies.MicroserviceProductsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    MicroserviceProductsProxy microserviceProductsProxy;

    public String getProductsUsingRestTemplate(Model model){
        String url = "http://localhost:1000/products";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Product>>() {}
        );
        model.addAttribute("products", response.getBody());
        return "product/list";
    }

    @GetMapping
    public String getProducts(Model model){
        model.addAttribute("products",microserviceProductsProxy.getAllProducts() );
        return "product/list";
    }

    @GetMapping("/{reference}")
    public String getProducts(@PathVariable String reference){
        microserviceProductsProxy.getProductById(reference);
        return "product/list";
    }

    @GetMapping("/add")
    public String productCreationForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/add";
    }

    @PostMapping("/save")
    public String saveNewProduct(@ModelAttribute Product product){
        microserviceProductsProxy.createProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{reference}")
    public String deleteByReference(@PathVariable String reference){
        microserviceProductsProxy.deleteProduct(reference);
        return "redirect:/products";
    }

    @GetMapping("/edit/{reference}")
    public String edit(@PathVariable String reference, Model model){
        Product product = microserviceProductsProxy.getProductById(reference);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/saveOnUpdate")
    public String saveOnUpdate(@ModelAttribute Product product){
        microserviceProductsProxy.updateProduct(product);
        return "redirect:/products";
    }

}
