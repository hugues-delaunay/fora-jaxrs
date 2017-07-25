package io.robusta.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.robusta.ForaDataSource;
import io.robusta.business.TopicBusiness;
import io.robusta.business.UserBusiness;
import io.robusta.demo.providers.NotAcceptableCustomException;
import io.robusta.domain.Topic;
import io.robusta.domain.User;

@Path("users")
@Produces("application/json")
@Consumes("application/json")
public class UserResource {

	@Path("test")
	@GET
	public String test() {
		return "test";
	}

	@GET

	public Response findUsers(@QueryParam("admin") String isAdmin) throws Exception {

		List<User> users = new ArrayList<>();

		for (User user : ForaDataSource.getInstance().getUsers()) {

			if (isAdmin == null) {
				users.add(user);
			} else {
				switch (isAdmin) {
				case "true":
					if (user.isAdmin())
						users.add(user);
					break;

				case "false":
					if (!user.isAdmin())
						users.add(user);
					break;

				default:
					throw new NotAcceptableCustomException("Parameter admin must be true or false");
				}
			}

		}
		return Response.ok(users).build();

	}

	@GET
	@Produces("application/json")
	@Path("{id}")
	public User findUsersById(@PathParam("id") Long id) throws Exception {
		User user = new UserBusiness().findById(id);
		if (user == null) {
			throw new NotFoundException("No user" + id + "found");
		}
		return user;
	}

	/*
	 * @GET public String helloAdmin(@QueryParam("admin") boolean isAdmin) {
	 * return "isAdmin ? " + isAdmin; }
	 */
	@POST
	public long createUser(User user) {
		new UserBusiness().createUser(user.getName());
		return user.getId();
	}
}
