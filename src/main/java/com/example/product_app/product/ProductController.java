package com.example.product_app.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Product created successfully", createdProduct));
    }

    @GetMapping
    public ApiResponse<List<Product>> getAll() {
        return new ApiResponse<>(true, "Products fetched successfully", productService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> getById(@PathVariable String id) {
        return new ApiResponse<>(true, "Product fetched successfully", productService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<Product> update(@PathVariable String id, @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);
        return new ApiResponse<>(true, "Product updated successfully", updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Product> delete(@PathVariable String id) {
        Product deletedProduct = productService.delete(id);
        return new ApiResponse<>(true, "Product deleted successfully", deletedProduct);
    }
}
