package com.albertolpsiqueira.aggregator;

public class TravelFlight {

    private Long id;
    private Long travelOrderId;
    private String fromAirport;
    private String toAirport;

    public TravelFlight() {
    }

    public TravelFlight(Long id, Long travelOrderId, String fromAirport, String toAirport) {
        this.id = id;
        this.travelOrderId = travelOrderId;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelOrderId() {
        return travelOrderId;
    }

    public void setTravelOrderId(Long travelOrderId) {
        this.travelOrderId = travelOrderId;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }
}
