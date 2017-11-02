/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author jared
 */

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sighting {
    
    private int id;
    private Location location;
    private LocalDate date;
    private List<Hero> heroes = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.location);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.heroes);
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
        final Sighting other = (Sighting) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.heroes, other.heroes)) {
            return false;
        }
        return true;
    }

    
}
