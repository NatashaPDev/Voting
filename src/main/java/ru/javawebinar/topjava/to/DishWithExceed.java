package ru.javawebinar.topjava.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class DishWithExceed {
    private final Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int price;

    private final String restaurant;

    public DishWithExceed(@JsonProperty("id") Integer id,
                          @JsonProperty("dateTime") LocalDateTime dateTime,
                          @JsonProperty("description") String description,
                          @JsonProperty("price") int price,
                          @JsonProperty("restaurant") String restaurant) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getRestaurant() {
        return restaurant;
    }

    @Override
    public String toString() {
        return "DishWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}