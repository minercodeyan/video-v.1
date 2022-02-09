package com.example.vidT.models;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Заполните")
    private String filename;
    @NotNull(message = "Заполните")
    @Length(max=4096, message = "Слишком длинный текст")
    private String textm;
    private long timer1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User author;

    public Video() {
    }



    public Video(String filename, String textm, User user) {
        this.filename = filename;
        this.textm = textm;
        this.author = user;
    }

    public String getAuthorName(){

        return author != null ? author.getUsername() : "<none>";
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
