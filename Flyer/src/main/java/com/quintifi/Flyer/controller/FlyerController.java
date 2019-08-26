package com.quintifi.Flyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(value = {"/flyers","/flyers/"}, method = { RequestMethod.GET })
	public ResponseEntity<?> flyers() {
		List<Flyer> flyers = flyerService.read();
		return ResponseEntity.ok(flyers);
	}
	
	@RequestMapping(value = "/most-clicked-flyer", method = { RequestMethod.GET })
	public ResponseEntity<?> mostClickedFlyer() {
		return null;
	}
}
