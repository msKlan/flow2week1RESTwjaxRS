package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import facades.PersonFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("person")
public class PersonResource {

    private EntityManagerFactory emf; 
    private PersonFacade facade =  PersonFacade.getFacadeExample(emf);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPerson() {
        List<Person> list = facade.getAllPersons();
//        return Response.ok().entity(GSON.toJson(list)).build();
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByID(@PathParam("id") int id) {
        Person p = facade.getPerson(id);
        return Response.ok().entity(GSON.toJson(p)).build();
    }   
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Person entity) {
//        return Response.ok().entity(GSON.toJson(facade.addPerson(entity.getfirstName(), entity.getlastName(), entity.getPhone()))).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Person entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
