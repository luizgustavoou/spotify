package imd.ufrn.br.spotify.front.helpers.HomeScreenStrategy;

import javafx.scene.Node;

public class HomeScreenContext {
    private HomeScreenStrategy homeScreenStrategy;

    public void setHomeScreenStrategy(HomeScreenStrategy homeScreenStrategy) {
        this.homeScreenStrategy = homeScreenStrategy;
    }

    public void execute(Node node) {
        this.homeScreenStrategy.execute(node);
    }
}
