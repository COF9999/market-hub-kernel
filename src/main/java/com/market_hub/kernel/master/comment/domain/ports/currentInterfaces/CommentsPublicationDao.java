package com.market_hub.kernel.master.comment.domain.ports.currentInterfaces;

import com.market_hub.kernel.master.comment.infraestructure.model.CommentPublication;

import java.util.List;

public interface CommentsPublicationDao {
    List<CommentPublication> findCommentsPublicationByPublication(Long idPublication);
}
