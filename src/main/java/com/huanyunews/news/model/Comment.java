package com.huanyunews.news.model;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private String content;
    private Timestamp create_time;
    private int t_user_id;
    private int t_article_id;
    private int pid;
    private int reply_user_id;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", create_time=" + create_time +
                ", t_user_id=" + t_user_id +
                ", t_article_id=" + t_article_id +
                ", pid=" + pid +
                ", reply_user_id=" + reply_user_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public int getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(int t_user_id) {
        this.t_user_id = t_user_id;
    }

    public int getT_article_id() {
        return t_article_id;
    }

    public void setT_article_id(int t_article_id) {
        this.t_article_id = t_article_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getReply_user_id() {
        return reply_user_id;
    }

    public void setReply_user_id(int reply_user_id) {
        this.reply_user_id = reply_user_id;
    }
}
