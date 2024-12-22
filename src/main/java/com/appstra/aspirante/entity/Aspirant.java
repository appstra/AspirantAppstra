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
@Table(name = "ASPIRANT", schema = "PARAMETERIZATION")
public class Aspirant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASPI_ID")
    private Integer aspirantId;

    @Column(name = "PERS_ID")
    @Comment("Campo de la base de datos de UserAppstra tabla personas")
    private Integer personId;

    @Column(name = "COMP_ID")
    @Comment("Campo de la base de datos de companyAppstra tabla empresa")
    private Integer companyId;

    @Column(name = "STAT_ID")
    @Comment("Campo de la base de datos de UserAppstra tabla estados")
    private Integer stateId;

    @Column(name = "ROLE_ID")
    @Comment("Campo de la base de datos de companyAppstra tabla roles")
    private Integer roleId;

    @Column(name = "ASPI_APPLICATION_DATE")
    private Timestamp aspirantApplicationDate;

    @Column(name = "ASPI_CREATION_DATE")
    private Timestamp aspirantCreationDate;

    @Column(name = "ASPI_EDIT_DATE")
    private Timestamp aspirantEditDate;

    @Column(name = "ASPI_EDIT_USER_ID")
    private Integer aspiEditUserId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "aspirant")
    private List<Evaluation> evaluationList;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "aspirant")
    private List<LaborExperience> LaborExperience;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "aspirant")
    private List<DescriptionFamily> familyDescriptions;
}
