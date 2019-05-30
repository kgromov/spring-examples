package com.examples.recipeproject.model;

import javax.persistence.*;

/**
 * Created by konstantin on 25.05.2019.
 */
@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column
    private String description;

    public UnitOfMeasure() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
