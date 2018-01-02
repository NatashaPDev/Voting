package ru.natashapetrenko.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT m FROM Vote m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Vote.GET_BETWEEN, query = "SELECT m FROM Vote m " +                "WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC"),
})
@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "votes_unique_user_datetime_idx")})
public class Vote extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Vote.getAll";
    public static final String DELETE = "Vote.delete";
    public static final String GET_BETWEEN = "Vote.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDateTime dateTime, Restaurant restaurant) {
        this(null, dateTime, restaurant);
    }

    public Vote(Integer id, LocalDateTime dateTime, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                '}';
    }
}
