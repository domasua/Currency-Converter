package com.IBMtask.currencyConverter.service;

import com.IBMtask.currencyConverter.Conversion.ConversionCurrency;
import com.IBMtask.currencyConverter.tables.CurrencyTable;
import com.IBMtask.currencyConverter.tables.UserData;
import com.IBMtask.currencyConverter.repository.CurrencyRepository;
import com.IBMtask.currencyConverter.repository.UserDataRepository;
import com.IBMtask.currencyConverter.dto.FxRate;
import com.IBMtask.currencyConverter.dto.FxRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class Server {

    @Autowired
    private CurrencyRepository repository;

    @Autowired
    private CurrencyTableService currencyTableService;

    @Autowired
    private UserDataRepository userDataRepository;


    private List<CurrencyTable> transform (FxRates rates){
        List<CurrencyTable> currencyTableList = new ArrayList<>();

        for (FxRate rate: rates.getFxRate()) { // add currencies values and amounts to currencyTable
            CurrencyTable currencyTable = new CurrencyTable();

            currencyTable.setCurrency(rate.getCcyAmt()[1].getCcy());
            currencyTable.setExchangeRate(new BigDecimal(rate.getCcyAmt()[1].getAmt()));
            currencyTableList.add(currencyTable);
        }
        currencyTableList.sort(Comparator.comparing(CurrencyTable::getCurrency));

        return currencyTableList;
    }

    // update currencies amount after 60 min
    @Scheduled(fixedRate = 5 * 1000 * 60 * 60)
    public void save(){
        // delete old currencies amounts
        repository.deleteAllInBatch();

        FxRates rates = currencyTableService.getCurrencies();
        List<CurrencyTable> currencyTables = transform(rates);
        // add euro in currency list
        CurrencyTable euro = new CurrencyTable();
        euro.setCurrency("EUR");
        euro.setExchangeRate(BigDecimal.ONE);

        repository.save(euro);
        repository.saveAll(currencyTables);
    }

    // currencies conversion formula
    public Optional <BigDecimal> convert(ConversionCurrency conversionCurrency){
        CurrencyTable to = this.repository.findByCurrency(conversionCurrency.getTo());
        CurrencyTable from = this.repository.findByCurrency(conversionCurrency.getFrom());

       if(to == null || from == null) {
           return Optional.empty();
       } else {

           BigDecimal toValue = new BigDecimal(String.valueOf(to.getExchangeRate()));
           BigDecimal fromValue = new BigDecimal(String.valueOf(from.getExchangeRate()));

           BigDecimal conversationResult = (toValue.multiply(conversionCurrency.getValue())).
                   divide(fromValue, 5, RoundingMode.HALF_EVEN);

           return Optional.of(conversationResult);
       }
    }

    // save all User activity into database
    public void saveUserData(UserData userData){
        userDataRepository.save(userData);
    }

}
