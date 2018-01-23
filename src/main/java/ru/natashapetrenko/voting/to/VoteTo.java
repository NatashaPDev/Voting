package ru.natashapetrenko.voting.to;

import java.time.LocalDate;
import java.time.LocalTime;

public class VoteTo extends BaseTo{

    private final LocalDate date;

    private final LocalTime time;

    private final int restaurant;

    private final int user;

    public VoteTo(Integer id, LocalDate date, LocalTime time, int restaurant, int user) {
        super(id);
        this.date = date;
        this.time = time;
        this.restaurant = restaurant;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
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
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}