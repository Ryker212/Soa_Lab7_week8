package th.ac.ku.kps.eng.cpe.soa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import th.ac.ku.kps.eng.cpe.soa.model.Customer;

public class CustomerDAO {
	public ArrayList<Customer> getAllCustomers() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Customer");
		ArrayList<Customer> customers = (ArrayList<Customer>) query.list();
		session.close();
		return customers;
	}

	public boolean addCustomer(Customer c) {
		try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			session.save(c);
			tx.commit();
			session.close();
		} catch (TransactionException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//match username password
	public Customer findCustomer(Customer c) {
		Session session = SessionUtil.getSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("username", c.getUsername()));
		cr.add(Restrictions.eq("pwd", c.getPwd()));
		if (cr.list().size() == 0)
			return null;
		return (Customer) cr.list().get(0);
	}
	//match name
	public Customer findCustomer(String userName) {
        Session session = SessionUtil.getSession();
        Query cr = session.createQuery("from Customer where username=:name");
        cr.setParameter("name", userName);
        return (Customer) cr.list().get(0);
    }
	//match name
	public Customer findCustomerByName(String name) {
        List<Customer> cusList = getAllCustomers();
        for (Customer cus : cusList) {
            if (cus.getName().equalsIgnoreCase(name)) {
                return cus;
            }
        }
        return null;
    }
}