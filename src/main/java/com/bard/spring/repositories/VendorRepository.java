package com.bard.spring.repositories;

import com.bard.spring.domain.Category;
import com.bard.spring.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
