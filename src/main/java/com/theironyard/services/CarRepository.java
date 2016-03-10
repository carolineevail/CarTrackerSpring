package com.theironyard.services;

import com.theironyard.entities.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Caroline on 3/10/16.
 */
public interface CarRepository extends CrudRepository<Car, Integer>{
}
