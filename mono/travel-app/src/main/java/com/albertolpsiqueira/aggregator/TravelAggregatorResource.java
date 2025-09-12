package com.albertolpsiqueira.aggregator;

import com.albertolpsiqueira.travel_fight.TravelFlight;
import com.albertolpsiqueira.travel_fight.TravelFlightResource;
import com.albertolpsiqueira.travel_hotel.TravelHotel;
import com.albertolpsiqueira.travel_hotel.TravelHotelResource;
import com.albertolpsiqueira.travel_order.TravelOrder;
import com.albertolpsiqueira.travel_order.TravelOrderResource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Path("travel-aggregator")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TravelAggregatorResource {

    @Inject
    TravelFlightResource travelFlightResource;
    @Inject
    TravelHotelResource travelHotelResource;
    @Inject
    TravelOrderResource travelOrderResource;

    @GET
    public List<TravelAggregatorDTO> travelAggregatorDTOS(){
        return TravelOrder.<TravelOrder>listAll().stream()
                .map(
                        order -> TravelAggregatorDTO.of(
                                order,
                                travelFlightResource.findByTravelOrderId(order.id),
                                travelHotelResource.findByTravelOrderId(order.id)
                        )
                ).collect(Collectors.toList());
    }

    @POST
    public TravelAggregatorDTO newTravelAggregatorDTO(TravelAggregatorDTO travelAggregatorDTO){
        TravelOrder travelOrder = new TravelOrder();
        travelOrder = travelOrderResource.newTravelOrder(travelOrder);

        TravelFlight travelFlight = new TravelFlight();
        travelFlight.fromAirport = travelAggregatorDTO.getFromAirport();
        travelFlight.toAirport = travelAggregatorDTO.getToAirport();
        travelFlight.travelOrderId = travelOrder.id;
        travelFlight = travelFlightResource.newTravelFlight(travelFlight);

        TravelHotel travelHotel = new TravelHotel();
        travelHotel.nights = travelAggregatorDTO.getNights();
        travelHotel.travelOrderId = travelOrder.id;
        travelHotel = travelHotelResource.newTravelHotel(travelHotel);

        return TravelAggregatorDTO.of(travelOrder, travelFlight, travelHotel);

    }

}
