package com.pavelch22.controller;

import com.pavelch22.model.Model;

/**
 * Controller implementation.
 */
public class Controller {
    private Model model;

    /**
     * Constructs a new contoller.
     *
     * @param model
     */
    public Controller(Model model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

    /**
     * Invoked when process of finding vacancies has been started.
     *
     * @param cityName where to find vacancies.
     */
    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }
}
