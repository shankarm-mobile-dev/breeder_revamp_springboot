package com.suguna.breeder_revamp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUG_FEEDBACK_MST", schema = "SUG")
public class FeedbackMaster {

    @Id
    @Column(name = "QUESTION_ID")
    private int questionId;
    @Column(name = "LEDGER_ID")
    private int ledgerId;
    @Column(name = "FEEDBACK_REF")
    private String feedbackRef;
    @Column(name = "USER_TYPE")
    private String userType;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "CATEGORY_ID")
    private int categoryId;
    @Column(name = "QUESTION")
    private String question;
    @Column(name = "QUESTION_SEQ")
    private int questionSeq;
}
