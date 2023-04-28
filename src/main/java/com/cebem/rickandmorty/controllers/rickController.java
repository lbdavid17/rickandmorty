package com.cebem.rickandmorty.controllers;



import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyService;
import com.cebem.rickandmorty.utils.Utils;
@RestController
@CrossOrigin(origins="*")  
public class rickController {

    @GetMapping("/")
    public String saluda(){
        return "Bienvenido a mi api rest de rickAndMorty";
    }

    // debemos devolver siempre String porque el cliente espera formato texto
    @GetMapping("/random")
    public String random(){
        return Math.round(Math.random()*10)+ "";
    }

    //parametro
    //http:localhost:8080/palindrome/word
    @GetMapping("/palindrome/{word}")
    public String palindrome (@PathVariable String word) {
        return (Utils.isPalindrome(word))?"Si es palindromo":"No es palindromo";
    }

    //queries--->
    //http:localhost:8080/add?n1=2&n2=4
    @GetMapping("/add")
    public String add( @RequestParam String n1, @RequestParam String n2){
        float resultado= Float.parseFloat(n1)+Float.parseFloat(n2);
        Object params[]={n1,n2,resultado};
        return MessageFormat.format("La suma de {0} más {1} es igual a {2}",params);
    }

    //http:localhost:8080/saveOnDisk
    //ACCEDER DESDE EL FORMULARIO
    @PostMapping ("/saveOnDisk")
    public String saveOnDisk(@RequestParam Map<String,String> body){
        String name= body.get("name");
        String price= body.get("price");

        String info= name+" "+price+"\n";

        try {
            Utils.writeOnDisk("datos.txt", info);

        } catch (IOException e) {
            return "Error al intentar escribir en el fichero";
        }

        return "Gracias por enviar el formulario. Guardado con éxito";

    }


    //ACCEDER DESDE EL FORMULARIO
    @DeleteMapping ("/removeFromDisk")
    public String removeFromDisk(){
            boolean resultado = Utils.deleteFromDisk("datos.txt");
            return resultado?"Se borró correctamente":"No se ha podido borrar";
    }



    //http:localhost:8080/square?number=2
    @GetMapping("/square")
    public String add( @RequestParam String number){

        try {
            float value= Float.parseFloat(number);
            return "El cuadrado de "+number+" es: "+value*value;
        } catch (NumberFormatException e) {
            return "El nº no es válido";
            
        }

    }

    /*ejemplos:
     * localhost:8080/profesores/342523252 parametro: muestra el profesor concreto
     * localhost:8080/profesores TODOS
     * localhost:8080/profesores?ojos=azules QUERY:aquellos con ojos azules
    */

    //http:localhost:8080/clear
    @DeleteMapping("/clear")
    public String clear(){
        try {
            Utils.clearFile("datos.txt");
            return "FICHERO VACIADO";

        } catch (IOException e) {
            return "Error al intentar borrar el contenido del fichero"+e.getMessage();
        }
    }

   //http:localhost:8080/products
   @GetMapping ("/products")
   public String getProducts(){
        try{
            return Utils.readFromDisk("datos.txt");
        }    catch (FileNotFoundException e) {
            return "No encontré el fichero";
        }catch(IOException e){
            return "fallo en la lectura del fichero"+e.getMessage();
        }

    }
   

   
    //http:localhost:8080/max?n1=2&n2=2&n3=3
    @GetMapping("/max")
    public String getMax(@RequestParam String n1, @RequestParam String n2, @RequestParam String n3){
                float f1= Float.parseFloat(n1);
                float f2= Float.parseFloat(n2);
                float f3= Float.parseFloat(n3);
                return "El mayor es: "+Utils.maxOfNElements(f1, f2, f3);


    }

    /*
     //http:localhost:8080/ponMayusculas
    @PostMapping("/ponMayusculas")
    public String ponMayusculas(@RequestParam Map<String,String> body){
        String text= body.get("text");
        String[] palabras= text.split(" ");
        Strinn capitalizedText="";
        for (String palabra:palabras){
        n capitalizedText+= palabra.substring(0, 1).toUpperCase()+palabra.substring(1)+" ";
        }
        return capitalizedText;
    }*/
    
    //http:localhost:8080/ponMayusculas/{texto}
    @GetMapping("/capitalize({text})")
    public String Capitalize(@PathVariable String text){
        return Utils.capitalizeText(text);

    }




    //http:localhost:8080/getRandomColor
    @GetMapping("/getRandomColor")
    public String getColor(){
        final int COLOR_COUNT=11;
        final String[] COLORS= {"negro", "azul", "marrón", "gris", "verde", "naranja", "rosa", "púrpura", "rojo", "blanco", "amarillo"};
        if (COLOR_COUNT>COLORS.length) throw new RuntimeException("LÍMITE DE COLORES SUPERADO");


        ArrayList<String> colores= new ArrayList<String>(Arrays.asList(COLORS));

        String finalColors="";
        for (int i=0;i<COLOR_COUNT;i++){
            
        int random1= Utils.getRandomValue(colores.size());
        finalColors+= colores.remove(random1)+" ";


        }

        return finalColors;
    }


    /* CREA UN ENDPOINT QUE TE DEVUELVA UN PERSONAJE RANDOM DE R&M */

    //el endpoint del api de rick&morty es: get https://rickandmortyapi.com/api/character/?

  
    @Autowired
    RickAndMortyService rickAndMortyService;

    @GetMapping("/rickandmorty/random")
    public String randomCharacter(){
        CharacterModel characterModel= rickAndMortyService.getCharacterRandom();

        return characterModel.name +"</br>"+"<img width='200' src='"+characterModel.image+"'/>";
    }

     //CREA UN ENDPOINT QUE TE DEVUELVA una página con muchos de golpe
    //el endpoint del api de rick&morty es: get https://rickandmortyapi.com/api/character/

    @GetMapping("/rickandmorty/all")
    public String allCharacters(){
        CharactersModel charactersModel= rickAndMortyService.getAllCharacters();
        String html="<html><head></head><body>";
        for (CharacterModel characterModel:charactersModel.results){
            html+= characterModel.name +"</br>"+"<img width='200' src='"+characterModel.image+"'/> <hr>";
        }
        html+="</body></html>";
        
        return html;
    }

    //otra versión (la adecuada): a través de un motor de plantillas usando thymeleaf (ver en webController)

   



}



    

