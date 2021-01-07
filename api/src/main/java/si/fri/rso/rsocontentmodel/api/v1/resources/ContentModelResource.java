package si.fri.rso.rsocontentmodel.api.v1.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import si.fri.rso.rsocontentmodel.lib.Base64FileConverter;
import si.fri.rso.rsocontentmodel.lib.ContentModel;
import si.fri.rso.rsocontentmodel.services.beans.ContentModelBean;

@ApplicationScoped
@Path("/content-models")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(allowOrigin = "*")
public class ContentModelResource {

    private Logger log = Logger.getLogger(ContentModelResource.class.getName());

    @Inject
    private ContentModelBean contentModelBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getContentModel() {

        List<ContentModel> contentModel = contentModelBean.getContentModelFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(contentModel).build();
    }

    @GET
    @Path("/{contentModelId}")
    public Response getContentModel(@PathParam("contentModelId") Integer contentModelId) {

        ContentModel contentModel = contentModelBean.getContentModel(contentModelId);

        if (contentModel == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(contentModel).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createContentModel(ContentModel contentModel) throws IOException {
         if ((contentModel.getTitle() == null || contentModel.getAttributes() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            contentModel = contentModelBean.createContentModel(contentModel);
        }

        return Response.status(Response.Status.OK).entity(contentModel).build();
    }

    @PUT
    @Path("{contentModelId}")
    public Response putContentModel(@PathParam("contentModelId") Integer contentModelId,
                                     ContentModel contentModel) {

        contentModel = contentModelBean.putContentModel(contentModelId, contentModel);

        if (contentModel == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @DELETE
    @Path("{contentModelId}")
    public Response deleteContentModel(@PathParam("contentModelId") Integer contentModelId) {

        boolean deleted = contentModelBean.deleteContentModel(contentModelId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
