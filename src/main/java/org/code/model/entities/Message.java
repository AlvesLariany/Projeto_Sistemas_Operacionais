package org.code.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalTime hour;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "email")
    private Users id_users;

    @ManyToOne
    @JoinColumn(name = "id_chanel", referencedColumnName = "id")
    private Chanel id_chanel;

    public Message() {}

    public Message(Long id, LocalTime hour, LocalDate date, Users id_users, Chanel id_chanel) {
        this.id = id;
        this.hour = hour;
        this.date = date;
        this.id_users = id_users;
        this.id_chanel = id_chanel;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", hour=" + hour +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Users getId_users() {
        return id_users;
    }

    public void setId_users(Users id_users) {
        this.id_users = id_users;
    }
}
