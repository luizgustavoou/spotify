package imd.ufrn.br.spotify.entities;

import java.util.List;
import java.util.UUID;

public class Playlist {
    private UUID id;
    private String name;
    private List<Song> songs;
    private UUID userId;

    public Playlist(UUID id, String name, List<Song> songs, UUID userId) {
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.userId = userId;
    }

    public Playlist(String name, List<Song> songs, UUID userId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.songs = songs;
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

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                ", userId=" + userId +
                '}';
    }
}
