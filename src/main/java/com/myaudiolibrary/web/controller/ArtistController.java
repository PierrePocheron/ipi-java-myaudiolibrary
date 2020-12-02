package com.myaudiolibrary.web.controller;


import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.AlbumRepository;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    //GET http://localhost:5366/artists/1
    //Recherche artist par son ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Artist> getArtist(@PathVariable (value = "id") Long id)
    {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if(optionalArtist.isEmpty())
        {
            //Gestion erreur 404
            throw new EntityNotFoundException("L'artiste d'indentifiant : " + id + " n'a pas été trouvé");
        }
        return optionalArtist;
    }

    //GET http://localhost:5366/artists?name=aerosmith
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"name"})
    public Artist findArtistByName(@RequestParam(value = "name") String name)
    {
        Artist artist = artistRepository.findByName(name);
        //404
        if(artist == null)
        {
            throw new EntityNotFoundException("L'artiste qui s'appel : " + name + " n'a pas été trouvé.");
        }
        return artist;
    }

    // GET http://localhost:5366/artists?page=0&size=10&sortProperty=name&sortDirection=ASC
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"page"})
    public Page<Artist> listArtists(
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="size", defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "name") String sortProperty,
            @RequestParam(value="sortDirection", defaultValue = "ASC") String sortDirection){
        if (page < 0)
        {
            //400
            throw new IllegalArgumentException("Le paramètre page doit etre positif ou nul !");
        }
        if (size <= 0 || size > 50)
        {
            throw new IllegalArgumentException("Le paramètre size doit être compris entre 0 et 50");
        }
        if(!"ASC".equalsIgnoreCase(sortDirection) && !"DESC".equalsIgnoreCase(sortDirection))
        {
            throw new IllegalArgumentException("Le paramèter sortDirection doit valoir ASC ou DESC");
        }

        return artistRepository.findAll(PageRequest.of(page,size,
                Sort.Direction.fromString(sortDirection),
                sortProperty));

    }

    //Création d'un Artiste
    //Retourne un Artiste
    // POST http://localhost:5366/artists
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    //Renvoie un code Http 201 pour la création
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist)
    {
        if(artistRepository.findById(artist.getArtistId()) !=null)
        {
            throw new EntityExistsException("Il y a deja un artist d'id : " + artist.getArtistId());
        }
        return artistRepository.save(artist);
    }


    //POST http://localhost:5366/artists
    //Modification d'un artist
    //Retourne un Artiste
    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist updateArtiste(@RequestBody Artist artist,
                                @PathVariable Integer id)
    {
        //Gestion Erreur : 404  Name non renseigné
        if(artist.getName().isBlank())
        {
            throw new IllegalArgumentException("Le paramètre name doit être renseigné !");
        }
        return artistRepository.save(artist);
    }



    //DELETE http://localhost:5366/artists/275
    //Suppression d'un artist
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//204
    public void deleteArtist(@PathVariable Long id)
    {
        //Gestion Erreur : id < 1
        if (id < 1)
        {
            throw new IllegalArgumentException("L'id de l'artiste doit être égal ou supérieur à 1" );
        }

        //Gestion Erreur :  404 not found
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if(optionalArtist.isEmpty())
        {
            throw new EntityNotFoundException("L'artiste d'indentifiant : " + id + " n'a pas été trouvé");
        }
        artistRepository.deleteById(id);
    }

    // ADD Tymeleaf routes au projet
//    @Controller
//    @RequestMapping(value = "/thymeleaf")
//    public class ThymeleafController
//    {
//        // toute les @request ici auraont dabord Thymeleaf/le-reste-de-la-route
//    }

}