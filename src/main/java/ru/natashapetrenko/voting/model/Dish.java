package ru.natashapetrenko.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT m FROM Dish m ORDER BY m.date DESC"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish m WHERE m.id=:id"),
        @NamedQuery(name = Dish.GET_BY_DATE, query = "SELECT m FROM Dish m " +
                "WHERE m.date=:date AND m.restaurant.id = :restaurant_id"),
})
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "dishes_unique_user_datetime_idx")})
public class Dish extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET_BY_DATE = "Dish.GET_BY_DATE";

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(LocalDate date, String description, int price) {
        this(null, date, description, price);
    }

    public Dish(Integer id, LocalDate date, String description, int price) {
        super(id);
        this.date = date;
        this.description = description;
        this.price = price;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
