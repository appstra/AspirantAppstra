package com.appstra.aspirante.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "EVALUATION", schema = "PARAMETERIZATION")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVAL_ID")
    private Integer evaluationId;

    @Column(name = "STAT_ID")
    @Comment("Campo de la base de datos de UserAppstra tabla estados")
    private Integer stateId;

    @Column(name = "EVAL_LIMIT_DATE")
    private Timestamp evaluationLimitDate;

    @Column(name = "EVAL_CREATION_DATE")
    private Timestamp evaluationCreationDate;

    @Column(name = "EVAL_EDIT_DATE")
    private Timestamp evaluationEditDate;

    @Column(name = "EVAL_EDIT_USER_ID")
    private Integer evaluationEditUserId;

    @ManyToOne
    @JoinColumn(name = "ASPI_ID", referencedColumnName = "ASPI_ID")
    private Aspirant aspirant;

    @ManyToOne
    @JoinColumn(name = "TYTE_ID", referencedColumnName = "TYTE_ID")
    private TypeTest typeTest;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "evaluation")
    private List<ResponseEvaluation> responseEvaluationList;

}
