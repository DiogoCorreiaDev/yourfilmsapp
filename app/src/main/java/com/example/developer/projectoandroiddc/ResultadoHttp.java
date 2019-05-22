package com.example.developer.projectoandroiddc;

public class ResultadoHttp {
    private String seriesString;
    private EVENTTYPE eventtype;

    public ResultadoHttp() {
    }

    public ResultadoHttp(String seriesString, EVENTTYPE eventtype) {
        this.seriesString = seriesString;
        this.eventtype = eventtype;
    }

    public String getSeriesString() {
        return seriesString;
    }

    public void setSeriesString(String seriesString) {
        this.seriesString = seriesString;
    }

    public EVENTTYPE getEventtype() {
        return eventtype;
    }

    public void setEventtype(EVENTTYPE eventtype) {
        this.eventtype = eventtype;
    }
}