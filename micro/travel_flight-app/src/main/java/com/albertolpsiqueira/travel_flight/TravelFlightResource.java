package com.albertolpsiqueira.travel_flight;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("travel-flight")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
public class TravelFlightResource {

    @GET
    public List<TravelFlight> travelFlights(){
        return TravelFlight.listAll();
    }

    @GET
    @Path("findById")
    public TravelFlight findById(@QueryParam("id") long id){
        return TravelFlight.findById(id);
    }

    @GET
    @Path("findByTravelOrdeId")
    public TravelFlight findByTravelOrderId(@QueryParam("travelOrderId") long id){
        return TravelFlight.findByTravelOrderIf(id);
    }

    @POST
    @Transactional
    public TravelFlight newTravelFlight(TravelFlight travelFlight){
        travelFlight.id = null;
        travelFlight.persist();

        return travelFlight;
    }

    public Response deleteById(long id){
        TravelFlight.deleteById(id);
        return Response.accepted().build();
    }

}
