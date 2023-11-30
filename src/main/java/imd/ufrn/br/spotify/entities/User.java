package imd.ufrn.br.spotify.entities;

import java.util.UUID;

public class User extends Entity {
    private String username;
    private String password;
    private String fullName;
    private Boolean isVip;
    private UUID idPlaylistPadrao;

    public User(UUID id, String username, String password, String fullName, Boolean isVip, UUID idPlaylistPadrao) {
        super(id);
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.isVip = isVip;
        this.idPlaylistPadrao = idPlaylistPadrao;
    }

    public User(String username, String password, String fullName, Boolean isVip, UUID idPlaylistPadrao) {
        super();
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.isVip = isVip;
        this.idPlaylistPadrao = idPlaylistPadrao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public UUID getIdPlaylistPadrao() {return idPlaylistPadrao;}
    public void setIdPlaylistPadrao(UUID idPlaylistPadrao) {this.idPlaylistPadrao = idPlaylistPadrao;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", isVip=" + isVip + '\'' +
                ", idPlaylistPadrao=" + idPlaylistPadrao +
                '}';
    }
}
