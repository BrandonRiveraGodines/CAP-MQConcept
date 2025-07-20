package com.cap.concept.demo.mq.repository;

import com.cap.concept.demo.mq.model.Person4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Person4Repository extends JpaRepository<Person4, Long> {
}
