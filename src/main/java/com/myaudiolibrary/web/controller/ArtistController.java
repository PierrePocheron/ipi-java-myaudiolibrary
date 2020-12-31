package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.aspectj.util.GenericSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    ///////////////////////////
    ////    Detail Artist
    ///////////////////////////
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getArtistById(@PathVariable Long id, final ModelMap model){

        Optional<Artist> artistOptional = artistRepository.findById(id);
        //Gestion Erreur
        if(artistOptional.isEmpty()){
            throw new EntityNotFoundException("L'artist d'identifiant " + id + " n'a pas été trouvé !");
        }

        Artist artist = artistOptional.get();
        model.put("artist",artist);
        return "detailArtist";
    }

    ///////////////////////////
    ////    Afficher Artist by Name
    ///////////////////////////
    @RequestMapping(method = RequestMethod.GET, value = "", params = {"name"})
    public String searchArtistByName(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "10") Integer size,
                                     @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
                                     @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection,
                                     final ModelMap model)
    {
        //Gestion Erreur
        if (page < 0)
        {
            throw new IllegalArgumentException("Le paramètre page doit etre positif");
        }
        if (size <= 0 || size > 50)
        {
            throw new IllegalArgumentException("Le paramètre size doit être compris entre 0 et 50");
        }
        if(!("ASC".equalsIgnoreCase(sortDirection)) && !("DESC".equalsIgnoreCase(sortDirection)))
        {
            throw new IllegalArgumentException("Le paramètre sortDirection doit valoir ASC ou DESC");
        }

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        Page<Artist> artistList= artistRepository.findAllByNameContaining(name, pageRequest);

        model.put("size", size);
        model.put("sortProperty", sortProperty);
        model.put("sortDirection", sortDirection);
        model.put("pageNumber", page + 1);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("start", page * size + 1);
        model.put("end", (page)*size + artistList.getNumberOfElements());
        model.put("artists", artistList);

        return "listeArtists";
    }



    ///////////////////////////
    ////    Afficher All Artist
    ///////////////////////////
    @RequestMapping(method = RequestMethod.GET, value = "")
    public String listArtists(final ModelMap model,
                              @RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "10") Integer size,
                              @RequestParam(defaultValue = "name") String sortDirection,
                              @RequestParam(defaultValue = "ASC") String sortProperty)
    {
        //Gestion Erreur
        if (page < 0)
        {
            throw new IllegalArgumentException("Le paramètre page doit etre positif");
        }
        if (size <= 0 || size > 50)
        {
            throw new IllegalArgumentException("Le paramètre size doit être compris entre 0 et 50");
        }
        if(!("ASC".equalsIgnoreCase(sortDirection)) && !("DESC".equalsIgnoreCase(sortDirection)))
        {
            throw new IllegalArgumentException("Le paramètre sortDirection doit valoir ASC ou DESC");
        }

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        Page<Artist> pageArtist = artistRepository.findAll(pageRequest);

        model.put("artists", pageArtist);
        model.put("pageNumber", page + 1);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("start", page * size + 1);
        model.put("end", (page) * size + pageArtist.getNumberOfElements());

        return "listeArtists";
    }


    ///////////////////////////
    ////   Créer Artist
    ///////////////////////////
    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newArtist(final ModelMap model, Artist artist)
    {
        model.put("artist",artist);
        return "detailArtist";
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "",
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveArtist(final ModelMap model, Artist artist)
    {
        //Gestion Erreur
        if(artistRepository.existsByNameIgnoreCase(artist.getName()))
        {
            throw new EntityExistsException("Il y a deja un artist qui s'appel : " + artist.getName());
        }
        artistRepository.save(artist);
        model.put("artist",artistRepository.findByName(artist.getName()));
        return "detailArtist";
    }


    ///////////////////////////
    ////   Modifier Artist
    ///////////////////////////
    @RequestMapping(method = RequestMethod.POST, value = "/checkUpdateArtist", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView checkUpdateArtist(Artist artist, final ModelMap model)
    {
        //Gestion Erreur
        if(artistRepository.existsByNameIgnoreCase(artist.getName()))
        {
            throw new EntityExistsException("L'artist existe déjà !");
        }
        return updateArtist(artist, model);
    }

    private RedirectView updateArtist(Artist artist, ModelMap model)
    {
        //Gestion Erreur
        if(artistRepository.existsByNameIgnoreCase(artist.getName()))
        {
            throw new EntityExistsException("L'artist existe déjà !");
        }
        artist = artistRepository.save(artist);
        model.put("Artist", artist);
        return new RedirectView("/artists/"+ artist.getArtistId());
    }



    ///////////////////////////
    ////   Supprimer Artist
    ///////////////////////////
    @RequestMapping(method = RequestMethod.GET, value = "/{artistId}/delete")
    public RedirectView deleteArtist(@PathVariable(value = "artistId") Long artistId){
        //Gestion Erreur
        if (artistId < 1)
        {
            throw new IllegalArgumentException("L'id de l'artiste doit être supérieur à 0" );
        }

        Optional<Artist> optionalArtist = artistRepository.findById(artistId);
        //Gestion Erreur
        if(optionalArtist.isEmpty())
        {
            throw new EntityNotFoundException("L'artist d'indentifiant : " + artistId + " n'a pas été trouvé");
        }

        artistRepository.deleteById(artistId);
        return new RedirectView("/artists?page=0&size=10&sortProperty=name&sortDirection=ASC");
    }
}
