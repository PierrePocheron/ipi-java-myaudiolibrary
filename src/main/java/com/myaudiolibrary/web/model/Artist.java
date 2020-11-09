package com.myaudiolibrary.web.model;


import javax.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {


    //Attributs Privés
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer artistId;

    @Column(name = "Name")
    private String name;


    //Constructeur
    public Artist() {

    }
    public Artist(Integer artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }


    //Getter - Setter
    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
