package com.example.commentapi.service;
import com.example.commentapi.model.Comment;

import java.util.List;

public interface CommentService {
    public Comment save(String text, Long userId, String userName, Long wineryId);
    public List<Comment> findAllByWineryId(Long wineryId);

}
