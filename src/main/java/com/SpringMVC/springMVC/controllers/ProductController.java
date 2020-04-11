package com.SpringMVC.springMVC.controllers;

import com.SpringMVC.springMVC.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private ProductService productService;

    @RequestMapping("/products")
    public String listProduct(Model model){
        model.addAttribute("products",productService.listAllProduct());
        return "products";
    }
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
