package com.lin.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lin.springdemo.dao.CustomerDAO;
import com.lin.springdemo.entity.Customer;
import com.lin.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// need to inject the customer service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {
		
		// get customer from the dao 
		
		
		List<Customer> theCustomers = null;
		
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);
			
		} else {
			
			theCustomers = customerService.getCustomers();

		}
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, 
			                        Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = customerService.getCustomer(theId);
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete model attribute to bind form data
		customerService.deleteCustomer(theId);
				
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {
        // search customers from the service
        List<Customer> theCustomer = customerService.searchCustomers(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("customers", theCustomer);
        return "list-customer";        
    }
}
