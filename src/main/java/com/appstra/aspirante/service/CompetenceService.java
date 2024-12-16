package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.Competence;

import java.util.List;

public interface CompetenceService {
    Competence saveCompetence(Competence competence);
    Competence updateCompetence(Competence competence);
    Boolean deleteCompetence(Integer competenceId);
    List<Competence> listCompetences();
    Competence getCompetence(Integer competenceId);
}
