package facades;

import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    public interface IPersonFacade {
      public Person addPerson(String fName, String lName, String phone);  
      public Person deletePerson(int id);  
      public Person getPerson(int id);  
      public List<Person> getAllPersons();  
      public Person editPerson(Person p);  
    }
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person addPerson(String fName, String lName, String phone) {
        EntityManager em = emf.createEntityManager();
        Person p = new Person(fName, lName, phone);
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        } 
    } 

    public Person deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person p;
        try {
            p = em.find(Person.class, id);
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        } 
        return p;
    }  

    public Person getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Person p = em.find(Person.class, id);
            return p;
        } finally {
            em.close();
        }
    }  

    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Person.getAll").getResultList();
        } finally {
            em.close();
        }
    }  

    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.refresh(p);
            em.getTransaction().commit();            
        } finally {
            em.close();
        } 
        return p;
    }  

}
