package com.uk.POST_CRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.awt.geom.GeneralPath;

// Это покдлючения к БД
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    // Это поля для нее
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title ;

    @NotNull
    private String description ;

    private Long watches = 0L;


}
