package com.theironyard.services;

import com.theironyard.entities.Car;
import com.theironyard.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Caroline on 3/10/16.
 */
public interface CarRepository extends PagingAndSortingRepository<Car, Integer> {
    List<Car> findByUser(User user);
    Page<Car> findAll(Pageable pageable);
}
