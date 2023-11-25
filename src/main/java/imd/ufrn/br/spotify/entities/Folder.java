package imd.ufrn.br.spotify.entities;

import java.util.UUID;

public class Folder {
    UUID id;
    String path;

    public Folder(UUID id, String path) {
        this.id = id;
        this.path = path;
    }

    public Folder(String path) {
        this.id = UUID.randomUUID();
        this.id = id;
        this.path = path;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
