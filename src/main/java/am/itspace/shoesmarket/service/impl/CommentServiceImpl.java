package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.CommentDto;
import am.itspace.shoesmarket.entity.Comment;
import am.itspace.shoesmarket.mapper.CommentMapper;
import am.itspace.shoesmarket.repository.CommentRepository;
import am.itspace.shoesmarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public void getCommentsByShoes(int id, ModelMap modelmap) {
        List<Comment> comments = commentRepository.findByShoesId(id);
        modelmap.put("comments", comments);
    }

    @Override
    public void getCommentById(int id) {
        commentRepository.findById(id);
    }

    @Override
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(int id, CommentDto commentDto) {
        Optional<Comment> comments = commentRepository.findById(id);
        if (comments.isPresent()) {
            Comment entity = commentMapper.toEntity(commentDto);
            entity.setId(id);
            commentRepository.save(entity);
        }
        throw new RuntimeException();
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}