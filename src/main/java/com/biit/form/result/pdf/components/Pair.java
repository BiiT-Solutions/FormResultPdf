package com.biit.form.result.pdf.components;

public class Pair<T, U> {

    public Pair(T first, U second) {
        this.second = second;
        this.first = first;
    }

    private T first;
    private U second;

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
