package com.cebem.rickandmorty.controllers;

//import java.lang.reflect.Array;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.rickandmorty.models.VideogameModel;
import com.cebem.rickandmorty.services.VideogameService;

@RestController
@CrossOrigin( origins = "*" )
public class VideogameController {
    
    @Autowired
    VideogameService videogameService;

    @GetMapping("/api/videogames")
    public String videogames(){
        String tmp = "";
        for(VideogameModel game: videogameService.getAll()){
            tmp += game.getName();
            tmp += "<br/>";
        }
        return tmp;
    }

    @GetMapping("/api/videogames/{id}")
    public String videogame(@PathVariable String id){
        VideogameModel game = videogameService.getById(Long.parseLong(id));
        if(game==null) return "No existe ese videojuego";
        
        String tmp = "";
        tmp+= game.getId();
        tmp += "<br/>";
        tmp+= game.getName();
        tmp += "<br/>";
        tmp+= game.getRate();
        return tmp;
    }

    @PostMapping("/api/videogames")
    public String videogameAdd(@RequestParam Map<String, String> body){
        String name = body.get("name");
        String description = body.get("description");
        String categoria = body.get("categoria");
        String rate = body.get("rate");
        
        VideogameModel game = new VideogameModel();
        game.setName(name);
        game.setDescription(description);
        game.setCategory(categoria);
        game.setRate(Integer.parseInt(rate));

        videogameService.create(game);

        return "Videogame creado. Gracias";
    }

    @DeleteMapping("/videogames/{id}")
    public String videogameDelete(@PathVariable String id){
        boolean result = videogameService.delete(Long.parseLong(id));
        if(result){
            return "OK borrado correcto";
        }else{
            return "ERROR al borrar el videogame";
        }
    }

}