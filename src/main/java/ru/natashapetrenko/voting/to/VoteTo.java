package ru.natashapetrenko.voting.to;

import java.time.LocalDateTime;

public class VoteTo extends BaseTo{

    private final LocalDateTime dateTime;

    private final int restaurant;

    private final int user;

    public VoteTo(Integer id, LocalDateTime dateTime, int restaurant, int user) {
        super(id);
        this.dateTime = dateTime;
        this.restaurant = restaurant;
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public int getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                '}';
    }
}