package com.cap.concept.demo.mq.repository;

import com.cap.concept.demo.mq.model.Person2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Person2Repository extends JpaRepository<Person2, Long> {
}
