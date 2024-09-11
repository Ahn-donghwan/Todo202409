package com.sparta.round13.service;

import com.sparta.round13.dto.CommentSaveRequestDto;
import com.sparta.round13.dto.CommentResponseDto;
import com.sparta.round13.dto.CommentUpdateRequestDto;
import com.sparta.round13.entity.Comment;
import com.sparta.round13.entity.Todo;
import com.sparta.round13.exception.NoSuchResourceException;
import com.sparta.round13.repository.CommentRepository;
import com.sparta.round13.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

//    public Comment findCommentById(Long todoId){
//        return commentRepository.findById(todoId).orElseThrow(() -> new NoSuchResourceException("해당 리소스를 찾을 수 없습니다. ID :" + todoId ));
//    }

    @Transactional
    public CommentResponseDto saveComment(Long todoId, CommentSaveRequestDto commentSaveRequestDto) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        Comment comment = new Comment(commentSaveRequestDto.getUsername(), commentSaveRequestDto.getContents(), todo);

        commentRepository.save(comment);

        return new CommentResponseDto(
                comment.getId(),
                comment.getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }


    public List<CommentResponseDto> getAllComment(Long todoId) {


        List<Comment> commentList = commentRepository.findAllByTodoId(todoId);

        List<CommentResponseDto> dtoList = new ArrayList<>();

        for (Comment comment : commentList) {

            CommentResponseDto dto = new CommentResponseDto( comment.getId(),
                                                             comment.getUsername(),
                                                             comment.getContents(),
                                                             comment.getCreatedAt(),
                                                             comment.getModifiedAt()
            );

            dtoList.add(dto);
        }
        return dtoList;
    }

    public CommentResponseDto getDetailComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        return new CommentResponseDto(
                comment.getId(),
                comment.getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        comment.updateComment(commentUpdateRequestDto.getUsername(), commentUpdateRequestDto.getContents());

        return new CommentResponseDto(
                comment.getId(),
                comment.getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
