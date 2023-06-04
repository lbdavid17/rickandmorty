package com.cebem.rickandmorty.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cebem.rickandmorty.models.CommentModel;
import com.cebem.rickandmorty.models.VideogameModel;
import com.cebem.rickandmorty.services.CommentService;
import com.cebem.rickandmorty.services.VideogameService;


@Controller
public class VideogameWebController {
    @Autowired
    VideogameService videogameService;

    @Autowired
    CommentService commentService;

@RequestMapping("/game/{id}")
  public String gameTemplate(Model modelo, @PathVariable String id){
    VideogameModel game = videogameService.getById(Long.parseLong(id));
    ArrayList<CommentModel> comments = commentService.getAllByVideogameId(Long.parseLong(id));
    modelo.addAttribute("game", game);
    modelo.addAttribute("comments", comments);

    return "game";
  }


  @RequestMapping("/addgame")
  public String addGame(){
    return "gameAddForm";
  }

  @RequestMapping("/games")
  public String videogames(Model modelo){
    ArrayList<VideogameModel> games = videogameService.getAll();
    modelo.addAttribute("games", games);
    return "games";
  }

}