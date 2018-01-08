package ru.natashapetrenko.voting.model;

import javax.persistence.*;

@SuppressWarnings("JpaQlInspection")
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    public Restaurant() {
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
