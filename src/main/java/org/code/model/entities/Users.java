package org.code.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Users implements Serializable {
    @Id
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String image_path;

    @Column(nullable = true)
    private Long codEspecial;

    @OneToMany(mappedBy = "id_users")
    private Set<Message> messageSet = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_in_chanel",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chanel_id")
    )
    private Set<Chanel> chanelSet = new LinkedHashSet<>();

    public Users() {}

    public Users(String email, String name, String password, String image_path, Long codEspecial) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.image_path = image_path;
        this.codEspecial = codEspecial;
    }

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", image_path='" + image_path + '\'' +
                ", codEspecial=" + codEspecial +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users user = (Users) o;

        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Long getCodEspecial() {
        return codEspecial;
    }

    public Set<Message> getMessageSet() {
        return messageSet;
    }

    public void setMessageSet(Set<Message> messageSet) {
        this.messageSet = messageSet;
    }

    public Set<Chanel> getChanelSet() {
        return chanelSet;
    }

    public void setChanelSet(Set<Chanel> chanelSet) {
        this.chanelSet = chanelSet;
    }

    public void setOneChanel(Chanel chanel) {
        this.chanelSet.add(chanel);
    }
}
