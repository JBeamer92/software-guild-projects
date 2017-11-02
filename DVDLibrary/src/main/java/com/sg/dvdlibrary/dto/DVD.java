/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author jared
 */
public class DVD {
    
    private String title;
    private LocalDate releaseDate;
//    private String releaseDate;
    private String mpaaRating;
    private String director;
    private String studio;
    private String userNote;
    
    // CONSTRUCTOR
    public DVD(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + " |Release Date: " + releaseDate.toString()
                + " |Rating: " + mpaaRating + " |Director: " + director
                + " |Studio: " + studio + " |User note: " + userNote;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.releaseDate);
        hash = 29 * hash + Objects.hashCode(this.mpaaRating);
        hash = 29 * hash + Objects.hashCode(this.director);
        hash = 29 * hash + Objects.hashCode(this.studio);
        hash = 29 * hash + Objects.hashCode(this.userNote);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userNote, other.userNote)) {
            return false;
        }
        return true;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
    
}
