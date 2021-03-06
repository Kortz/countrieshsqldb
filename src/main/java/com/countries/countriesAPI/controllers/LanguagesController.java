package com.countries.countriesAPI.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.countries.countriesAPI.dataAccess.LanguageDataAccess;
import com.countries.countriesAPI.models.Language;

@Path("languages")
public class LanguagesController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanguages() {
        LanguageDataAccess l = new LanguageDataAccess();
        Response response;
        List<Language> languageList = null;
        try {
            languageList = l.getLanguageList();
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
            response = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Something went wrong, contact someone who can sort it").build();
        }
            
        if ( languageList == null) {
            response = Response.noContent().build();
        } else {
            response = Response.ok(languageList).build(); 
        }

        return response;
    }

}