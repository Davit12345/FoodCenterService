package com.example.foodcenter.service;

import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Address;
import com.example.foodcenter.model.RegTable;

public interface RegistrationService {
    void addAddress(Address address,String email) throws InternalErrorException;
    void addTable(RegTable regTable,String email) throws InternalErrorException;

}
