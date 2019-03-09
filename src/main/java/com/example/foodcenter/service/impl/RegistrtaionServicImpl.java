package com.example.foodcenter.service.impl;

import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Address;
import com.example.foodcenter.model.RegTable;
import com.example.foodcenter.model.User;
import com.example.foodcenter.repository.AddressRepository;
import com.example.foodcenter.repository.RegTableRepository;
import com.example.foodcenter.service.RegistrationService;
import com.example.foodcenter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrtaionServicImpl implements RegistrationService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RegTableRepository regTableRepository;
    @Autowired
    private UserServiceImpl userService;

    @Transactional
    @Override
    public void addAddress(Address address, String email) throws InternalErrorException {
        try {
            User user = userService.getByEmail(email);
            address.setUser(user);
            addressRepository.save(address);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

@Transactional
    @Override
    public void addTable(RegTable regTable, String email) throws InternalErrorException {
        try {
        User user = userService.getByEmail(email);
        regTable.setUser(user);
        regTableRepository.save(regTable);
    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
        throw new InternalErrorException(Constants.ERROR_MESSAGE);
    }
    }
}
