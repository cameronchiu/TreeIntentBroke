package com.example.tree;

public class Scores {
    private int _date;
    private int _score;
    private int _id;

    public Scores(int date, int score) {
        this._date = date;
        this._score = score;
    }

    public int get_date() {
        return _date;
    }

    public void set_date(int _date) {
        this._date = _date;
    }

    public int get_score() {
        return _score;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}

