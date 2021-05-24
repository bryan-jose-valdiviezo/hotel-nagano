/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.controllers;

import com.hnagano.dtos.DayPriceDTO;
import com.hnagano.dtos.SeasonPriceDTO;
import com.hnagano.dtos.SuitePriceDTO;
import com.hnagano.dtos.ViewPriceDTO;
import com.hnagano.models.Day;
import com.hnagano.models.DayPrice;
import com.hnagano.models.Season;
import com.hnagano.models.Suite;
import com.hnagano.models.SuitePrice;
import com.hnagano.models.View;
import com.hnagano.models.ViewPrice;
import com.hnagano.services.DayServices;
import com.hnagano.services.SeasonServices;
import com.hnagano.services.SuiteServices;
import com.hnagano.services.ViewServices;
import java.time.LocalDate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Bryan
 */
@RequestMapping("admin/prices")
public class AdminPricesController {
    private ViewServices viewServices;
    private SuiteServices suiteServices;
    private SeasonServices seasonServices;
    private DayServices dayServices;

    public void setViewServices(ViewServices viewServices) {
        this.viewServices = viewServices;
    }

    public void setSuiteServices(SuiteServices suiteServices) {
        this.suiteServices = suiteServices;
    }

    public void setSeasonServices(SeasonServices seasonServices) {
        this.seasonServices = seasonServices;
    }

    public void setDayServices(DayServices dayServices) {
        this.dayServices = dayServices;
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String Index(ModelMap model) {
        model.addAttribute("currentViewPrices", viewServices.findAllCurrentViewsAndPrices());
        model.addAttribute("upcomingViewPrices", viewServices.findUpcomingViewPrices());
        
        model.addAttribute("currentSuitePrices", suiteServices.findAllCurrentSuitesAndPrices());
        model.addAttribute("upcomingSuitePrices", suiteServices.findAllUpcomingSuitesAndPrices());
        
        model.addAttribute("currentDayPrices", dayServices.findCurrentDaysAndPrices());
        model.addAttribute("upcomingDayPrices", dayServices.findUpcomingDaysAndPrices());
        
        model.addAttribute("currentSeasonPrice", seasonServices.findCurrentSeasonAndPrice());
        model.addAttribute("upcomingSeasonPrices", seasonServices.findUpcomingSeasonAndPrices());
        
        return "prices/pricesPage";
    }
    
    @RequestMapping(value="/new-view-price", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newViewPriceForm(ModelMap model) {
        model.put("viewPriceDTO", new ViewPriceDTO());
        model.addAttribute("views", viewServices.getAllViews());
        return new ModelAndView("prices/ViewPrices/viewPricesFormPartial");
    }
    
    @RequestMapping(value="/create-view-price", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createViewPrice(@ModelAttribute ViewPriceDTO form, ModelMap model) {      
        ViewPrice viewPrice = new ViewPrice(new View());
        viewPrice.setId(form.getViewID());
        viewPrice.setDateStart(LocalDate.parse(form.getDateStart()));
        viewPrice.setPrice(form.getPrice());
        
        viewServices.createViewPrice(viewPrice);
        
        model.addAttribute("currentViewPrices", viewServices.findAllCurrentViewsAndPrices());
        model.addAttribute("upcomingViewPrices", viewServices.findUpcomingViewPrices());
        
        return new ModelAndView("prices/ViewPrices/viewPricesListPartial");
    }
    
    @RequestMapping(value="/new-suite-price", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newSuitePriceForm(ModelMap model) {
        model.put("suitePriceDTO", new SuitePriceDTO());
        model.addAttribute("suites", suiteServices.getAllSuites());
        
        return new ModelAndView("prices/SuitePrices/suitePricesFormPartial");
    }
    
    @RequestMapping(value="/create-suite-price", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createSuitePrice(@ModelAttribute SuitePriceDTO form, ModelMap model) {      
        SuitePrice suitePrice = new SuitePrice(new Suite());
        suitePrice.setId(form.getSuiteID());
        suitePrice.setDateStart(LocalDate.parse(form.getDateStart()));
        suitePrice.setPrice(form.getPrice());
        
        suiteServices.createSuitePrice(suitePrice);
        
        model.addAttribute("currentSuitePrices", suiteServices.findAllCurrentSuitesAndPrices());
        model.addAttribute("upcomingSuitePrices", suiteServices.findAllUpcomingSuitesAndPrices());
        
        return new ModelAndView("prices/SuitePrices/suitePricesListPartial");
    }
    
    @RequestMapping(value="/new-day-price", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newDayPriceForm(ModelMap model) {
        model.put("dayPriceDTO", new DayPriceDTO());
        model.addAttribute("days", dayServices.getAllDayType());
        
        return new ModelAndView("prices/DayPrices/dayPricesFormPartial");
    }
    
    @RequestMapping(value="/create-day-price", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createDayPrice(@ModelAttribute DayPriceDTO form, ModelMap model) {      
        DayPrice dayPrice = new DayPrice(new Day());
        dayPrice.setId(form.getDayID());
        dayPrice.setDateStart(LocalDate.parse(form.getDateStart()));
        dayPrice.setPrice(form.getPrice());
        
        dayServices.createDayPrice(dayPrice);
        
        model.addAttribute("currentDayPrices", dayServices.findCurrentDaysAndPrices());
        model.addAttribute("upcomingDayPrices", dayServices.findUpcomingDaysAndPrices());
        
        return new ModelAndView("prices/DayPrices/dayPricesListPartial");
    }
    
    @RequestMapping(value="/new-season-price", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newSeasonPriceForm(ModelMap model) {
        model.put("seasonPriceDTO", new SeasonPriceDTO());
        
        return new ModelAndView("prices/SeasonPrices/seasonPricesFormPartial");
    }
    
    @RequestMapping(value="/create-season-price", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createSeasonPrice(@ModelAttribute SeasonPriceDTO form, ModelMap model) {      
        Season season = new Season();
        season.setEvent(form.getEvent());
        season.setPrice(form.getPrice());
        season.setDateStart(LocalDate.parse(form.getDateStart()));
        season.setDateEnd(LocalDate.parse(form.getDateEnd()));
        
        seasonServices.createSeasonPrice(season);
        
        model.addAttribute("currentSeasonPrice", seasonServices.findCurrentSeasonAndPrice());
        model.addAttribute("upcomingSeasonPrices", seasonServices.findUpcomingSeasonAndPrices());
        
        return new ModelAndView("prices/SeasonPrices/seasonPricesListPartial");
    }
}
