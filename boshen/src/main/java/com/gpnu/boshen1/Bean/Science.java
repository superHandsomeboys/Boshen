package com.gpnu.boshen1.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Science {
    DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
    private Integer science_id;
    private String title;
    private Integer author_id;
    private Integer article_id;
    private Date create_time;

    public Integer getScience_id() {
        return science_id;
    }

    public void setScience_id(Integer science_id) {
        this.science_id = science_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String ChangeDate(){

        return df.format(create_time);
    }
    @Override
    public String toString() {
        return "Science{" +
                "science_id=" + science_id +
                ", title='" + title + '\'' +
                ", author_id=" + author_id +
                ", article_id=" + article_id +
                ", create_time=" + create_time +
                '}';
    }
}
