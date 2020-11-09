package com.myaudiolibrary.web.model;


import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album {


    //Attributs Privés
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer albumId;

    @Column(name = "Title")
    private String title;

    @Column(name = "ArtistId")
    private Integer artistId;



    //Constructeur
    public Album(Integer albumId, String title, Integer artistId) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
    }


    //Getter - Setter
    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}

