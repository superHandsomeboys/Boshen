package com.gpnu.boshen1.Bean;

public class Wiki {
    private Integer wiki_id;
    private String title;
    private Integer article_id;

    public Integer getWiki_id() {
        return wiki_id;
    }

    public void setWiki_id(Integer wiki_id) {
        this.wiki_id = wiki_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "Wiki{" +
                "wiki_id=" + wiki_id +
                ", title='" + title + '\'' +
                ", article_id=" + article_id +
                '}';
    }
}
