package com.albertolpsiqueira.aggregator;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Path("travel-aggregator")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
public class TravelAggregatorResource {

    @RestClient
    @Inject
    TravelFlightService travelFlightService;
    @RestClient
    @Inject
    TravelHotelService travelHotelService;
    @RestClient
    @Inject
    TravelOrderService travelOrderService;

    @GET
    public List<TravelAggregatorDTO> travelAggregatorDTOS(){
        return travelOrderService.<TravelOrder>travelOrders().stream()
                .map(
                        order -> TravelAggregatorDTO.of(
                                order,
                                travelFlightService.findByTravelOrderId(order.getId()),
                                travelHotelService.findByTravelOrderId(order.getId())
                        )
                ).collect(Collectors.toList());
    }

    @POST
    public TravelAggregatorDTO newTravelAggregatorDTO(TravelAggregatorDTO travelAggregatorDTO){
        TravelOrder travelOrder = new TravelOrder();
        travelOrder = travelOrderService.newTravelOrder(travelOrder);

        TravelFlight travelFlight = new TravelFlight();
        travelFlight.setFromAirport(travelAggregatorDTO.getFromAirport());
        travelFlight.setToAirport(travelAggregatorDTO.getToAirport());
        travelFlight.setTravelOrderId(travelOrder.getId());
        travelFlight = travelFlightService.newTravelFlight(travelFlight);

        TravelHotel travelHotel = new TravelHotel();
        travelHotel.setNights(travelAggregatorDTO.getNights());
        travelHotel.setTravelOrderId(travelOrder.getId());
        travelHotel = travelHotelService.newTravelHotel(travelHotel);

        return TravelAggregatorDTO.of(travelOrder, travelFlight, travelHotel);

    }

}
