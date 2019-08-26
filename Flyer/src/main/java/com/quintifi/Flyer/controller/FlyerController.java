package com.quintifi.Flyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quintifi.Flyer.domain.jpa.Flyer;
import com.quintifi.Flyer.domain.network.request.FlyerRequest;
import com.quintifi.Flyer.service.FlyerService;

/**
 * 
 * @author nchopra
 *
 */
@RestController
public class FlyerController {

	@Autowired
	private FlyerService flyerService;

	@RequestMapping(value = "/flyers", method = { RequestMethod.POST })
	public ResponseEntity<?> addFlyer(@RequestBody FlyerRequest flyerRequest) {
		String response = flyerService.create(flyerRequest.getFlyer());
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = { "/flyers", "/flyers/{id}" }, method = { RequestMethod.GET })
	public ResponseEntity<?> flyers(@PathVariable(required = false) Long id) {
		if (!ObjectUtils.isEmpty(id)) {
			Flyer flyer = flyerService.readById(id);
			return ResponseEntity.ok(flyer);
		} else {
			List<Flyer> flyers = flyerService.read();
			return ResponseEntity.ok(flyers);
		}
	}

	@RequestMapping(value = { "/flyers" }, method = { RequestMethod.PUT })
	public ResponseEntity<?> edit(@RequestBody FlyerRequest flyerRequest) {
		String responser = flyerService.edit(flyerRequest.getFlyer(), flyerRequest.getNumClick(),
				flyerRequest.getTimestampClick());
		return ResponseEntity.ok(responser);
	}

	@RequestMapping(value = "/most-clicked-flyer/{startTimestamp}/{endTimestamp}", method = { RequestMethod.GET })
	public ResponseEntity<?> mostClickedFlyer(@PathVariable(required = false) Integer startTimestamp,
			@PathVariable(required = false) Integer endTimestamp) {
		Flyer flyer = flyerService.mostClickedFlyer(startTimestamp, endTimestamp);
		return ResponseEntity.ok(flyer);
	}
}
