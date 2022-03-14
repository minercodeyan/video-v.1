package com.example.vidT.models;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long newsId;
    @Size(min = 1)
    @Column(name = "news")
    private String news;
    @Size(min = 1)
    @Column(name = "content")
    private String content;

    public News() {
    }

    public News(String news, String content) {
        this.news = news;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return String.valueOf(newsId) ;
    }
}
