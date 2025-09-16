package com.albertolpsiqueira.travel_hotel;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class TravelHotel extends PanacheEntity {

    public long travelOrderId;
    public Integer nights;

    public static TravelHotel findByTraveOrderId(long id){
        return find("travelOrderId", id).firstResult();
    }

}
