package com.myaudiolibrary.web.controller;


import com.myaudiolibrary.web.repository.AlbumRepository;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    //POST http://localhost:5366/albums
    //Ajout d'un album


    // DELETE http://localhost:5366/albums/x
    //Suppression d'un album
    //gerer les cascades
    //gerer les exceptions


}
