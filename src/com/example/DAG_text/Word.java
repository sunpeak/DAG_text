package com.example.DAG_text;

import java.util.List;

/**
 * Created by gg on 2018/5/28.
 */
public class Word {

    private Word() {
    }

    public Word(char key) {
        this.key = key;
    }

    public Word(char key, boolean end) {
        this.key = key;
        this.end = end;
    }

    private char key;

    private boolean end;

    private List<Word> next;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return key == word.key;

    }

    @Override
    public int hashCode() {
        return (int) key;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public List<Word> getNext() {
        return next;
    }

    public void setNext(List<Word> next) {
        this.next = next;
    }
}
