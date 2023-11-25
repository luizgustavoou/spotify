package imd.ufrn.br.spotify.entities;

import java.util.UUID;

public class Song {

    private UUID id;
    private String name;
    private String path;
    private UUID playlistId;


    public Song(String name, String path, UUID playlistId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.path = path;
        this.playlistId = playlistId;
    }

    public Song(UUID id, String name, String path, UUID playlistId) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.playlistId = playlistId;
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
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", playlistId=" + playlistId +
                '}';
    }
}
