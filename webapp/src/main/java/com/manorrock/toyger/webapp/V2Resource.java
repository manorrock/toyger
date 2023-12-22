package com.manorrock.toyger.webapp;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 * @author MAnfred Riem (mriem@manorrock.com)
 */
@Path("v2")
public class V2Resource {

    /**
     * The API version check.
     *
     * Return status 200 with header 'Docker-Distribution-API-Version:
     * registry/2.0'.
     *
     * @see
     * https://distribution.github.io/distribution/spec/api/#api-version-check
     * @return the response.
     */
    @GET
    public Response apiVersionCheck() {
        return Response
                .ok()
                .header("Docker-Distribution-API-Version", "registry/2.0")
                .build();
    }
    
    /**
     * The catalog.
     * 
     * @see https://distribution.github.io/distribution/spec/api/#listing-repositories
     * @return the response.
     */
    @GET
    @Path("_catalog")
    @Produces("application/json")
    public Response catalog() {
        return Response
                .ok()
                .entity("{}")
                .build();
    }
}
