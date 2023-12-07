package imd.ufrn.br.spotify.stores;

public class CurrentPlaylist {
    private int index = -1;
    public int getIndex() {
        return index;
    }

    public void setIndex(int value) {
        this.index = value;
    }
    private CurrentPlaylist() {}
    static public CurrentPlaylist instance;
    static public CurrentPlaylist getInstance() {
        if(instance == null) {
            instance = new CurrentPlaylist();
        }
        return instance;
    }
}

