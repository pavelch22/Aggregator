package com.pavelch22.model.strategies;

import com.pavelch22.vo.Vacancy;

import java.util.List;

/**
 * Strategy to collect vacancies from different sites.
 */
public interface Strategy {

    /**
     * Returns List of vacancies from a particular site.
     *
     * @param searchString additional search string.
     * @param pages        number of pages to parse.
     * @return List of vacancies.
     */
    List<Vacancy> getVacancies(String searchString, int pages);
}
