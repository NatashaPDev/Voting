package ru.natashapetrenko.voting.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class DishTo extends BaseTo{

    private final LocalDate date;

    private final String description;

    private final int price;

    private final int restaurant;

    public DishTo(@JsonProperty("id") Integer id,
                  @JsonProperty("date") LocalDate date,
                  @JsonProperty("description") String description,
                  @JsonProperty("price") int price,
                  @JsonProperty("restaurant_id") Integer restaurant_id) {
        super(id);
        this.date = date;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getRestaurant() {
        return restaurant;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}