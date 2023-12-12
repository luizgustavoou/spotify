package imd.ufrn.br.spotify.front.stores;

import imd.ufrn.br.spotify.back.entities.User;

import java.util.UUID;

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

    public UUID getId() {
        return this.user.getId();
    }

    public String getFullName() {
        return this.user.getFullName();
    }

    public Boolean getIsVip() {
        return this.user.getVip();
    }
}
