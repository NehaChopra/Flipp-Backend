package com.quintifi.Flyer.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.quintifi.Flyer.domain.jpa.Flyer;
import com.quintifi.Flyer.exceptions.ApiExCode;
import com.quintifi.Flyer.exceptions.ApiException;
import com.quintifi.Flyer.repository.jpa.FlyerRepository;
import com.quintifi.Flyer.service.FlyerService;

/**
 * 
 * @author nchopra
 *
 */
@Service
public class FlyerServiceImpl implements FlyerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FlyerServiceImpl.class);
	private static final String added = "added";
	private static final String Unsuccessful = "Unsuccessful";

	@Autowired
	private FlyerRepository flyerRepository;

	@Override
	public List<Flyer> read() throws ApiException {
		List<Flyer> flyers = flyerRepository.findAll();
		if (CollectionUtils.isEmpty(flyers)) {
			LOGGER.error("Error while reading a list of flyers");
			throw new ApiException(ApiExCode.RECORD_NOT_FOUND.getCode(), ApiExCode.RECORD_NOT_FOUND.getMessage());
		}
		return flyers;
	}

	@Override
	public String create(Flyer flyer) throws ApiException {
		try {
			if (!ObjectUtils.isEmpty(flyer)) {
				flyerRepository.save(flyer);
				return flyer.getClicks() + " : " + added;
			}
		} catch (Exception e) {
			LOGGER.error("Error while creating a flyer");
			throw new ApiException(ApiExCode.NOT_VALID_RECORD.getCode(), ApiExCode.NOT_VALID_RECORD.getMessage());
		}
		return null;
	}

	@Override
	public Flyer mostClickedFlyer(Integer startTimestamp, Integer endTimestamp) throws ApiException {
		List<Flyer> flyers = flyerRepository.findAll();
		return mostClickedFlyer(flyers, startTimestamp, endTimestamp);
	}

	private Flyer mostClickedFlyer(List<Flyer> flyers, Integer startTimestamp, Integer endTimestamp) {
		Flyer mostClickedFlyer = null;
		Integer mostClickedFlyerNum = 0;
		for (Flyer f : flyers) {
			Integer currentClickedFlyper = 0;
			if (!ObjectUtils.isEmpty(f.getClicks())) {
				for (String it : f.getClicks().split(",")) {
					if (Integer.parseInt(it) >= startTimestamp && Integer.parseInt(it) <= endTimestamp) {
						currentClickedFlyper += 1;
					}
					if (currentClickedFlyper > mostClickedFlyerNum) {
						mostClickedFlyerNum = currentClickedFlyper;
						mostClickedFlyer = f;
					}
				}
			}
		}
		return mostClickedFlyer;
	}

	@Override
	public Flyer readById(Long id) throws ApiException {
		Optional<Flyer> flyer = flyerRepository.findById(id);
		if (!flyer.isPresent()) {
			LOGGER.error("Error while reading a list of flyers");
			throw new ApiException(ApiExCode.RECORD_NOT_FOUND.getCode(), ApiExCode.RECORD_NOT_FOUND.getMessage());
		}
		return flyer.get();
	}

	@Override
	public String edit(Flyer flyer, Integer numClick, Integer timestampClick) throws ApiException {
		Optional<Flyer> fly = flyerRepository.findById(flyer.getId());
		if (!fly.isPresent()) {
			LOGGER.error("Error while reading a list of flyers");
			throw new ApiException(ApiExCode.RECORD_NOT_FOUND.getCode(), ApiExCode.RECORD_NOT_FOUND.getMessage());
		}
		StringBuilder sb = new StringBuilder();
		sb.append(fly.get().getClicks()).append(",").append(flyer.getClicks());
		String[] flyClicks = sb.toString().split(",");
		String[] inputClicks = flyer.getClicks().split(",");
		if (flyClicks.length > numClick && (Integer.parseInt(inputClicks[inputClicks.length - 1]) == timestampClick)) {
			return Unsuccessful;
		} else {
			fly.get().setClicks(sb.toString());
			flyerRepository.save(fly.get());
			return added;
		}
	}

}
