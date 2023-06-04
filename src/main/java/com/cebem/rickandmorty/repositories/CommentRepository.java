package com.cebem.rickandmorty.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cebem.rickandmorty.models.CommentModel;


//para consultas mas elaboradas, usar el JPARepository en vez del CrudRepository (ej de este caso: ordenar por nº de comentarios cada videojuego)
//
@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    ArrayList<CommentModel> findByVideogameIdOrderByCreatedAtDesc(Long videogameId);

    //otro ejemplo: findbyVideogameIOrderByText ORDENA ALFABETICAMENTE
}

