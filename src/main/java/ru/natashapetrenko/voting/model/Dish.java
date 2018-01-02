package ru.natashapetrenko.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT m FROM Dish m ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish m WHERE m.id=:id"),
        @NamedQuery(name = Dish.GET_BETWEEN, query = "SELECT m FROM Dish m " +
                "WHERE m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC"),
})
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date_time"}, name = "dishes_unique_user_datetime_idx")})
public class Dish extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET_BETWEEN = "Dish.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

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

    public Dish(LocalDateTime dateTime, String description, int price) {
        this(null, dateTime, description, price);
    }

    public Dish(Integer id, LocalDateTime dateTime, String description, int price) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
