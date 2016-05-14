package com.pavelch22.view;

import com.pavelch22.vo.Vacancy;

import java.util.List;

/**
 * View that prints vacancies to the console.
 */
public class ConsoleView implements View {

    @Override
    public void update(List<Vacancy> vacancies) {
        for (Vacancy vacancy : vacancies) {
            System.out.println(vacancy);
        }
    }
}
