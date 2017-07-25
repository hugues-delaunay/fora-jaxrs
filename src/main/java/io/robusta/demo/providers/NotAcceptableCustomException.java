package io.robusta.demo.providers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public final class NotAcceptableCustomException extends WebApplicationException {

	public NotAcceptableCustomException(String message) {
		super(Response.status(406).entity(message).build());
	}
}
