package com.example.update_address.service;

import com.example.update_address.model.AddressDetails;
import com.example.update_address.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public String add(AddressDetails addressDetails) {
        repository.save(addressDetails);
        return "Successfully added to database";
    }

    public AddressDetails updateAddress(Long id, String type, AddressDetails addressDetails) {
        if ("CURRENT".equalsIgnoreCase(type)) {
            return updateCurrentAddress(id, addressDetails);
        } else if ("PERMANENT".equalsIgnoreCase(type)) {
            return updatePermanentAddress(id, addressDetails);
        } else {
            throw new IllegalArgumentException("Unknown address type: " + type);
        }
    }

    private AddressDetails updateCurrentAddress(Long id, AddressDetails addressDetails) {
        return repository.findByIdAndType(id, "CURRENT".equalsIgnoreCase("current") ? "CURRENT" : "current").map(existingAddress -> {
            BeanUtils.copyProperties(addressDetails, existingAddress, "id", "type");
            existingAddress.setType("current".equalsIgnoreCase(existingAddress.getType()) ? "current" : "CURRENT");
            return repository.save(existingAddress);
        }).orElseThrow(() -> new RuntimeException("Current address not found"));
    }

    private AddressDetails updatePermanentAddress(Long id, AddressDetails addressDetails) {
        return repository.findByIdAndType(id, "PERMANENT".equalsIgnoreCase("permanent") ? "PERMANENT" : "permanent").map(existingAddress -> {
            BeanUtils.copyProperties(addressDetails, existingAddress, "id", "type");
            existingAddress.setType("permanent".equalsIgnoreCase(existingAddress.getType()) ? "permanent" : "PERMANENT");
            return repository.save(existingAddress);
        }).orElseThrow(() -> new RuntimeException("Permanent address not found"));
    }


}
