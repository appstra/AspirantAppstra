package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.LaborExperience;

import java.util.List;

public interface LaborExperienceService {
    LaborExperience saveLaborExperience(LaborExperience laborExperience);
    LaborExperience updateLaborExperience(LaborExperience laborExperience);
    Boolean deleteLaborExperience(Integer laborExperienceId);
    List<LaborExperience> listLaborExperiences();
    LaborExperience getLaborExperience(Integer laborExperienceId);
    List<LaborExperience> getLaborExperienceAspirant(Integer laborExperienceId);
}
