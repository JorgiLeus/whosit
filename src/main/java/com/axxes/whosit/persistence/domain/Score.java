package com.axxes.whosit.persistence.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "axxes_user_id")
    private AxxesUser axxesUser;

    @Column
    @CreatedDate
    private Date timestamp;

    @Column
    private double score;

    @Column(name =  "completiontime")
    private int completionTimeMs;
}
