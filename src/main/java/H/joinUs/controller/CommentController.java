package H.joinUs.controller;

import H.joinUs.dto.RequestDto.CommentRequestDto;
import H.joinUs.dto.ResponseDto.CommentResponseDto;
import H.joinUs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    }

    @GetMapping(value = "/post/{postId}/comment/list")
    public ResponseEntity<List<CommentResponseDto.GetComment>> getList(@PathVariable(name = "postId") Long postId) {
        return ResponseEntity.ok(commentService.get(postId));
    }

    @PostMapping(value = "/post/{postId}/comment/{commentId}/recomment")
    public ResponseEntity<CommentResponseDto.CreateRecomment> createRecomment(@PathVariable(name = "postId") Long postId,
                                                                              @PathVariable(name = "commentId") Long commentId,
                                                                              @RequestBody CommentRequestDto.CreateRecomment request) {
        return ResponseEntity.ok(commentService.createRecomment(postId, commentId, request));
    }

    @PutMapping(value = "/post/{postId}/comment/{commentId}/recomment/{recommentId}")
    public ResponseEntity<CommentResponseDto.UpdateRecomment> updateRecomment(@PathVariable(name = "postId") Long postId,
                                                                              @PathVariable(name = "commentId") Long commentId,
                                                                              @PathVariable(name = "recommentId") Long recommentId,
                                                                              @RequestBody CommentRequestDto.UpdateRecomment request){
        return ResponseEntity.ok(commentService.updateRecomment(postId, commentId, recommentId, request));
    }


}
