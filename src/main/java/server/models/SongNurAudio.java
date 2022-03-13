package server.models;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nurAudio", types = Song.class) //song klasse aber wir wollen nur das audio; projection gut wenn du beim schicken manche daten nicht mitschicken möchtest
public interface SongNurAudio {

    String getAudio();

}
