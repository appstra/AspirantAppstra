package com.appstra.aspirante.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "LABOR_EXPERIENCE", schema = "TRANSACTIONAL")
public class LaborExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LAEX_ID")
    private Integer laborExperienceId;

    @Column(name = "LAEX_COMPANY_NAME")
    @Comment("Nombre de la empresa")
    private String laborExperienceCompanyName;

    @Column(name = "LAEX_POSITION_HELD")
    @Comment("Posición ocupada")
    private String laborExperiencePositionHeld;

    @Column(name = "LAEX_SALARY_EARNED")
    @Comment("Salario percibido")
    private Integer laborExperienceSalaryEarned;

    @Column(name = "LAEX_START_DATE")
    @Comment("Fecha de inicio")
    private Timestamp laborExperienceStartDate;

    @Column(name = "LAEX_END_DATE")
    @Comment("Fecha de finalización")
    private Timestamp laborExperienceEndDate;

    @Column(name = "LAEX_NUMBER_PEOPLE_MANAGED")
    @Comment("Número de personas gestionadas")
    private Integer laborExperienceNumberPeopleManaged;

    @Column(name = "LAEX_ACHIEVEMENTS")
    @Comment("Logros en la empresa")
    private String laborExperienceAchivements;

    @Column(name = "LAEX_REASON_LEAVING")
    @Comment("razon por retirarce de la empresa")
    private String laborExperienceReasonLeaving;

    @Column(name = "LAEX_SUPERVISOR_NAME")
    @Comment("Nombre del supervisor")
    private String laborExperienceSupervisorName;

    @Column(name = "LAEX_SUPERVISOR_PHONE")
    @Comment("Teléfono del supervisor")
    private String laborExperienceSupervisorPhone;

    @Column(name = "LAEX_CREATION_DATE")
    @Comment("Fecha de creación")
    private Timestamp laborExperienceCreationDate;

    @Column(name = "LAEX_EDIT_DATE")
    @Comment("Fecha de última edición")
    private Timestamp laborExperienceEditDate;

    @Column(name = "LAEX_EDIT_USER_ID")
    @Comment("ID del usuario que realizó la última edición")
    private Integer laborExperienceEditUserId;

    @ManyToOne
    @JoinColumn(name = "ASPI_ID", referencedColumnName = "ASPI_ID")
    private Aspirant aspirant;

}

