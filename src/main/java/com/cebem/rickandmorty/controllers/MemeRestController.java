package com.cebem.rickandmorty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.rickandmorty.services.MemeService;

@RestController
public class MemeRestController {
    @Autowired
    MemeService memeService;
    
    @DeleteMapping("/memes/{id}")
    public String memesDelete(@PathVariable String id){
      

        boolean result= memeService.deleteMeme(Long.parseLong(id));
        if (result) return "OK. Borrado correctamente";
        return "ERROR al borrar el meme";
    }
    
}