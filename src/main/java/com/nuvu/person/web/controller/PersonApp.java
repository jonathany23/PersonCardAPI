package com.nuvu.person.web.controller;

import com.nuvu.person.persistence.entity.Person;
import com.nuvu.person.service.IPersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonApp {

    @Autowired
    private IPersonService service;

    @PostMapping("/save")
    @ApiOperation("Save a new person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD REQUEST"),
    })
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        return service.savePerson(person)
                .map(per -> new ResponseEntity<>(per, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping("/edit")
    @ApiOperation("Edit a exiting person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD REQUEST"),
    })
    public ResponseEntity<Person> editPerson(@RequestBody Person person){
        return service.editPerson(person)
                .map(per -> new ResponseEntity<>(per, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{legal_id}")
    @ApiOperation("Get a exiting person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
    })
    public ResponseEntity<Person> getPerson(
            @ApiParam(value = "Person legal id", required = true, example = "1020432787")
            @PathVariable("legal_id") String legalId){
        return service.getPerson(legalId)
                .map(per -> new ResponseEntity<>(per, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping ("/delete/{legal_id}")
    @ApiOperation("delete a person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "INTERNAL ERROR"),
    })
    public ResponseEntity<Boolean> deletePerson(@PathVariable("legal_id") String legalId){
        return new ResponseEntity<>(service.deletePerson(legalId), HttpStatus.OK);
    }
}
