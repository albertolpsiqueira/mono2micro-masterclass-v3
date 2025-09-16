package com.albertolpsiqueira.aggregator;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TravelFlightService {

    @GET
    public List<TravelFlight> travelFlights();

    @GET
    @Path("findById")
    public TravelFlight findById(@QueryParam("id") long id);

    @GET
    @Path("findByTravelOrdeId")
    public TravelFlight findByTravelOrderId(@QueryParam("travelOrderId") long id);

    @POST
    public TravelFlight newTravelFlight(TravelFlight travelFlight);

    @DELETE
    public Response deleteById(long id);

}
