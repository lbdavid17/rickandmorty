package com.cebem.rickandmorty.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
// 2  Zelda


//  33       el mejor RPG de la historia     ayer     2 

@Entity
@Table (name = "comment")
public class CommentModel {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable= false)
    private long id;
    @Column ( nullable = false)
    private String text;
    private LocalDateTime createdAt;

     //el JPA HACE AUTOMATICAMENTE EL JOIN ENTRE COMENTARIO Y VIDEOJUEGO
    @ManyToOne
    @JoinColumn(name="videogame_id")
    private VideogameModel videogame;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = LocalDateTime.now();
    }

    public VideogameModel getVideogame() {
        return videogame;
    }

    public void setVideogame(VideogameModel videogame) {
        this.videogame = videogame;
    }

    
}