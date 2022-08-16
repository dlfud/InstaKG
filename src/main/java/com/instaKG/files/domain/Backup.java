package com.instaKG.files.domain;

import com.instaKG.board.domain.Board;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Backup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @ManyToOne
    private Board board;
}
