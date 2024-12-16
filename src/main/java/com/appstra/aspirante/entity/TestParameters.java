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
@Table(name = "TEST_PARAMETERS", schema = "TRANSACTIONAL")
public class TestParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEPA_ID")
    private Integer testParametersId;

    @Column(name = "TEPA_VALUE_MIN")
    private Integer testParametersValueMin;

    @Column(name = "TEPA_VALUE_MAX")
    private Integer testParametersValueMax;

    @Column(name = "TEPA_DESCRIPTION")
    private String testParametersDescription;

    @Column(name = "TEPA_CREATION_DATE")
    private Timestamp testParametersCreationDate;

    @Column(name = "TEPA_EDIT_DATE")
    private Timestamp testParametersEditDate;

    @Column(name = "TEPA_EDIT_USER_ID")
    private Integer testParametersEditUserID;

    @ManyToOne
    @JoinColumn(name = "COMP_ID", referencedColumnName = "COMP_ID")
    private Competence competence;

}
