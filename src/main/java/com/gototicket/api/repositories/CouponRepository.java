package com.gototicket.api.repositories;

import com.gototicket.api.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Date;
import java.util.List;

public interface CouponRepository extends JpaRepository <Coupon, UUID> {
    List<Coupon> findByEventIdAndValidAfter(UUID eventId, Date currentDate);
}