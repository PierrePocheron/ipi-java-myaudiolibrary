package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccueilController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(final ModelMap m)
    {

        m.put("nbArtist", artistRepository.count());
        return "accueil";
    }


}
