package com.itmuch.platform.webservice.client.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * cxf在web.xml侦听/cxf, 在applicationContext.xml里侦听/jaxrx，完整访问路径为 /cxf/jaxrs/user/1.后缀
 * @author zhouli
 */
@Path("/user")
public interface IRestUserClient {

    @GET
    @Path("/{id}")
    public RestResult selectById(@PathParam("id") Integer id);

    @PUT
    @Path("/{id}")
    public RestResult updateById(@PathParam("id") Integer id, RestUser user);
}
