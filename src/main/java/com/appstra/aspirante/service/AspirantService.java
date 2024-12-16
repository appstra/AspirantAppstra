package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.Aspirant;

import java.util.List;

public interface AspirantService {
    Aspirant saveAspirant(Aspirant aspirant);
    Aspirant updateAspirant(Aspirant aspirant);
    Boolean deleteAspirant(Integer aspirantId);
    List<Aspirant> listAspirants();
    Aspirant getAspirant(Integer aspirantId);
}

