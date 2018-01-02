package ru.natashapetrenko.voting.model;

import javax.persistence.*;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT m FROM Restaurant m ORDER BY m.name"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant m WHERE m.id=:id")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    public static final String ALL_SORTED = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id) {
        super(id, "");
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
