package com.cebem.rickandmorty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyService;



@Controller
public class WebController {
    
    @Autowired
    RickAndMortyService rickAndMortyService;

    //es un controlador, no un rest: no se usa get, put,...
    @RequestMapping("/rickandmorty/allTemplate")
    public String allCharactersTemplate( Model modelo){
       CharactersModel charactersModel= rickAndMortyService.getAllCharacters();
        
        modelo.addAttribute("creator", "Creado por DAVID");
        modelo.addAttribute("characters", charactersModel.results);
        return "rickandmortyall";
    }

     //queremos mostrar en una web el total 
    //de personajes que tenemos (frase "TOTAL DE PERSONAJES: 8234")
    //centrado en pantalla. Color verde.
    //usa el motor de plantillas

    @RequestMapping("/rickandmorty/count")
    public String countCharacters( Model modelo){
        int count= rickAndMortyService. getCharactersCount();
        
        modelo.addAttribute("count", count);
        return "rickandmortycount";
    }



}
