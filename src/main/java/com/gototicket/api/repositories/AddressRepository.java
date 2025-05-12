package com.gototicket.api.repositories;

import com.gototicket.api.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    //@Query("SELECT a FROM Address a WHERE a.id == :id")
    //public List<Address> findAddressById(@Param("id") String id);
    public Optional<Address> findByEventId(UUID eventId);
}
