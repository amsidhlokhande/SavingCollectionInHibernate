/**
 * 
 */
package com.amsidh.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.amsidh.dto.Address;
import com.amsidh.dto.UserDetails;

/**
 * @author VIRU
 *
 */
public class MainApp {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sessionFactory.openSession();
       session.beginTransaction();
      
       UserDetails userDetails=new UserDetails(new Integer(123456), "amsidh.lokhande", populateAddresses());
       
       session.save(userDetails);
       
       session.getTransaction().commit();
       session.close();
       
       session=sessionFactory.openSession();
       session.beginTransaction();
      
       List<UserDetails> userlist=(List<UserDetails>)session.createQuery("from UserDetails").list();
       session.getTransaction().commit();
       session.close();
     for(UserDetails user:userlist)
      {
    	 List<Address> listOfAddress=user.getAddresses();
    	 System.out.print(user.getUserId()+ user.getUserName());
    	 for(Address address:listOfAddress)
    	 {
    		 System.out.print(address.getStreet()+", "+address.getCity()+", "+address.getState()+" "+address.getPicode());
    	 }
    	 System.out.println();
      }
       
      
       
       
	}

	public static List<Address> populateAddresses()
	{
		List<Address> addresses=new ArrayList<Address>();
		Address homeAddress=new Address("Shiranal", "Bijapur", "Karnataka", new Integer(586119));
		Address tempAddress=new Address("Pimple Gurav", "Pune", "Maharashtra", new Integer(411061));
		Address officeAddress=new Address("Hinjewadi", "Pune", "Maharashtra", new Integer(411057));
		addresses.add(homeAddress);
		addresses.add(tempAddress);
		addresses.add(officeAddress);
		return addresses;
	}
}
