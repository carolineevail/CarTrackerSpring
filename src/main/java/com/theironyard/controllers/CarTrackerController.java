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
import com.theironyard.utils.PasswordStorage;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {

    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        if (userName != null) {
            model.addAttribute("user", user);
            model.addAttribute("cars", cars.findByUser(user));
        }
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-car", method = RequestMethod.POST)
    public String addCar(HttpSession session, String carMake, String carModel, int carYear, String carColor) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        Car car = new Car(carMake, carModel, carYear, carColor, user);
        cars.save(car);
        return "redirect:/";
    }

    @RequestMapping(path = "/edit-car", method = RequestMethod.GET)
    public String editCar(Model model, Integer id) {
        Car car = cars.findOne(id);
        model.addAttribute("car", car);
        return "edit";
    }

    @RequestMapping(path = "/perform-edit", method = RequestMethod.POST)
    public String performEdit(Integer id, String editedMake, String editedModel, int editedYear, String editedColor) {
        Car car = cars.findOne(id);
        car.make = editedMake;
        car.model = editedModel;
        car.year = editedYear;
        car.color = editedColor;
        cars.save(car);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-car", method = RequestMethod.POST)
    public String deleteCar(Integer id) {
        cars.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
