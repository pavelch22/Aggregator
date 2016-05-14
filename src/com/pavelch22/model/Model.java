package com.pavelch22.model;

import com.pavelch22.view.View;
import com.pavelch22.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Model implementation.
 */
public class Model {
    private View view;
    private Provider[] providers;

    /**
     * Constructs a new model.
     *
     * @param view a view to display data.
     * @param providers array of providers.
     */
    public Model(View view, Provider[] providers) {
        if (view == null || providers == null || providers.length < 1) {
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }

    /**
     * Collects data from all providers and updates a view.
     *
     * @param city where to find vacancies.
     */
    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider provider : providers) {
            vacancies.addAll(provider.getJavaVacancies(city));
        }
        view.update(vacancies);
    }
}
