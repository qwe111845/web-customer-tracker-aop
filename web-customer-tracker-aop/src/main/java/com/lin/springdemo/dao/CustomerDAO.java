package com.lin.springdemo.dao;

import java.util.List;

import com.lin.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCumtomers(String theSearchName);

	public List<Customer> getCustomers(int theSortField);

}
