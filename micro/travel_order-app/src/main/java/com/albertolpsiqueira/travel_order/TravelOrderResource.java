package com.albertolpsiqueira.travel_order;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("travel-order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TravelOrderResource {

    @GET
    public List<TravelOrder> travelOrders(){
        return TravelOrder.listAll();
    }

    @GET
    @Path("findById")
    public TravelOrder findById(@QueryParam("id") long id){
        return TravelOrder.findById(id);
    }

    @POST
    @Transactional
    public TravelOrder newTravelOrder(TravelOrder travelOrder){
        travelOrder.id = null;
        travelOrder.persist();

        return travelOrder;
    }

    @DELETE
    @Transactional
    public Response deleteById(long id){
        TravelOrder.deleteById(id);

        return Response.accepted().build();
    }

}
