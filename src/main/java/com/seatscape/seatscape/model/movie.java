package com.seatscape.seatscape.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class movie {
    @Id
    private Integer movieid;
    private String title;
    private String description;
    private Integer duration;
    private String lang;
    private LocalDate releasedate;
    private String genre;
    @Override
    public String toString() {
        return "movie{" +
                "movieid=" + movieid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", lang='" + lang + '\'' +
                ", releasedate=" + releasedate +
                ", genre='" + genre + '\'' +
                '}';
    }
}
