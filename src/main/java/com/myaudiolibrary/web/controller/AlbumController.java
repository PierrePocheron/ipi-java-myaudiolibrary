package com.myaudiolibrary.web.controller;


import com.myaudiolibrary.web.model.Album;
import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.AlbumRepository;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;



    //POST http://localhost:5366/albums
    //Ajout d'un album a un artist
    //gerer les exceptions




    // DELETE http://localhost:5366/albums/x
    //Suppression d'un album
    //gerer //500
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//204
    public void deleteAlbum(@PathVariable Long id)
    {
        //Gestion Erreur : id < 1
        if (id < 1)
        {
            throw new IllegalArgumentException("L'id de l'album doit être égal ou supérieur à 1" );
        }

        //Gestion Erreur :  404 not found
        if(albumRepository.findById(id) == null)
        {
            throw new EntityNotFoundException("Il n'y a pas d'album d'id : " + id);
        }
        albumRepository.deleteById(id);
    }


}
