package com.examples.recipeproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by konstantin on 25.05.2019.
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"ingredients", "categories"})
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int prepTime;
    @Column
    private int cookTime;
    @Column
    private int servings;
    @Column
    private String source;
    @Column
    private String url;
    private String description;
    @Column
    @Lob
    private String directions;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @Column
    @Lob
    private byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();
}
