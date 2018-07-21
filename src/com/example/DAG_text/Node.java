package com.example.DAG_text;

import java.util.HashMap;

/**
 * Created by gg on 2018/7/20.
 */
public class Node {
    private char key;
    private boolean end;
    private HashMap<Character, Node> next;


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

    public HashMap<Character, Node> getNext() {
        return next;
    }

    public void setNext(HashMap<Character, Node> next) {
        this.next = next;
    }
}
