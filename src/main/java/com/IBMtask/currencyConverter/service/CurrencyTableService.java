package com.IBMtask.currencyConverter.service;

import com.IBMtask.currencyConverter.repository.CurrencyRepository;
import com.IBMtask.currencyConverter.dto.FxRates;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;


@Service
public class CurrencyTableService {

    @Autowired
    private RestTemplate restTemplate;


    private static final Logger logger = LoggerFactory.getLogger(CurrencyTableService.class);
    private static final String url = "http://lb.lt/webservices/FxRates/FxRates.asmx/";


    public FxRates getCurrencies() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper mapper = new XmlMapper(module);

        try {
            var s = restTemplate.getForObject(url + "getCurrentFxRates?tp=", String.class);

            var unmarshalled = mapper.readValue(new StringReader(s), FxRates.class);
            logger.info("Received "  + unmarshalled);

            return (FxRates) unmarshalled;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
    }

}
