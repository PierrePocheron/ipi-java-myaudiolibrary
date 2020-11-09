package com.myaudiolibrary.web.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "artist")
public class Artist {


    //Attributs Privés
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artistId;

    @Column(name = "Name")
    private String name;


    //Constructeur
    public Artist() {

    }
    public Artist(Long artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }


    //Getter - Setter
    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(getArtistId(), artist.getArtistId()) &&
                Objects.equals(getName(), artist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtistId(), getName());
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                '}';
    }
}
