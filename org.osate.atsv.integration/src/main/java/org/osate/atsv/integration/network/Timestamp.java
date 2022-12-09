package org.osate.atsv.integration.network;

import java.time.Instant;

public class Timestamp {

	/**
	 * When the request arrives on the loopback port
	 */
	private Instant requestReceived = null;

	/**
	 * When the request is done being deserialized (and read) into a request object
	 */
	private Instant unmarshallingComplete = null;

	/**
	 * When the plugins have been looked up by their id and loaded into OSATE
	 */
	private Instant pluginsLoaded = null;

	/**
	 * When the system model is done being instantiated
	 */
	private Instant modelInstantiated = null;

	/**
	 * When all selected analyses have finished executing
	 */
	private Instant analysesRun = null;

	/**
	 * When the response is done being serialized (and written) from a response object
	 */
	private Instant marshallingComplete = null;

	/**
	 * When the response leaves on the loopback port
	 */
	private Instant responseSent = null;

	/**
	 * When the previous response was sent
	 */
	private Instant prevResponseSent = null;

	public Timestamp(Instant prevResponseTime) {
		this.prevResponseSent = prevResponseTime;
	}

	public Instant getRequestReceived() {
		return requestReceived;
	}

	public void setRequestReceived(Instant requestReceived) {
		this.requestReceived = requestReceived;
	}

	public Instant getUnmarshallingComplete() {
		return unmarshallingComplete;
	}

	public void setUnmarshallingComplete(Instant unmarshallingComplete) {
		this.unmarshallingComplete = unmarshallingComplete;
	}

	public Instant getPluginsLoaded() {
		return pluginsLoaded;
	}

	public void setPluginsLoaded(Instant pluginsLoaded) {
		this.pluginsLoaded = pluginsLoaded;
	}

	public Instant getModelInstantiated() {
		return modelInstantiated;
	}

	public void setModelInstantiated(Instant modelInstantiated) {
		this.modelInstantiated = modelInstantiated;
	}

	public Instant getAnalysesRun() {
		return analysesRun;
	}

	public void setAnalysesRun(Instant analysesRun) {
		this.analysesRun = analysesRun;
	}

	public Instant getMarshallingComplete() {
		return marshallingComplete;
	}

	public void setMarshallingComplete(Instant marshallingComplete) {
		this.marshallingComplete = marshallingComplete;
	}

	public Instant getResponseSent() {
		return responseSent;
	}

	public void setResponseSent(Instant responseSent) {
		this.responseSent = responseSent;
	}

	public Instant getPrevResponseSent() {
		return prevResponseSent;
	}
}
