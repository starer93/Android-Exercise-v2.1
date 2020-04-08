package com.android.androidexercise.data.response;

import java.util.List;

public class NewsListResponse {
    private String title;
    private List<News> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<News> getRows() {
        return rows;
    }

    public void setRows(List<News> rows) {
        this.rows = rows;
    }
}
