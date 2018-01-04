package ru.natashapetrenko.voting.to;

import java.time.LocalDateTime;

public class VoteTo {
    private final Integer id;

    private final LocalDateTime dateTime;


    private final String restaurant;

    public VoteTo(Integer id, LocalDateTime dateTime, String restaurant) {
        this.id = id;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getRestaurant() {
        return restaurant;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                '}';
    }
}