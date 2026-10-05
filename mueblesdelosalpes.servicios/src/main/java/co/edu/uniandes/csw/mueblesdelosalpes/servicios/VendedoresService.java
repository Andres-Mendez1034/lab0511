package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Vendedor;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioVendedoresMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Vendedores")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendedoresService {

    @EJB
    private IServicioVendedoresMockLocal vendedoresEjb;

    /** GET: lista de vendedores del sistema. */
    @GET
    public List<Vendedor> getVendedores() {
        return vendedoresEjb.getVendedores();
    }

    /** POST: agrega un vendedor. 400 si la operacion es invalida. */
    @POST
    public Response agregarVendedor(Vendedor vendedor) {
        try {
            vendedoresEjb.agregarVendedor(vendedor);
            return Response.status(Response.Status.CREATED).entity(vendedor).build();
        } catch (OperacionInvalidaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /** DELETE: elimina un vendedor por su identificacion. 400 si no se puede. */
    @DELETE
    @Path("{id}")
    public Response eliminarVendedor(@PathParam("id") long id) {
        try {
            vendedoresEjb.eliminarVendedor(id);
            return Response.noContent().build();
        } catch (OperacionInvalidaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}