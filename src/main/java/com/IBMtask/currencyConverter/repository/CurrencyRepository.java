package com.IBMtask.currencyConverter.repository;

import com.IBMtask.currencyConverter.tables.CurrencyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// CRUD method: save, delete and ect in to database
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyTable,String> {

    CurrencyTable findByCurrency(String currency);

    List<CurrencyTable> findAll();

    void deleteAllInBatch();

}
