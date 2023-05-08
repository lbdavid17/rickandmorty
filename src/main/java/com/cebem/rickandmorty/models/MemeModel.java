package com.cebem.rickandmorty.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="meme")
public class MemeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (unique =true,nullable=false)
    public long id;
    public String category;
    public String description;
    //@Column(length=500)
    public String url;
    //debería ser Autor autor ya que estos tienen info
    public String author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column (columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date createdAt;

    

}


//ORM-- permite instanciar objeto de tipo MEME y con un método save() persiste. 
// el mejor ORM de JAVA es el JPA.