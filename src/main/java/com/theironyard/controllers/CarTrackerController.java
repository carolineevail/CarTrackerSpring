package com.theironyard.controllers;

import com.theironyard.entities.Car;
import com.theironyard.entities.User;
import com.theironyard.services.CarRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Caroline on 3/10/16.
 */
@Controller
public class CarTrackerController {
    @Autowired
    CarRepository cars;

    @Autowired
    UserRepository users;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        if (userName != null) {
            model.addAttribute("user", user);
        }
        return "home";
    }

    @RequestMapping(path = "/add-car", method = RequestMethod.POST)
    public String addCar(HttpSession session, String carMake, String carModel, int carYear, String carColor) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        Car car = new Car(carMake, carModel, carYear, carColor, user);
        cars.save(car);
        return "redirect:/";
    }


}
