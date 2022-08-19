package com.instaKG.answerComment.dao;

import com.instaKG.answerComment.domain.AnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {
}
