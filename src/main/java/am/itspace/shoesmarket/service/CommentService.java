package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.dto.CommentDto;
import am.itspace.shoesmarket.entity.Comment;
import org.springframework.ui.ModelMap;

public interface CommentService {

    void getCommentsByShoesId(int id, ModelMap modelmap);

    void getCommentById(int id);

    void createComment(Comment comment);

    void updateComment(int id, CommentDto commentDto);

    void deleteComment(int id);
}