module imd.ufrn.br.spotify {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jbcrypt;
    requires javafx.media;

    opens imd.ufrn.br.spotify to javafx.fxml;
    exports imd.ufrn.br.spotify.back.controllers;
    exports imd.ufrn.br.spotify.back.services.song;
    exports imd.ufrn.br.spotify.back.services.user;
    exports imd.ufrn.br.spotify.back.services.folder;
    exports imd.ufrn.br.spotify.back.services.playlist;
    exports imd.ufrn.br.spotify.exceptions;
    exports imd.ufrn.br.spotify.back.entities;
    exports imd.ufrn.br.spotify.front.stores;
    opens imd.ufrn.br.spotify.back.controllers to javafx.fxml;
    exports imd.ufrn.br.spotify.front.utils;
    opens imd.ufrn.br.spotify.front.utils to javafx.fxml;
    exports imd.ufrn.br.spotify.front;
    opens imd.ufrn.br.spotify.front to javafx.fxml;
    exports imd.ufrn.br.spotify.front.controllers;
    opens imd.ufrn.br.spotify.front.controllers to javafx.fxml;
    exports imd.ufrn.br.spotify.front.lib;
    opens imd.ufrn.br.spotify.front.lib to javafx.fxml;
}