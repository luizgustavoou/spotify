package imd.ufrn.br.spotify.entities;

import java.util.UUID;

public class Folder {
    UUID id;
    String path;
    UUID playlistId;

    public Folder(UUID id, String path, UUID playlistId) {
        this.id = id;
        this.path = path;
        this.playlistId = playlistId;
    }

    public Folder(String path, UUID playlistId) {
        this.id = UUID.randomUUID();
        this.path = path;
        this.playlistId = playlistId;
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

    public UUID getplaylistId() {
        return playlistId;
    }

    public void setplaylistId(UUID playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", playlistId=" + playlistId +
                '}';
    }
}
