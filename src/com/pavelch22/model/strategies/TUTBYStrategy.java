package com.pavelch22.model.strategies;

import com.pavelch22.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class parses vacancies from tut.by.
 */
public class TUTBYStrategy implements Strategy {
    private static final String URL_FORMAT = "https://jobs.tut.by/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString, int pages) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            for (int i = 0; i < pages; i++) {
                System.out.println("Parsing " + (i + 1) + " page...");
                Document document = getDocument(searchString, i);
                Elements vacancyElements = document.getElementsByClass("search-result-item");
                for (Element vacancyElem : vacancyElements) {
                    String vacancyName = vacancyElem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text();
                    String city = vacancyElem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text();
                    String company = vacancyElem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text();
                    String salary = vacancyElem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text();
                    String url = vacancyElem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href");
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(vacancyName);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(company);
                    vacancy.setSiteName("TUT.by");
                    vacancy.setSalary(salary);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);
                }
                System.out.println("Page " + (i + 1) + " of " + pages + " is parsed... " + (pages - i - 1) + " remaining...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    private Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (X11; Linux x86_64)").referrer("google.com").get();
    }
}
