package api.camp.controller;

import static api.camp.service.JwtTokenService.validateToken;

import api.camp.collection.Comment;
import api.camp.model.comments.CommentDTO;
import api.camp.service.CommentService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/campsites/{campsiteid}/comments", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

  private CommentService commentService;

  @PostMapping
  public Map<String, String> postComment(
      @RequestHeader("Authorization") String authorizationHeader,
      @PathVariable String campsiteid, @RequestBody CommentDTO commentDTO) {
    log.info("postComment controller");
    validateToken(authorizationHeader);
    String commentId = commentService.postComment(campsiteid, commentDTO);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Comment posted successfully");
    response.put("commentId", commentId);
    return response;
  }

  @GetMapping
  public List<Comment> getComments(@PathVariable String campsiteid) {
    log.info("getComments controller");
    return commentService.getCommentsByCampsiteId(campsiteid);
  }

  @DeleteMapping
  public Map<String, String> deleteComment(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String commentid) {
    log.info("deleteComment controller");
    validateToken(authorizationHeader);
    commentService.deleteComment(commentid);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Comment deleted successfully");
    response.put("commentId", commentid);
    return response;
  }

}
