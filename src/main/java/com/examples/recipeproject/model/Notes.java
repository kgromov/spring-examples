package com.examples.recipeproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by konstantin on 26.05.2019.
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "recipe")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Lob
    private String recipeNotes;
    @OneToOne
    private Recipe recipe;
}
