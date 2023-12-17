package ApiDemo.controllers;

import ApiDemo.Response.ApiResponse;
import ApiDemo.entities.Customer;
import ApiDemo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers.json")
    public ApiResponse<List<Customer>> getCustomers() {
        ApiResponse<List<Customer>> response = new ApiResponse<>();
        response.setCode(200);
        response.setError(null);
        response.setData(customerService.findAll());
        return response;
    }

    @GetMapping("/customers/{id}.json")
    public ApiResponse<Customer> getCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        ApiResponse<Customer> response = new ApiResponse<>();
        response.setCode(200);
        response.setData(customer);
        return response;
    }

    @PostMapping("/customers.json")
    public ApiResponse<Customer> create(@ModelAttribute Customer customer) {
        ApiResponse<Customer> response = new ApiResponse<>();
        Customer customerSaved = customerService.save(customer);
        response.setData(customerSaved);
        response.setCode(200);
        return response;
    }

    @PutMapping("/customers/{id}.json")
    public ApiResponse<Customer> update(@ModelAttribute Customer customer, @PathVariable("id") Long id) {
        customer.setId(id);
        ApiResponse<Customer> response = new ApiResponse<>();
        Customer customerSaved = customerService.save(customer);
        response.setData(customerSaved);
        response.setCode(200);
        return response;
    }
    @DeleteMapping("/customers/{id}.json")
    public  ApiResponse<Customer> delete (@PathVariable("id") Long id){
        ApiResponse<Customer> response = new ApiResponse<>();
        response.setCode(200);
        Customer customer = customerService.findById(id);
         customerService.deleteById(id);
        response.setData(customer);
        return response;
    }

}
