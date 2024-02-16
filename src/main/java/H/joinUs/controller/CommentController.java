package H.joinUs.controller;

import H.joinUs.dto.RequestDto.CommentRequestDto;
import H.joinUs.dto.RequestDto.PostRequestDto;
import H.joinUs.dto.ResponseDto.CommentResponseDto;
import H.joinUs.dto.ResponseDto.PostResponseDto;
import H.joinUs.service.CommentService;
import H.joinUs.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping(value = "/post/{postId}/comment")
    public ResponseEntity<CommentResponseDto.CreateComment> createComment(@PathVariable(name = "postId") Long postId,
                                                                          @RequestBody CommentRequestDto.CreateComment request) {
        return ResponseEntity.ok(commentService.create(postId, request));
    }

    @PutMapping(value = "/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentResponseDto.UpdateComment> updateComment(@PathVariable(name = "postId") Long postId,
                                                                          @PathVariable(name = "commentId") Long commentId,
                                                                          @RequestBody CommentRequestDto.UpdateComment request) {
        return ResponseEntity.ok(commentService.update(postId, commentId, request));
    }

    @DeleteMapping(value = "/post/{postId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name = "postId") Long postId,
                                              @PathVariable(name = "commentId") Long commentId) {
        commentService.delete(postId, commentId);
        return ResponseEntity.ok().build();
    }}
