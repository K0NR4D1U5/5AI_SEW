package server.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity //Definiert als Entity
@Getter //Vorgefertigte Getter
@Setter //Vorgefertigte Setter
public class Song extends Persistent { //Persistent ist die Klasse mit den Attributen die alle Entities haben sollen

    @NotBlank(message = "Ein Lied benötigt einen Titel.") //darf null sein aber nicht leerer string
    @Size(min = 4) //mind. 4 chars
    @Column(unique = true) //wenn datenbank hast, es soll 2 mal den selben titel verhindern
    private String title;

    @ManyToOne(optional = false) //n zu 1 wie sql
    private Artist artist;

    @ElementCollection //MUSS wenn du eine collection hast wie zb Set<String>
    @NotEmpty //darf nicht leer sein
    private Set<@NotBlank String> genres;

    private String duration;

    // Audio-Track als Data-URL
    @Lob //Lob halt. dieser Datastring statt dateiserver
    private String audio;

    private String filename;

    @Transient //idk
    private Integer size;

}
