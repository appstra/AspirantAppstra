package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.DescriptionFamily;

import java.util.List;

public interface FamilyDescriptionService {
    DescriptionFamily saveFamilyDescription(DescriptionFamily familyDescription);
    DescriptionFamily updateFamilyDescription(DescriptionFamily familyDescription);
    Boolean deleteFamilyDescription(Integer familyDescriptionId);
    List<DescriptionFamily> listFamilyDescriptions();
    DescriptionFamily getFamilyDescription(Integer familyDescriptionId);
    List<DescriptionFamily> getFamilyDescriptionAspirant(Integer familyDescriptionId);
}

