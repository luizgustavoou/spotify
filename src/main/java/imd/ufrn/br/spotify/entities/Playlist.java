package imd.ufrn.br.spotify.entities;

import java.util.List;
import java.util.UUID;

public class Playlist {
    private UUID id;
    private String name;
    private UUID userId;

    public Playlist(UUID id, String name, UUID userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public Playlist(String name, UUID userId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}
