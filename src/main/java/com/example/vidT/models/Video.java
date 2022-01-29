package com.example.vidT.models;
import javax.persistence.*;


@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filename;
    private String media_file_id;
    private int timer;
    private long timer1;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User author;

    public Video() {
    }

    public Video(String filename, String media_file_id, int timer, User user) {
        this.filename = filename;
        this.media_file_id = media_file_id;
        this.timer = timer;
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

    public String getMedia_file_id() {
        return media_file_id;
    }

    public void setMedia_file_id(String media_file_id) {
        this.media_file_id = media_file_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
