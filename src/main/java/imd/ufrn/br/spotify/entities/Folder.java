package imd.ufrn.br.spotify.entities;

import java.util.UUID;

public class Folder extends Entity{
    String path;
    UUID playlistId;

    public Folder(UUID id, String path, UUID playlistId) {
        super(id);
        this.path = path;
        this.playlistId = playlistId;
    }

    public Folder(String path, UUID playlistId) {
        super();
        this.path = path;
        this.playlistId = playlistId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UUID getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(UUID playlistId) {
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
