package com.lin.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lin.springdemo.entity.Customer;
import com.lin.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by firstName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
        
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSession.saveOrUpdate(theCustomer);

	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		// Get Customer by Id
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCumtomers(String theSearchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
	        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
		
		List<Customer> theCustomers = theQuery.getResultList();
		
		
		return theCustomers;
	}

	@Override
	public List<Customer> getCustomers(int theSortField) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		String theFieldName = null;
		
		switch(theSortField) {
		    case SortUtils.FIRST_NAME:
		    	theFieldName = "firstName";
		    	break;
		    case SortUtils.LAST_NAME:
		    	theFieldName = "lastName";
		    	break;
		    case SortUtils.EMAIL:
		    	theFieldName = "email";		
		}
		
		String queryString = "from Customer order by " + theFieldName;
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery(queryString, Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		return customers;
	}
	

}
