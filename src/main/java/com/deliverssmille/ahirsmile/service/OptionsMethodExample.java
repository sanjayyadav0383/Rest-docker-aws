package com.deliverssmille.ahirsmile.service;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("books")
public class OptionsMethodExample {
 
  @OPTIONS
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/")
  public Response optionsForBookResource() {        
      return Response.status(200)
        .header("Allow","POST, PUT, GET")
        .header("Content-Type", MediaType.APPLICATION_JSON)
        .header("Content-Length", "0")
        .build();
  }
     
}
