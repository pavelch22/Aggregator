package com.pavelch22.view;

import com.pavelch22.vo.Vacancy;

import java.util.List;

/**
 * Base interface for all views.
 */
public interface View {

    /**
     * Updates a view using List of vacancies.
     *
     * @param vacancies list of vacancies.
     */
    void update(List<Vacancy> vacancies);
}
