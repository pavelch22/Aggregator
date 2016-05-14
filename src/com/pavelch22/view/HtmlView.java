package com.pavelch22.view;

import com.pavelch22.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * View that prints vacancies to the html file.
 */
public class HtmlView implements View {
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
        openInBrowser();
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document = null;
        try {
            document = getDocument();
            Element vacancyTemplate = document.getElementsByAttributeValue("class", "vacancy template").first().clone();
            document.getElementsByClass("vacancy").remove();
            for (Vacancy vacancy : vacancies) {
                Element clonedTemplate = vacancyTemplate.clone();
                clonedTemplate.removeClass("template");
                Element title = clonedTemplate.getElementsByAttributeValue("class", "title").first();
                title.getElementsByTag("a").first().attr("href", vacancy.getUrl()).append(vacancy.getTitle());
                clonedTemplate.getElementsByAttributeValue("class", "city").first().append(vacancy.getCity());
                clonedTemplate.getElementsByAttributeValue("class", "companyName").append(vacancy.getCompanyName());
                clonedTemplate.getElementsByAttributeValue("class", "salary").append(vacancy.getSalary());
                document.getElementsByTag("table").first().appendChild(clonedTemplate);
            }
            document.getElementsByAttributeValue("class", "vacancy").first().before(vacancyTemplate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.html();
    }

    private void updateFile(String s) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filePath);
            os.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void openInBrowser() {
        try {
            Desktop.getDesktop().browse(new File(filePath).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
