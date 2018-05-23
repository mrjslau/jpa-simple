package lt.vu.rest;

import lt.vu.entities.Author;
import lt.vu.persistence.AuthorsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/authors")
public class AuthorsController {
    @Inject
    private AuthorsDAO authorsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Author getById(@PathParam("id") final Integer id) {
        return authorsDAO.findOne(id);
    }

    @Path("/{id}/{name}")
    @PUT
    //@Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer authorId,
            @PathParam("name") final String authorName
    ) {
        try {
            Author existingAuthor = authorsDAO.findOne(authorId);
            if (existingAuthor == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingAuthor.setName(authorName);
            authorsDAO.update(existingAuthor);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/create/{name}")
    @POST
    @Transactional
    public Author create(@PathParam("name") String name) {
        Author author = new Author();
        author.setName(name);
        authorsDAO.save(author);
        return author;
    }
}
