package io.robusta.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.robusta.demo.UserDemoResource;

@ApplicationPath("fora")
public class ForaApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UserResource.class);
		s.add(TopicResource.class);
		s.add(CommentRessource.class);
		return s;
	}
	
}
