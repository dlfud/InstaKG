package com.instaKG.answerComment.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.instaKG.answer.domain.Answer;
import com.instaKG.board.domain.Board;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class AnswerComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private Integer replyLike;

    @ManyToOne
    private Board board;

    @ManyToOne
    private Answer answer;

//    @Converter
//    class BooleanToYNConverter implements AttributeConverter<Boolean, String> {
//        @Override
//        public String convertToDatabaseColumn(Boolean attribute){
//            return (attribute != null && attribute) ? "Y" : "N";
//        }
//
//        @Override
//        public Boolean convertToEntityAttribute(String dbData){
//            return "Y".equals(dbData);
//        }
//    }
}