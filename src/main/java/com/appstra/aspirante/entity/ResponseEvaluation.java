package com.appstra.aspirante.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;
import java.util.List;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "RESPONSE_EVALUATION", schema = "TRANSACTIONAL")
public class ResponseEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REEV_ID")
    private Integer responseEvaluationId;

    @Column(name = "RESP_CREATION_DATE")
    private Timestamp responseEvaluationCreationDate;

    @Column(name = "RESP_EDIT_DATE")
    private Timestamp responseEvaluationEditDate;

    @Column(name = "RESP_EDIT_USER_ID")
    private Integer responseEvaluationEditUserId;

    @ManyToOne
    @JoinColumn(name = "EVAL_ID", referencedColumnName = "EVAL_ID")
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "ASK_ID", referencedColumnName = "ASK_ID")
    private Ask ask;

    @ManyToOne
    @JoinColumn(name = "RESP_ID", referencedColumnName = "RESP_ID")
    private Response response;

}

