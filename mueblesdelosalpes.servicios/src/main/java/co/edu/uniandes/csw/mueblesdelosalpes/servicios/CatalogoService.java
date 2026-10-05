package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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

@Path("/Catalogo")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogoService {

    /**
     * Referencia al EJB del catalogo encargado de realizar las operaciones del mismo.
     */
    @EJB
    private IServicioCatalogoMockLocal catalogoEjb;

    /**
     * GET: lista JSON con los muebles disponibles para la venta.
     */
    @GET
    @Path("muebles/")
    public List<Mueble> getTodosLosMuebles() {
        return catalogoEjb.darMuebles();
    }

    /**
     * POST: agrega un mueble al catalogo. Recibe el mueble en JSON.
     */
    @POST
    @Path("muebles/")
    public Response agregarMueble(Mueble mueble) {
        catalogoEjb.agregarMueble(mueble);
        return Response.status(Response.Status.CREATED).entity(mueble).build();
    }

    /**
     * PUT: remueve un ejemplar del mueble (no el mueble completo).
     */
    @PUT
    @Path("muebles/{id}/quitar-ejemplar")
    public Response removerEjemplarMueble(@PathParam("id") long id) {
        catalogoEjb.removerEjemplarMueble(id);
        return Response.ok().build();
    }

    /**
     * DELETE: elimina un mueble del sistema por su referencia.
     */
    @DELETE
    @Path("muebles/{id}")
    public Response eliminarMueble(@PathParam("id") long id) {
        catalogoEjb.eliminarMueble(id);
        return Response.noContent().build();
    }
}