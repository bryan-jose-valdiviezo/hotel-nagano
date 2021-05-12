/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.models;

/**
 *
 * @author Bryan
 */
public class DatePrice {
    private ViewPrice viewPrice;
    private SuitePrice suitePrice;
    private Season season;
    private DayPrice dayPrice;

    public ViewPrice getViewPrice() {
        return viewPrice;
    }

    public void setViewPrice(ViewPrice viewPrice) {
        this.viewPrice = viewPrice;
    }

    public SuitePrice getSuitePrice() {
        return suitePrice;
    }

    public void setSuitePrice(SuitePrice suitePrice) {
        this.suitePrice = suitePrice;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public DayPrice getDay() {
        return dayPrice;
    }

    public void setDay(DayPrice day) {
        this.dayPrice = day;
    }
}
