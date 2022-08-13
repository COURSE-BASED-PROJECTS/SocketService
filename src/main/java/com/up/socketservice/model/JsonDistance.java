package com.up.socketservice.model;

import java.util.ArrayList;

import java.util.ArrayList;

public class JsonDistance{
    public ArrayList<Row> rows;

    @Override
    public String toString() {
        return "JsonDistance{" +
                "rows=" + rows.toString() +
                '}';
    }
}
class Distance{
    public String text;
    public int value;

    @Override
    public String toString() {
        return "Distance{" +
                "text='" + text + '\'' +
                ", value=" + value +
                '}';
    }
}

class Duration{
    public String text;
    public int value;

    @Override
    public String toString() {
        return "Duration{" +
                "text='" + text + '\'' +
                ", value=" + value +
                '}';
    }
}

class Element{
    public String status;
    public Duration duration;
    public Distance distance;

    @Override
    public String toString() {
        return "Element{" +
                "status='" + status + '\'' +
                ", duration=" + duration.toString() +
                ", distance=" + distance.toString() +
                '}';
    }
}


class Row{
    public ArrayList<Element> elements;

    @Override
    public String toString() {
        return "Row{" +
                "elements=" + elements.toString() +
                '}';
    }
}
