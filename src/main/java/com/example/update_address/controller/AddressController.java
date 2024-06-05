package com.example.update_address.controller;

import com.example.update_address.model.AddressDetails;
import com.example.update_address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping("/adding_details")
    public String add(@RequestBody AddressDetails address) {
        return service.add(address);
    }

    @PatchMapping("/updating_Addresses/{id}/{type}")
    public ResponseEntity<AddressDetails> updateAddress(@PathVariable Long id, @PathVariable String type,
                                                        @RequestBody @Validated AddressDetails addressDetails) {
        try {
            AddressDetails updatedAddress = service.updateAddress(id, type, addressDetails);
            return ResponseEntity.ok(updatedAddress);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
