package com.cebem.rickandmorty.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cebem.rickandmorty.models.VideogameModel;

@Repository
public interface VideogameRepository extends CrudRepository<VideogameModel, Long> {
    
}

