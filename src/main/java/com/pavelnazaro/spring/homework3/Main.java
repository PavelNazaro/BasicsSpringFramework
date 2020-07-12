package com.pavelnazaro.spring.homework3;

import com.pavelnazaro.spring.models.Customer;
import com.pavelnazaro.spring.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/crud/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Customer customer = new Customer("Ivan");
            session.save(customer);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Customer simpleItemFromDb = session.get(Customer.class, 1L);
            System.out.println(simpleItemFromDb);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Customer reader = session.get(Customer.class, 1L);
            System.out.println(reader);
            System.out.println("Products: ");
            for (Product b : reader.getProducts()) {
                System.out.println(b.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
