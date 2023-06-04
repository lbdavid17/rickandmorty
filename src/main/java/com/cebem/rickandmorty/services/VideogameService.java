package com.cebem.rickandmorty.services;

import java.util.ArrayList;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebem.rickandmorty.models.VideogameModel;
import com.cebem.rickandmorty.repositories.VideogameRepository;



@Service
public class VideogameService {
    @Autowired
    VideogameRepository videogameRepository;

    public ArrayList<VideogameModel> getAll(){
        return (ArrayList<VideogameModel>) videogameRepository.findAll();
    }
    public VideogameModel getById(long id){
        if(videogameRepository.findById(id).isPresent())
            return videogameRepository.findById(id).get();
        else
            return null;
    }
    public VideogameModel create(VideogameModel videogame){
        return videogameRepository.save(videogame);
    }
    public boolean delete(long id){
        try{
            videogameRepository.deleteById(id);
            return true;
        }catch(IllegalArgumentException ex){
            return false;
        }
    }

}