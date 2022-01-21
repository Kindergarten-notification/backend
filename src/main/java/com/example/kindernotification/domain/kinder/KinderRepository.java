package com.example.kindernotification.domain.kinder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KinderRepository extends JpaRepository<Kinder, Long> {


//    @Override
    Page<Kinder> findAllByOrderByKinderName(Pageable pageable);
  
    List<Kinder> findAllByOrderByIdDesc();

}

