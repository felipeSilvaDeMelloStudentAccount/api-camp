package api.camp.service;

import api.camp.collection.Comment;
import api.camp.model.comments.CommentDTO;
import api.camp.repository.CommentRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;

  public String postComment(String campsiteId, CommentDTO commentDTO) {
    log.debug("postComment method");
    Comment comment = Comment.builder()
        .campsiteId(campsiteId)
        .text(commentDTO.getText())
        .author(commentDTO.getAuthor())
        .build();
    commentRepository.save(comment);
    return comment.getId();
  }

  public List<Comment> getCommentsByCampsiteId(String campsiteId) {
    log.debug("getCommentsByCampsiteId method");
    return commentRepository.findByCampsiteId(campsiteId);
  }

  public void deleteComment(String commentid) {
    log.debug("deleteComment method");
    commentRepository.deleteById(commentid);
  }
}
