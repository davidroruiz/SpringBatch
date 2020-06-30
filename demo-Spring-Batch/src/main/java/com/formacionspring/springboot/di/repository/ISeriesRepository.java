package com.formacionspring.springboot.di.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.springboot.di.entity.Series;

@Repository
public interface ISeriesRepository extends JpaRepository<Series, Long>{

}
