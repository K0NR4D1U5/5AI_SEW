package server.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
public class Artist extends Persistent {

    @Column(unique = true)
    @NotBlank
    private String name;

    // Data-URL des Bildes
    @Lob
    private String image;

    @OneToMany(mappedBy = "artist") //1 zu n; verbunden mit Song.java:private Artist artist; bzw. assign this song to this artist
    private Set<Song> songs;

}
