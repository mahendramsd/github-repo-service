package com.msd.restservice.repository;

import com.msd.restservice.domain.OwnerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mahendrasridayarathna
 * @created 15/08/2024 - 8:41 am
 * @project IntelliJ IDEA
 */

@Repository
public interface OwnerDetailsRepository extends JpaRepository<OwnerDetails, Long> {

    Optional<OwnerDetails> findByFullName(String s);
}
