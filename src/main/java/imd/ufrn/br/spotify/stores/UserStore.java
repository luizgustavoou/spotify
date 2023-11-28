package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.User;

public class UserStore {
    private User user;
    private UserStore() {}
    static public UserStore instance;
    static public UserStore getInstance() {
        if(instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
