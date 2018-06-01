package com.example.DAG_text;

/**
 * Created by gg on 2018/5/31.
 */
public class Keyword {

    private String key;

    private int start;

    private int end;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyword keyword = (Keyword) o;

        if (start != keyword.start) return false;
        return end == keyword.end;

    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + end;
        return result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "key='" + key + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
