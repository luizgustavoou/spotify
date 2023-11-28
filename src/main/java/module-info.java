module imd.ufrn.br.spotify {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jbcrypt;

    opens imd.ufrn.br.spotify to javafx.fxml;
    exports imd.ufrn.br.spotify;
    exports imd.ufrn.br.spotify.controllers;
    opens imd.ufrn.br.spotify.controllers to javafx.fxml;
}