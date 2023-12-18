package com.manorrock.toyger.webapp;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 * The API version checkout.
 *
 * @see https://distribution.github.io/distribution/spec/api/#api-version-check
 * @author MAnfred Riem (mriem@manorrock.com)
 */
@Path("v2")
public class ApiVersionCheckResource {

    /**
     * Return status 200 with header 
     * 'Docker-Distribution-API-Version: registry/2.0'.
     *
     * @return the response.
     */
    @GET
    public Response apiVersionCheck() {
        return Response
                .ok()
                .header("Docker-Distribution-API-Version", "registry/2.0")
                .build();
    }
}
