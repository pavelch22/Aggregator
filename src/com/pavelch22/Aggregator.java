package com.pavelch22;

import com.pavelch22.controller.Controller;
import com.pavelch22.model.Model;
import com.pavelch22.model.Provider;
import com.pavelch22.model.strategies.TUTBYStrategy;
import com.pavelch22.view.ConsoleView;
import com.pavelch22.view.HtmlView;
import com.pavelch22.view.View;

/**
 * Main class.
 */
public class Aggregator {

    public static void main(String[] args) {
        View view = new HtmlView();
        Model model = new Model(view, new Provider[]{new Provider(new TUTBYStrategy(), 20)});
        Controller controller = new Controller(model);
        controller.onCitySelect("minsk");
    }
}
