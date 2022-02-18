package com.example.vidT.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Заполните")
    private String name;
    @NotNull(message = "Заполните")
    @Length(max = 4096, message = "Слишком длинный текст")
    private String textm;
    private long timer1;
    private boolean isAdminsend;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String filename;

    public Video() {
    }

    public Video(String name, String textm, User user) {
        this.name = name;
        this.textm = textm;
        this.author = user;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public boolean isAdminsend() {
        return isAdminsend;
    }

    public void setAdminsend(boolean adminsend) {
        isAdminsend = adminsend;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getTimer1() {
        return timer1;
    }

    public void setTimer1(long timer1) {
        this.timer1 = timer1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextm() {
        return textm;
    }

    public void setTextm(String textm) {
        this.textm = textm;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
