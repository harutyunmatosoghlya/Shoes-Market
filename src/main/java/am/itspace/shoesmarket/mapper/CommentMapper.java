package am.itspace.shoesmarket.mapper;

import am.itspace.shoesmarket.dto.CommentDto;
import am.itspace.shoesmarket.dto.SaveCommentRequest;
import am.itspace.shoesmarket.dto.ShoesDto;
import am.itspace.shoesmarket.entity.Comment;
import am.itspace.shoesmarket.entity.Shoes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CommentMapper {

    CommentDto toCommentDto(Comment comment);

    List<CommentDto> toCommentDto(List<Comment> comment);

    Comment toEntity(SaveCommentRequest CommentRequest);

    Comment toEntity(CommentDto CommentDto);
}