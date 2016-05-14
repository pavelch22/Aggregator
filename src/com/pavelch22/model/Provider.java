package com.pavelch22.model;

import com.pavelch22.model.strategies.Strategy;
import com.pavelch22.vo.Vacancy;

import java.util.List;

/**
 * This class provides vacancies from different sites.
 */
public class Provider {
    private Strategy strategy;
    private int pages;

    /**
     * Constructs a new provider.
     *
     * @param strategy strategy which parse data from a particular site.
     * @param pages number of pages to provide.
     */
    public Provider(Strategy strategy, int pages) {
        this.strategy = strategy;
        this.pages = pages;
    }

    /**
     * Constructs a new provider. This constructor create object that parses only first page.
     *
     * @param strategy strategy which parse data from a particular site.
     */
    public Provider(Strategy strategy) {
        this(strategy, 1);
    }


    /**
     * Sets a new strategy.
     *
     * @param strategy a new strategy implementation.
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets number of pages to parse.
     *
     * @param pages number of pages.
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Returns List of vacancies.
     *
     * @return List of vacancies.
     */
    public List<Vacancy> getJavaVacancies(String searchString) {
        return strategy.getVacancies(searchString, pages);
    }
}
