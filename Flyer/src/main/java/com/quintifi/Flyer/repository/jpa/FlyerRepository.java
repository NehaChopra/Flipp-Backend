package com.quintifi.Flyer.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quintifi.Flyer.domain.jpa.Flyer;

/**
 * 
 * @author nchopra
 *
 */
public interface FlyerRepository extends JpaRepository<Flyer, Long> {
	
	
}