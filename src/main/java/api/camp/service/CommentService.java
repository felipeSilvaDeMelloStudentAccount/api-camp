package api.camp.service;

import api.camp.collection.Comment;
import api.camp.model.campsites.Author;
import api.camp.model.comments.CommentDTO;
import api.camp.repository.CommentRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;

  public String postComment(String userId, String userName, String campsiteId,
      CommentDTO commentDTO) {
    log.debug("postComment Service");
    log.info("userId: {}, userName: {}, campsiteId: {}", userId, userName, campsiteId);
    Author author = Author.builder()
        .userId(userId)
        .userName(userName)
        .build();
    Comment comment = Comment.builder()
        .campsiteId(campsiteId)
        .createdDate(LocalDateTime.now())
        .text(commentDTO.getText())
        .author(author)
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
