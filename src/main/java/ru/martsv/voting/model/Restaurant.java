package ru.martsv.voting.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * mart
 * 20.08.2016
 */
@NamedNativeQuery(
        name = Restaurant.GET_WINNERS,
        query = "SELECT r.id as id" +
                "      ,r.name as name" +
                "      ,r.description as description" +
                "      ,r.address as address" +
                "  FROM restaurants r" +
                " WHERE r.id IN (SELECT v.restaurant_id" +
                "                  FROM votes v" +
                "                 WHERE v.date=:date" +
                "                 GROUP BY v.restaurant_id" +
                "                HAVING COUNT(v.id) = (SELECT COUNT(v.id)" +
                "                                        FROM votes v" +
                "                                       WHERE v.date=:date" +
                "                                       GROUP by v.restaurant_id" +
                "                                       ORDER BY 1 DESC" +
                "                                       LIMIT 1))" +
                " ORDER BY r.name",
        resultClass = Restaurant.class
)

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "restaurants_unique_name_address_idx")})
public class Restaurant extends NamedEntity {

    public static final String GET_WINNERS = "Restaurant.getWinners";

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
