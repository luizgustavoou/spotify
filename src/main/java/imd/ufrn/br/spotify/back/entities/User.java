package imd.ufrn.br.spotify.back.entities;

import java.util.UUID;

public class User extends Entity {
    private String username;
    private String password;
    private String fullName;
    private Boolean isVip;

    public User(UUID id, String username, String password, String fullName, Boolean isVip) {
        super(id);
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.isVip = isVip;
    }

    public User(String username, String password, String fullName, Boolean isVip) {
        super();
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.isVip = isVip;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", isVip=" + isVip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (isVip == null) {
            if (other.isVip != null)
                return false;
        } else if (!isVip.equals(other.isVip))
            return false;
        return true;
    }
}
