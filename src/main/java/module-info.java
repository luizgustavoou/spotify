module imd.ufrn.br.spotify {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jbcrypt;

    opens imd.ufrn.br.spotify to javafx.fxml;
    exports imd.ufrn.br.spotify;
    exports imd.ufrn.br.spotify.controllers;
    exports imd.ufrn.br.spotify.services.song;
    exports imd.ufrn.br.spotify.services.user;
    exports imd.ufrn.br.spotify.services.folder;
    exports imd.ufrn.br.spotify.services.playlist;
    exports imd.ufrn.br.spotify.exceptions;
    exports imd.ufrn.br.spotify.entities;
    opens imd.ufrn.br.spotify.controllers to javafx.fxml;
}