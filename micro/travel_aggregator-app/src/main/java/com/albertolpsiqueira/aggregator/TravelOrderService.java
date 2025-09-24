package com.albertolpsiqueira.aggregator;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.ArrayList;
import java.util.List;

@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TravelOrderService {

    @GET
    @Path("findById")
    @Timeout(value = 2000L)
    public TravelOrder findById(@QueryParam("id") long id);

    @GET
    @Timeout(value = 2000L)
    @Fallback(fallbackMethod = "travelOrdersFallback")
    @CircuitBreaker(requestVolumeThreshold = 10, failureRatio = 0.5, delay = 5000L, successThreshold = 5)
    public List<TravelOrder> travelOrders();

    @POST
    @Timeout(value = 2000L)
    public TravelOrder newTravelOrder(TravelOrder travelOrder);

    @DELETE
    @Timeout(value = 2000L)
    public Response deleteById(long id);

    default List<TravelOrder> travelOrdersFallback(){
        return new ArrayList<>();
    }

}
