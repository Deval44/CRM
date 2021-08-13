package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel)
	{
		//get customers from service;
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add customers to model
		theModel.addAttribute("customers", theCustomers);	
				
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String addcustomers(Model model)
	{
		Customer thecustomer =new Customer();
		
		model.addAttribute("customer",thecustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomers(@ModelAttribute("customer") Customer theCustomer)
	{
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,Model model)
	{
		//get customer
		Customer thecustomer = customerService.getCustomer(id);
		
		//set customer as a model attribute to model
		model.addAttribute("customer",thecustomer);
		
		//send to form for update
		return "customer-form";
	}
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id)
	{
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("findName") String findName, Model model)
	{
		List<Customer> theCustomers = customerService.searchCustomers(findName);
		
		model.addAttribute("customers", theCustomers);
		return "list-customers";
	}
}


