package com.itmuch.cms.webservice.server.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.itmuch.cms.webservice.common.MediaTypes;

/**
 * cxf在web.xml侦听/cxf, 在applicationContext.xml里侦听/jaxrx，完整访问路径为 /cxf/jaxrs/user/1.后缀
 * @author zhouli
 */
@Path("/user")
public class RestUserService {

    @GET
    @Path("/{id}")
    @Produces(value = { MediaTypes.APPLICATION_XML_UTF_8, MediaTypes.JSON_UTF_8 })
    public RestResult selectById(@PathParam("id") Integer id) {
        RestUser entity = new RestUser();
        entity.setId(id);
        entity.setAge(18);
        entity.setUsername("test");
        entity.setSex((byte) 1);

        return new RestResult(entity);
    }

    @PUT
    @Path("/{id}")
    @Produces(value = { MediaTypes.APPLICATION_XML_UTF_8, MediaTypes.JSON_UTF_8 })
    public RestResult updateById(@PathParam("id") Integer id, RestUser user) {
        return new RestResult(user);
    }
}
