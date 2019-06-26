package com.huanyunews.news.excel;

public class User {
    public int t_user_id;
    public String t_user_account;
    public String t_user_password;
    public User(){

    }
    public String getT_user_email() {
        return t_user_email;
    }

    public void setT_user_email(String t_user_email) {
        this.t_user_email = t_user_email;
    }

    public String t_user_email;

    public String getT_user_url() {
        return t_user_url;
    }

    public void setT_user_url(String t_user_url) {
        this.t_user_url = t_user_url;
    }

    public String t_user_url;

    @Override
    public String toString() {
        return "User{" +
                "t_user_id=" + t_user_id +
                ", t_user_account='" + t_user_account + '\'' +
                ", t_user_password='" + t_user_password + '\'' +
                ", t_user_email='" + t_user_email + '\'' +
                ", t_user_url='" + t_user_url + '\'' +
                '}';
    }

    public int getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(int t_user_id) {
        this.t_user_id = t_user_id;
    }

    public String getT_user_account() {
        return t_user_account;
    }

    public void setT_user_account(String t_user_account) {
        this.t_user_account = t_user_account;
    }

    public String getT_user_password() {
        return t_user_password;
    }

    public void setT_user_password(String t_user_password) {
        this.t_user_password = t_user_password;
    }
}
