package com.theironyard.controllers;

import com.theironyard.services.CarRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Caroline on 3/10/16.
 */
@Controller
public class CarTrackerController {
    @Autowired
    CarRepository cars;

    @Autowired
    UserRepository users;
}
