package org.code.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

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
    @Lob
    @Column(nullable = false, columnDefinition = "MEDIUMBLOB")
    private byte[] image;

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

    public Users(String email, String name, String password, byte[] image, Long codEspecial) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.image = image;
        this.codEspecial = codEspecial;
    }

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
