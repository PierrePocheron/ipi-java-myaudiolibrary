package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.aspectj.util.GenericSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getArtistById(@PathVariable Long id, final ModelMap model){

        Optional<Artist> ArtistOptional = artistRepository.findById(id);
        if(ArtistOptional.isEmpty()){
            throw new EntityNotFoundException("L'employé d'identifiant " + id + " n'a pas été trouvé !");
        }

        model.put("artist", ArtistOptional.get());
        return "detailArtist";
    }



    @RequestMapping(method = RequestMethod.GET, value = "", params = "name")
    public String searchArtistByName(@RequestParam String name, final ModelMap model){
        Artist artist = artistRepository.findByName(name);
        //Ici il faudrait gérer l'erreur 404 !

        model.put("artist", artist);
        return "detailArtist";
    }


}
