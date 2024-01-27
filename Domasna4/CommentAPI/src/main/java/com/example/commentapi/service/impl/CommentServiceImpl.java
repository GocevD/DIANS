package com.example.commentapi.service.impl;

import com.example.commentapi.model.Comment;
import com.example.commentapi.repository.CommentRepository;
import com.example.commentapi.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    public final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(String text, Long userId, String userName, Long wineryId) {
        return commentRepository.save(new Comment(text, userId, userName, wineryId));
    }

    @Override
    public List<Comment> findAllByWineryId(Long wineryId) {
        return commentRepository.findByWineryId(wineryId);
    }
}
