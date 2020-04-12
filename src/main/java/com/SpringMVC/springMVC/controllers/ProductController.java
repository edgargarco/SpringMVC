package com.SpringMVC.springMVC.controllers;

import com.SpringMVC.springMVC.models.Product;
import com.SpringMVC.springMVC.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/product/{id}")
    public String getProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product",productService.getProductById(id));
        return "product";

    }
    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("product",new Product());
        return "productform";
    }
    @RequestMapping(value = "/product",method = RequestMethod.POST)
    public String saveOrUpdateProduct(Product product){
        Product saveOrUpdateProductuct = productService.saveOrUpdateProduct(product);
        return "redirect:/product/"+saveOrUpdateProductuct.getId();

    }

    @RequestMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product",productService.getProductById(id));
        return "productform";
    }
    @RequestMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
