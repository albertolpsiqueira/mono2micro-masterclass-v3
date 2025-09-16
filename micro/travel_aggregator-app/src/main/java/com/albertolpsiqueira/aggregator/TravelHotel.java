package com.albertolpsiqueira.aggregator;

public class TravelHotel {

    private Long id;
    private Long travelOrderId;
    private Integer nights;

    public TravelHotel() {
    }


    public TravelHotel(Long id, Long travelOrderId, Integer nights) {
        this.id = id;
        this.travelOrderId = travelOrderId;
        this.nights = nights;
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

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }
}
