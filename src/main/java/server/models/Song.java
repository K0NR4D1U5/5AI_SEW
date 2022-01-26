package server.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
public class Song extends Persistent {

    @NotBlank(message = "Ein Lied benötigt einen Titel.")
    @Size(min = 4)
    @Column(unique = true)
    private String title;

    @NotBlank
    private String artist;

    @ElementCollection
    @NotEmpty
    private Set<@NotBlank String> genres;

    private String duration;

    // Audio-Track als Data-URL
    @Lob
    private String audio;

    private String filename;

    @Transient
    private Integer size;

}
