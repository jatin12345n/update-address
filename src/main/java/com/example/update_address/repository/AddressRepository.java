package com.example.update_address.repository;

import com.example.update_address.model.AddressDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressDetails, Long> {

    Optional<AddressDetails> findByIdAndType(Long id, String type);

}
