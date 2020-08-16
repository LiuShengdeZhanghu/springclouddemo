package com.liusheng.controller;

import com.liusheng.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/search")
    public String search(){
        System.out.println(Thread.currentThread().getName());
//        int i = 10 /0;
        return "search: "+port;
    }

    @GetMapping("/search/{id}")
    public Customer findById(@PathVariable Integer id){
        return new Customer(1,"liusheng",22);
    }

    @GetMapping("/getCustomer")
    public Customer getCustomer(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer age){
        return new Customer(id,name,age);
    }

    @PostMapping("/customer")
    public Customer saveCustomer(Customer customer){
        return customer;
    }

}
