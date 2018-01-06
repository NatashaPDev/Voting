package ru.natashapetrenko.voting.to;

import javax.validation.constraints.NotBlank;

public class RestaurantTo extends BaseTo{

    @NotBlank
    private String name;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
