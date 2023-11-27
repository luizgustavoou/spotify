package imd.ufrn.br.spotify.entities;

import java.util.UUID;

public class Entity {
    UUID id;

    public Entity(UUID id) {
        this.id = id;;
    }

    public Entity() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
