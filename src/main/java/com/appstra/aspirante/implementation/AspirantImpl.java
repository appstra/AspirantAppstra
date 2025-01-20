package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.Aspirant;
import com.appstra.aspirante.repository.AspirantRepository;
import com.appstra.aspirante.service.AspirantService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AspirantImpl implements AspirantService {
    public static final int CONTRATADO = 8;
    public static final int INACTIVO = 11;

    private final AspirantRepository aspirantRepository;

    public AspirantImpl(AspirantRepository aspirantRepository) {
        this.aspirantRepository = aspirantRepository;
    }

    @Override
    public Aspirant saveAspirant(Aspirant aspirant) {
        aspirant.setAspirantCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        aspirant.setAspirantEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return aspirantRepository.save(aspirant);
    }

    @Override
    public Aspirant updateAspirant(Aspirant aspirant) {
        Aspirant existingAspirant = aspirantRepository.findById(aspirant.getAspirantId())
                .orElseThrow(() -> new IllegalArgumentException("El aspirante no existe: " + aspirant.getAspirantId()));
        aspirant.setAspirantCreationDate(existingAspirant.getAspirantCreationDate());
        aspirant.setAspirantEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return aspirantRepository.save(aspirant);
    }

    @Override
    public Boolean deleteAspirant(Integer aspirantId) {
        if (aspirantRepository.existsById(aspirantId)) {
            Aspirant aspirant = aspirantRepository.findById(aspirantId)
                    .orElseThrow(() -> new IllegalArgumentException("El aspirante no existe: " + aspirantId));
            aspirant.setStateId(this.INACTIVO);
            aspirant.setAspirantEditDate(Timestamp.valueOf(LocalDateTime.now()));
            aspirantRepository.save(aspirant);
            return true;
        }
        return false;
    }

    @Override
    public List<Aspirant> listAspirants() {
        List<Integer> excludedIds = Arrays.asList(this.CONTRATADO, this.INACTIVO);
        return aspirantRepository.findByStateIdNotIn(excludedIds);
    }

    @Override
    public Aspirant getAspirant(Integer aspirantId) {
        return aspirantRepository.findById(aspirantId)
                .orElseThrow(() -> new NoSuchElementException("El aspirante con el ID: " + aspirantId + " no se encontró"));
    }

    @Override
    public Aspirant findByAspirantPersonId(Integer aspirantId) {
        Aspirant aspirant = aspirantRepository.findByPersonId(aspirantId);
        if (aspirant != null) {
            return aspirant;
        } else {
            throw new RuntimeException("El aspirante con el ID: " + aspirantId + " no se encontró");
        }
    }

    @Override
    public List<Aspirant> findByStateId(Integer stateId) {
        return aspirantRepository.findByStateId(stateId);
    }


}

