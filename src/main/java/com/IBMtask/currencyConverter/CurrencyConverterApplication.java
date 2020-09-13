package com.IBMtask.currencyConverter;

import com.IBMtask.currencyConverter.dto.FxRates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


@SpringBootApplication
public class CurrencyConverterApplication {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public Unmarshaller jaxbUnmarshaller() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(FxRates.class);
		return jaxbContext.createUnmarshaller();
	}

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

}

