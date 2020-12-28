package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Album;
import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;


    @RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView newAlbum(final ModelMap model, Album album)
    {
        //VERIFICATIONSS 404 etc
        albumRepository.save(album);
        model.put("artist",album.getArtist());
        return new RedirectView("/artists/"+album.getArtist().getArtistId());
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{albumId}")
    public RedirectView deleteAlbum(@PathVariable("albumId") Long albumId, final ModelMap modelMap, Artist artist){
        System.out.println("Suppression de l album : " + albumId);
        albumRepository.deleteById(albumId);

        return new RedirectView("/artists/"+artist.getArtistId());
    }

}
