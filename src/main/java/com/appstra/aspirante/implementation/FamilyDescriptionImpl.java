package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.DescriptionFamily;
import com.appstra.aspirante.repository.FamilyDescriptionRepository;
import com.appstra.aspirante.service.FamilyDescriptionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FamilyDescriptionImpl implements FamilyDescriptionService {
    private final FamilyDescriptionRepository familyDescriptionRepository;

    public FamilyDescriptionImpl(FamilyDescriptionRepository familyDescriptionRepository) {
        this.familyDescriptionRepository = familyDescriptionRepository;
    }

    @Override
    public DescriptionFamily saveFamilyDescription(DescriptionFamily familyDescription) {
        familyDescription.setDescriptionFamilyCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        familyDescription.setDescriptionFamilyEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return familyDescriptionRepository.save(familyDescription);
    }

    @Override
    public DescriptionFamily updateFamilyDescription(DescriptionFamily familyDescription) {
        DescriptionFamily existingFamilyDescription = familyDescriptionRepository.findById(familyDescription.getDescriptionFamilyId())
                .orElseThrow(() -> new IllegalArgumentException("La descripción familiar no existe: " + familyDescription.getDescriptionFamilyId()));
        familyDescription.setDescriptionFamilyCreationDate(existingFamilyDescription.getDescriptionFamilyCreationDate());
        familyDescription.setDescriptionFamilyEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return familyDescriptionRepository.save(familyDescription);
    }

    @Override
    public Boolean deleteFamilyDescription(Integer familyDescriptionId) {
        if (familyDescriptionRepository.existsById(familyDescriptionId)) {
            familyDescriptionRepository.deleteById(familyDescriptionId);
            return true;
        }
        return false;
    }

    @Override
    public List<DescriptionFamily> listFamilyDescriptions() {
        return familyDescriptionRepository.findAll();
    }

    @Override
    public DescriptionFamily getFamilyDescription(Integer familyDescriptionId) {
        return familyDescriptionRepository.findById(familyDescriptionId)
                .orElseThrow(() -> new NoSuchElementException("La descripción familiar con el ID: " + familyDescriptionId + " no se encontró"));
    }

    @Override
    public List<DescriptionFamily> getFamilyDescriptionAspirant(Integer familyDescriptionId) {
        return familyDescriptionRepository.findByAspirantAspirantId(familyDescriptionId);
    }
}
