package io.robusta.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.robusta.ForaDataSource;
import io.robusta.business.CommentBusiness;
import io.robusta.domain.Comment;

@Path("/comments")
@Produces("application/json")
@Consumes("application/json")
public class CommentRessource {

	@GET
	public List<Comment> findComment() throws Exception {
		return ForaDataSource.getInstance().getComments();
	}

	
	@PUT
	@Path("{idComment}/up")
	public Integer like(@PathParam("idComment") String idComment) {
	
		Comment comment = new CommentBusiness().getCommentById(idComment);
		comment.up();
		return comment.getScore();
		
	}
	
	@PUT
	@Path("{idComment}/down")
	public Integer dislike(@PathParam("idComment") String idComment) {
	
		Comment comment = new CommentBusiness().getCommentById(idComment);
		comment.down();
		return comment.getScore();
		
	}

}
