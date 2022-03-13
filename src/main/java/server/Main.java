package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO Tu parallel zu /src/main/java ein resources-Ordner wohin das import sql hinein muss.

/**
 * Startet den Server für statische Inhalte und für das REST-API.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
