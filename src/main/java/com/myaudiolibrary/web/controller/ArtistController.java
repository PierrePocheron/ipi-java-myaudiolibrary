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

import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getArtistById(@PathVariable Long id, final ModelMap model){

        Optional<Artist> artistOptional = artistRepository.findById(id);
        if(artistOptional.isEmpty()){
            throw new EntityNotFoundException("L'employé d'identifiant " + id + " n'a pas été trouvé !");
        }

        Artist artist = artistOptional.get();
        model.put("artist",artist);
        return "detailArtist";
    }



    @RequestMapping(method = RequestMethod.GET, value = "", params = "name")
    public String searchArtistByName(@RequestParam String name, final ModelMap model){
        Artist artist = artistRepository.findByName(name);
        //Ici il faudrait gérer l'erreur 404 !

        model.put("artist", artist);
        return "detailArtist";
    }


    @RequestMapping(method = RequestMethod.GET, value = "")
    public String listArtists(final ModelMap model,
                              @RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "10") Integer size,
                              @RequestParam(defaultValue = "name") String sortDirection,
                              @RequestParam(defaultValue = "ASC") String sortProperty)
    {
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


    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newArtist(final ModelMap model)
    {
        System.out.println("on passe a la creation d'artist");
        Artist artist = new Artist();
        model.put("artist",artist);
        return "detailArtist";
    }




    @RequestMapping(method = RequestMethod.POST,
                    value = "",
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveArtist(final ModelMap model, Artist artist)
    {
        System.out.println("on sauve l'artiste ");
        System.out.println("nom de l'artiste "+artist.getName());


        artistRepository.save(artist);
        model.put("artist",artistRepository.findByName(artist.getName()));
        return "detailArtist";
    }



}
