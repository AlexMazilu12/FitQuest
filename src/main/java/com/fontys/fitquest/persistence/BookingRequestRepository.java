package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.BookingRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequestEntity, Long> {
    List<BookingRequestEntity> findByUserIdOrTrainerId(Long userId, Long trainerId);
}