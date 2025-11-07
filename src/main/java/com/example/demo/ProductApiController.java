package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // optional: allow access from front-end
public class ProductApiController {

    @GetMapping
    public List<Product> getAllProducts() {
        return ProductLoader.loadProducts();
    }

}
