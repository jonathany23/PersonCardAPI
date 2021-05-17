package com.nuvu.person.web.controller;

import com.nuvu.person.persistence.entity.Card;
import com.nuvu.person.service.ICardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardApp {

   @Autowired
   private ICardService service;

   @PostMapping("/save")
   @ApiOperation("Create a new card")
   @ApiResponses({
           @ApiResponse(code = 200, message = "OK"),
           @ApiResponse(code = 400, message = "BAD REQUEST"),
   })
   public ResponseEntity<Card> saveCard(@RequestBody Card card){
       return service.saveCard(card)
               .map( crd -> new ResponseEntity<>(crd, HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
   }

    @PatchMapping("/edit")
    @ApiOperation("Edit a card")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD REQUEST"),
    })
    public ResponseEntity<Card> editCard(@RequestBody Card card){
        return service.saveCard(card)
                .map( crd -> new ResponseEntity<>(crd, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{card_number}")
    @ApiOperation("Get a card")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
    })
    public ResponseEntity<Card> getCard(
            @ApiParam(value = "Card Number", required = true, example = "1111222233334444")
            @PathVariable("card_number") String cardNumber){
        return service.getCard(cardNumber)
                .map( crd -> new ResponseEntity<>(crd, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{card_number}")
    @ApiOperation("Get a card")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "INTERNAL ERROR"),
    })
    public ResponseEntity<Boolean> deleteCard(
            @ApiParam(value = "Card Number", required = true, example = "1111222233334444")
            @PathVariable("card_number") String cardNumber){
        return new ResponseEntity<>(service.deleteCard(cardNumber), HttpStatus.OK);
    }
}
