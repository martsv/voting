package ru.martsv.voting.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * mart
 * 20.08.2016
 */
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "restaurants_unique_name_address_idx")})
public class Restaurant extends NamedEntity {

    @Column(name = "description", nullable = false)
    @Size(max = 50)
    @NotEmpty
    private String description;

    @Column(name = "address", nullable = false)
    @NotEmpty
    private String address;

    public Restaurant() {}

    public Restaurant(Integer id, String name, String description, String address) {
        super(id, name);
        this.description = description;
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
