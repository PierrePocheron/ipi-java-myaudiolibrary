package com.myaudiolibrary.web.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "album")
public class Album {


    //Attributs Privés
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long albumId;

    @Column(name = "Title")
    private String title;

    @Column(name = "ArtistId")
    private Long artistId;



    //Constructeur
    public Album() {
    }
    public Album(Long albumId, String title, Long artistId) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
    }




    //Getter - Setter
    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return Objects.equals(getAlbumId(), album.getAlbumId()) &&
                Objects.equals(getTitle(), album.getTitle()) &&
                Objects.equals(getArtistId(), album.getArtistId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAlbumId(), getTitle(), getArtistId());
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}

