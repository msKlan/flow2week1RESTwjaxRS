/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entities.Person;

/**
 *
 * @author Klan
 */
public class Tester {
    
    public static void main(String[] args) {
        
	Person p = new Person("Michael", "Klan", "12121212");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        
        EntityManager em = emf.createEntityManager();
        
	try {
            em.getTransaction().begin();	
            em.persist(p);	
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}
