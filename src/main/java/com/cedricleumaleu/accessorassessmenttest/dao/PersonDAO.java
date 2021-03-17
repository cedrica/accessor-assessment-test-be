package com.cedricleumaleu.accessorassessmenttest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedricleumaleu.accessorassessmenttest.data.Person;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long>{

}
