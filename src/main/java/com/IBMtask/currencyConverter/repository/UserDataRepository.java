package com.IBMtask.currencyConverter.repository;


import com.IBMtask.currencyConverter.tables.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// CRUD method: save, delete and ect in to database
@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {


}
