package com.example.foodcenter.controller;

import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Address;
import com.example.foodcenter.model.RegTable;
import com.example.foodcenter.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class TableOrAddressController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/addAddress")
    public ResponseEntity addAddress(@Valid @RequestBody Address address, Principal principal) throws InternalErrorException, DuplicateDataException {


            registrationService.addAddress(address,principal.getName());
        return ResponseEntity.ok("success addUser one Address column");

    }

    @PostMapping("/addTable")
    public ResponseEntity addTable(@Valid @RequestBody RegTable table, Principal principal) throws InternalErrorException, DuplicateDataException {
        registrationService.addTable(table,principal.getName());

        return ResponseEntity.ok("success addUser one Table column");

    }
}
