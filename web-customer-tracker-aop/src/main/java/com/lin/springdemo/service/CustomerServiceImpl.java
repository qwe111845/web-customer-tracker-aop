package com.lin.springdemo.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.springdemo.dao.CustomerDAO;
import com.lin.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {		
		
		return customerDAO.getCustomer(theId);
		
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDAO.deleteCustomer(theId);		
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		return customerDAO.searchCumtomers(theSearchName);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		return customerDAO.getCustomers(theSortField);
    }
	

}
