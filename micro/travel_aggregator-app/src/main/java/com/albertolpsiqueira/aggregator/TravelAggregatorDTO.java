package com.albertolpsiqueira.aggregator;

public class TravelAggregatorDTO {

    private long travelOrderId;
    private String toAirport;
    private String fromAirport;
    private Integer nights;

    public TravelAggregatorDTO() {
    }

    private TravelAggregatorDTO(long travelOrderId, String toAirport, String fromAirport, Integer nights) {
        this.travelOrderId = travelOrderId;
        this.toAirport = toAirport;
        this.fromAirport = fromAirport;
        this.nights = nights;
    }

    public static TravelAggregatorDTO of(long travelOrderId, String toAirport, String fromAirport, Integer nights){
        return new TravelAggregatorDTO(travelOrderId, toAirport, fromAirport,nights);
    }

    public static TravelAggregatorDTO of(TravelOrder travelOrder, TravelFlight travelFlight, TravelHotel travelHotel){
        if(travelFlight == null){
            travelFlight = new TravelFlight();
        }
        if (travelHotel == null){
            travelHotel = new TravelHotel();
        }
        return new TravelAggregatorDTO(travelOrder.getId(), travelFlight.getToAirport(), travelFlight.getFromAirport(), travelHotel.getNights());
    }

    public long getTravelOrderId() {
        return travelOrderId;
    }

    public void setTravelOrderId(long travelOrderId) {
        this.travelOrderId = travelOrderId;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }
}
