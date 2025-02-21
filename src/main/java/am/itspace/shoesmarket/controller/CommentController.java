package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.dto.CommentDto;
import am.itspace.shoesmarket.entity.Comment;
import am.itspace.shoesmarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public String getCommentsByShoesId(@RequestParam int id, ModelMap modelmap) {
        commentService.getCommentsByShoesId(id, modelmap);
        return "/comments";
    }

    @GetMapping("/comment/{id}")
    public String getCommentById(@PathVariable int id) {
        commentService.getCommentById(id);
        return "redirect:/comment";
    }

    @GetMapping("/create")
    public String createCommentsPage() {
        return "comments/create";
    }

    @PostMapping
    public String createComment(@ModelAttribute Comment comment) {
        commentService.createComment(comment);
        return "redirect:/comments";
    }

    @GetMapping("/update/{id}")
    public String updateCommentPage(@PathVariable int id, @ModelAttribute CommentDto commentDto) {
        commentService.updateComment(id, commentDto);
        return "comments/updateComment";
    }

    @PostMapping("/update/{id}")
    public String updateComment(@PathVariable int id, @ModelAttribute CommentDto commentDto) {
        commentService.updateComment(id, commentDto);
        return "redirect:/comments";
    }

    @DeleteMapping("/delete")
    public String deleteComment(@RequestParam("id") int id) {
        commentService.deleteComment(id);
        return "redirect:/comments";
    }
}