package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories;

import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<BookingEntity, Long> {}
