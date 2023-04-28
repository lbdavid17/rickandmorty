package com.cebem.rickandmorty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.utils.Utils;

@Service
public class RickAndMortyService {

    // inyectado
    @Autowired
    RestTemplate restTemplate;


    final static String BASE_API= "https://rickandmortyapi.com/api";
    public CharacterModel getCharacterRandom() {
        // en realidad deberíamos consultar el nº de personajes por si en algún momento
        // hay más o menos
        final int TOTAL_CHARACTERS = 826;

        int random = Utils.getRandomValue(TOTAL_CHARACTERS - 1) + 1;

        String url = BASE_API+"/character/" + random;

        // librerías para hacer peticiones por ejemplo a apis
        // javascript: fetch, axios,... java: restTemplate,...

        /* RestTemplate restTemplate= new RestTemplate(); */
        // es mejor no hacerlo así ^ porque lleva muchos recursos, dejamos que lo dé
        // instanciado springboot con el autowired (inyeccion
        // de dependecias)

        //recogemos los datos con el modelo
        CharacterModel datos = restTemplate.getForObject(url, CharacterModel.class);
        return datos;
    }

    public CharactersModel getAllCharacters(){
        String url = BASE_API+"/character";
        CharactersModel datos = restTemplate.getForObject(url, CharactersModel.class);
        return datos;

    }

    public int getCharactersCount(){
        String url = BASE_API+"/character";
        CharactersModel datos = restTemplate.getForObject(url, CharactersModel.class);
        return datos.info.count;
    }
   




}
