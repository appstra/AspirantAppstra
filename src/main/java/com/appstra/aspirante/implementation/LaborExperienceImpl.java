package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.LaborExperience;
import com.appstra.aspirante.repository.LaborExperienceRepository;
import com.appstra.aspirante.service.LaborExperienceService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LaborExperienceImpl implements LaborExperienceService {
    private final LaborExperienceRepository laborExperienceRepository;

    public LaborExperienceImpl(LaborExperienceRepository laborExperienceRepository) {
        this.laborExperienceRepository = laborExperienceRepository;
    }

    @Override
    public LaborExperience saveLaborExperience(LaborExperience laborExperience) {
        laborExperience.setLaborExperienceCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        laborExperience.setLaborExperienceEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return laborExperienceRepository.save(laborExperience);
    }

    @Override
    public LaborExperience updateLaborExperience(LaborExperience laborExperience) {
        LaborExperience existingLaborExperience = laborExperienceRepository.findById(laborExperience.getLaborExperienceId())
                .orElseThrow(() -> new IllegalArgumentException("La experiencia laboral no existe: " + laborExperience.getLaborExperienceId()));
        laborExperience.setLaborExperienceCreationDate(existingLaborExperience.getLaborExperienceCreationDate());
        laborExperience.setLaborExperienceEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return laborExperienceRepository.save(laborExperience);
    }

    @Override
    public Boolean deleteLaborExperience(Integer laborExperienceId) {
        if (laborExperienceRepository.existsById(laborExperienceId)) {
            laborExperienceRepository.deleteById(laborExperienceId);
            return true;
        }
        return false;
    }

    @Override
    public List<LaborExperience> listLaborExperiences() {
        return laborExperienceRepository.findAll();
    }

    @Override
    public LaborExperience getLaborExperience(Integer laborExperienceId) {
        return laborExperienceRepository.findById(laborExperienceId)
                .orElseThrow(() -> new NoSuchElementException("La experiencia laboral con el ID: " + laborExperienceId + " no se encontró"));
    }

    @Override
    public List<LaborExperience> getLaborExperienceAspirant(Integer laborExperienceId) {
        return laborExperienceRepository.findByAspirantAspirantId(laborExperienceId);
    }
}

