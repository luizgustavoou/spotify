package imd.ufrn.br.spotify.front.helpers.HomeScreenStrategy;

import imd.ufrn.br.spotify.front.helpers.HomeScreenStrategy.HomeScreenStrategy;
import imd.ufrn.br.spotify.front.utils.Navigator;
import imd.ufrn.br.spotify.front.utils.PathViews;
import imd.ufrn.br.spotify.front.utils.TitleViews;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class FreeHomeScreenStrategy implements HomeScreenStrategy {

    private final Navigator navigator;
    public FreeHomeScreenStrategy() {
        this.navigator = Navigator.getInstance();


    }
    @Override
    public void execute(Node node) {
        try {
            Stage stage = this.navigator.configure(node, TitleViews.FREE_HOME_VIEW, PathViews.FREE_HOME_VIEW);
            this.navigator.execute(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
