package com.quintifi.Flyer.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.quintifi.Flyer.domain.jpa.Flyer;
import com.quintifi.Flyer.exceptions.ApiException;

/**
 * 
 * @author nchopra
 *
 */
public interface FlyerService {

	List<Flyer> read() throws ApiException;

	Flyer readById(Long id) throws ApiException;

	@Transactional
	String create(Flyer flyer) throws ApiException;

	@Transactional
	String edit(Flyer flyer, Integer numClick, Integer timestampClick) throws ApiException;

	Flyer mostClickedFlyer(Integer startTimestamp, Integer endTimestamp) throws ApiException;
}
