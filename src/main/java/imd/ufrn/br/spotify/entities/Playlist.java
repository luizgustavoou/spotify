package imd.ufrn.br.spotify.entities;

import java.util.List;
import java.util.UUID;

public class Playlist extends Entity {
    private String name;
    private UUID userId;

    public Playlist(UUID id, String name, UUID userId) {
        super(id);
        this.name = name;
        this.userId = userId;
    }

    public Playlist(String name, UUID userId) {
        super();
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {this.userId = userId;}

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }

//    @Override
//    public String toString() {
//        return name;
//    }
}
