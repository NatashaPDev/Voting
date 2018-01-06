package ru.natashapetrenko.voting.to;

import java.time.LocalDateTime;

public class VoteTo extends BaseTo{

    private final LocalDateTime dateTime;


    private final String restaurant;

    public VoteTo(Integer id, LocalDateTime dateTime, String restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.restaurant = restaurant;
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