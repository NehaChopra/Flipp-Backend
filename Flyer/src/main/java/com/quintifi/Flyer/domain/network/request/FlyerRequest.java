package com.quintifi.Flyer.domain.network.request;

import java.io.Serializable;
import java.util.List;

import com.quintifi.Flyer.domain.jpa.Flyer;

/**
 * @author nchopra
 */
public class FlyerRequest implements Serializable {

	private static final long serialVersionUID = -253207988057358728L;

	private Flyer flyer;
	private Integer numClick;
	private Integer timestampClick;

	public FlyerRequest() {
		super();
	}

	public FlyerRequest(Flyer flyer, Integer numClick, Integer timestampClick) {
		this.flyer = flyer;
		this.numClick = numClick;
		this.timestampClick = timestampClick;
	}

	public Flyer getFlyer() {
		return flyer;
	}

	public void setFlyer(Flyer flyer) {
		this.flyer = flyer;
	}

	public Integer getNumClick() {
		return numClick;
	}

	public void setNumClick(Integer numClick) {
		this.numClick = numClick;
	}

	public Integer getTimestampClick() {
		return timestampClick;
	}

	public void setTimestampClick(Integer timestampClick) {
		this.timestampClick = timestampClick;
	}

}