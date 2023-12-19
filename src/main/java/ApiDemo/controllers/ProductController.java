package ApiDemo.controllers;

import ApiDemo.Response.ApiResponse;
import ApiDemo.entities.Product;
import ApiDemo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products.json")
    public ApiResponse<List<Product>> getCustomers() {
        ApiResponse<List<Product>> response = new ApiResponse<>();
        response.setCode(200);
        response.setError(null);
        response.setData(productService.findAll());
        return response;
    }

    @GetMapping("/products/{id}.json")
    public ApiResponse<Product> getCustomer(@PathVariable("id") Long id) {
        Product customer = productService.findById(id);
        ApiResponse<Product> response = new ApiResponse<>();
        response.setCode(200);
        response.setData(customer);
        return response;
    }

    @PostMapping("/products.json")
    public ApiResponse<Product> create(@ModelAttribute Product customer) {
        ApiResponse<Product> response = new ApiResponse<>();
        Product customerSaved = productService.save(customer);
        response.setData(customerSaved);
        response.setCode(200);
        return response;
    }

    @PutMapping("/products/{id}.json")
    public ApiResponse<Product> update(@ModelAttribute Product customer, @PathVariable("id") Long id) {
        customer.setId(id);
        ApiResponse<Product> response = new ApiResponse<>();
        Product customerSaved = productService.save(customer);
        response.setData(customerSaved);
        response.setCode(200);
        return response;
    }

    @DeleteMapping("/products/{id}.json")
    public ApiResponse<Product> delete(@PathVariable("id") Long id) {
        ApiResponse<Product> response = new ApiResponse<>();
        response.setCode(200);
        Product customer = productService.findById(id);
        productService.deleteById(id);
        response.setData(customer);
        return response;
    }


}
