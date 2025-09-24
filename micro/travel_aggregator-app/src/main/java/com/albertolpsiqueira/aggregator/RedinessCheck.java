package com.albertolpsiqueira.aggregator;

import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class RedinessCheck implements HealthCheck {

    @RestClient
    @Inject
    TravelFlightService travelFlightService;
    @RestClient
    @Inject
    TravelHotelService travelHotelService;
    @RestClient
    @Inject
    TravelOrderService travelOrderService;

    @Override
    public HealthCheckResponse call() {
        if (travelFlightService.travelFlights().isEmpty() ||
                travelHotelService.travelHotels().isEmpty() ||
                travelOrderService.travelOrders().isEmpty()){
            return HealthCheckResponse.down("Não estou pronto");
        }else {
            return HealthCheckResponse.up("Estou pronto");
        }
    }
}
