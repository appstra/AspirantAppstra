package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.Competence;
import com.appstra.aspirante.repository.CompetenceRepository;
import com.appstra.aspirante.service.CompetenceService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CompetenceImpl implements CompetenceService {
    private final CompetenceRepository competenceRepository;

    public CompetenceImpl(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    @Override
    public Competence saveCompetence(Competence competence) {
        competence.setCompetenceCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        competence.setCompetenceEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return competenceRepository.save(competence);
    }

    @Override
    public Competence updateCompetence(Competence competence) {
        Competence existingCompetence = competenceRepository.findById(competence.getCompetenceId())
                .orElseThrow(() -> new IllegalArgumentException("La competencia no existe: " + competence.getCompetenceId()));
        competence.setCompetenceCreationDate(existingCompetence.getCompetenceCreationDate());
        competence.setCompetenceEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return competenceRepository.save(competence);
    }

    @Override
    public Boolean deleteCompetence(Integer competenceId) {
        if (competenceRepository.existsById(competenceId)) {
            competenceRepository.deleteById(competenceId);
            return true;
        }
        return false;
    }

    @Override
    public List<Competence> listCompetences() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence getCompetence(Integer competenceId) {
        return competenceRepository.findById(competenceId)
                .orElseThrow(() -> new NoSuchElementException("La competencia con el ID: " + competenceId + " no se encontró"));
    }
}

