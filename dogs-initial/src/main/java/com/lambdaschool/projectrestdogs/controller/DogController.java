package com.lambdaschool.projectrestdogs.controller;

import com.lambdaschool.projectrestdogs.exeception.ResourseNotFoundException;
import com.lambdaschool.projectrestdogs.model.Dog;
import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/dogs")
public class DogController
{
    // localhost:8080/dogs/dogs
    @GetMapping(value = "/dogs", produces = {"application/json"})
    public ResponseEntity<?> getAllDogs()
    {
        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);
    }

    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        Dog rtnDog;

        if((ProjectrestdogsApplication.ourDogList.findDog( e ->(e.getId()) == id)) == null) {
            throw new ResourseNotFoundException("Employee with id " + id + " not found");
        } else {
            rtnDog = ProjectrestdogsApplication.ourDogList.findDog(d -> (d.getId() == id));
        }

        return new ResponseEntity<>(rtnDog, HttpStatus.OK);
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}", produces = {"application/json"})
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed)
    {
        ArrayList<Dog> rtnDogs;

        if((ProjectrestdogsApplication.ourDogList.findDog( e ->(e.getBreed()).equals(breed))) == null) {
            throw new ResourseNotFoundException("Employee with breed " + breed + " not found");
        } else {
            rtnDogs = ProjectrestdogsApplication.ourDogList.findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));
        }

        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }
}
