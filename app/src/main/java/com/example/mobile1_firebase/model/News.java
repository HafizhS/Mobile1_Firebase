package com.example.mobile1_firebase.model;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.PropertyName;

public class News {
    @DocumentId
    private String uid;

    @PropertyName("title")
    private String title;

    @PropertyName("content")
    private String content;

    public News() {
    }

    public News(String uid, String title, String content) {
        this.uid = uid;
        this.title = title;
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
