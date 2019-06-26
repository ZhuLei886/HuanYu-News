package com.huanyunews.news.model;

public class Note {
    private int t_note_id;
    private String t_note_name;
    private String t_note_content;
    private int t_note_pid;
    private int t_note_rid;
    private int t_user_id;

    public Note(int t_note_id, String t_note_name, String t_note_content, int t_note_pid, int t_note_rid, int t_user_id) {
        this.t_note_id = t_note_id;
        this.t_note_name = t_note_name;
        this.t_note_content = t_note_content;
        this.t_note_pid = t_note_pid;
        this.t_note_rid = t_note_rid;
        this.t_user_id = t_user_id;
    }

    public int getT_note_id() {
        return t_note_id;
    }

    public void setT_note_id(int t_note_id) {
        this.t_note_id = t_note_id;
    }

    public String getT_note_name() {
        return t_note_name;
    }

    public void setT_note_name(String t_note_name) {
        this.t_note_name = t_note_name;
    }

    public String getT_note_content() {
        return t_note_content;
    }

    public void setT_note_content(String t_note_content) {
        this.t_note_content = t_note_content;
    }

    public int getT_note_pid() {
        return t_note_pid;
    }

    public void setT_note_pid(int t_note_pid) {
        this.t_note_pid = t_note_pid;
    }

    public int getT_note_rid() {
        return t_note_rid;
    }

    public void setT_note_rid(int t_note_rid) {
        this.t_note_rid = t_note_rid;
    }

    public int getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(int t_user_id) {
        this.t_user_id = t_user_id;
    }
}
