package com.theironyard.services;

import com.theironyard.entities.Car;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Caroline on 3/10/16.
 */
public interface CarRepository extends CrudRepository<Car, Integer>{
    List<Car> findByUser(User user);
}
