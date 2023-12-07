package imd.ufrn.br.spotify.stores;

public class CurrentSong {
    private int index = -1;
    public int getIndex() {
        return index;
    }

    public void setIndex(int value) {
        this.index = value;
    }
    private CurrentSong() {}
    static public CurrentSong instance;
    static public CurrentSong getInstance() {
        if(instance == null) {
            instance = new CurrentSong();
        }
        return instance;
    }


}