package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	//talk to database
	@Override
	public List<Customer> getCustomers() {
		
		//get session
		Session session = sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> theQuery = 
				session.createQuery("from Customer order by lastName",Customer.class);
		
		//query
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session session =sessionFactory.getCurrentSession();
		
		//save...update if we have id
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		Customer theCustomer = session.get(Customer.class,id);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Customer where id=:theCustomerId");
		
		query.setParameter("theCustomerId",theId);
		query.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomers(String findName) {
		Session session =sessionFactory.getCurrentSession();
		Query query = null;
		List<Customer> customers;
		
		if(findName!=null && findName.trim().length()>0)
		{
			//searching for first name or last name
			query = session.createQuery("from Customer where "
					+ "lower(firstName) like :name "
					+ "or lower(lastName) like :name",Customer.class);
			
			query.setParameter("name", "%" + findName.toLowerCase() +"%");
			customers = query.getResultList();
			
		}
		else
		{
			query = session.createQuery("from Customer",Customer.class);
			customers = query.getResultList();
		}
		return customers;
	}

}
