package com.IBMtask.currencyConverter.controller;

import com.IBMtask.currencyConverter.repository.UserDataRepository;
import com.IBMtask.currencyConverter.tables.CurrencyTable;
import com.IBMtask.currencyConverter.repository.CurrencyRepository;
import com.IBMtask.currencyConverter.Conversion.ConversionCurrency;
import com.IBMtask.currencyConverter.service.Server;
import com.IBMtask.currencyConverter.tables.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    
    @Autowired
    public Server toDataBase;

    @Autowired
    public CurrencyRepository repository;

    @Autowired
    private UserDataRepository userDataRepository;

    // Find all currencies
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CurrencyTable> getFxRates(){
        UserData user = new UserData();
        long timeNow = System.currentTimeMillis();
        user.setActivityTime(timeNow);
        user.setActivity("getting fx rates");

        userDataRepository.save(user);
        return repository.findAll();
    }

    //Convertion between 2 currencies
    @PostMapping("/convert")
    public ResponseEntity<BigDecimal> convertCurrency(@RequestBody ConversionCurrency conversionCurrency){
        Optional<BigDecimal> result = this.toDataBase.convert(conversionCurrency);
        if (result.isPresent()){
    // collect all data from users (amount, exchanges, time, and data
            UserData user = new UserData();
            user.setToCurrency(conversionCurrency.getTo());
            user.setFromCurrency(conversionCurrency.getFrom());
            user.setAmount(conversionCurrency.getValue());
            long timeNow = System.currentTimeMillis();
            user.setActivityTime(timeNow);
            user.setActivity("converting");

            toDataBase.saveUserData(user);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}



