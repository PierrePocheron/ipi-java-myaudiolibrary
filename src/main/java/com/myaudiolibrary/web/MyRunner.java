package com.myaudiolibrary.web;

import com.myaudiolibrary.web.model.Album;
import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.AlbumRepository;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("================================================================================\n");
        System.out.println("Salut les devs, Bienvenue dans mon Application Web Java ! ");
        System.out.println("Le serveur a bien démarré correctement ");
        System.out.println("Accueil : http://localhost:5366");
        System.out.println("Page de test : http://localhost:5366/test");
        System.out.println("Detail d'un artist : http://localhost:5366/artists/1");
        System.out.println("Liste des artists : http://localhost:5366/artists?page=0&size=10&sortProperty=name&sortDirection=ASC");
        System.out.println("Création d'un artist : http://localhost:5366/artists/new\n");
        System.out.println("================================================================================\n");
    }
    public static void print(Object t) {
        System.out.println(t);
    }
}
