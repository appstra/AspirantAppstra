package com.appstra.aspirante.implementation;


import com.appstra.aspirante.entity.Ask;
import com.appstra.aspirante.repository.AskRepository;
import com.appstra.aspirante.service.AskService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AskImpl implements AskService {
    private final AskRepository askRepository;

    public AskImpl(AskRepository askRepository) {
        this.askRepository = askRepository;
    }

    @Override
    public Ask saveAsk(Ask ask) {
        ask.setAskCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        ask.setAskEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return askRepository.save(ask);
    }

    @Override
    public Ask updateAsk(Ask ask) {
        Ask existingAsk = askRepository.findById(ask.getAskId())
                .orElseThrow(() -> new IllegalArgumentException("La pregunta no existe: " + ask.getAskId()));
        ask.setAskCreationDate(existingAsk.getAskCreationDate());
        return askRepository.save(existingAsk);
    }

    @Override
    public Boolean deleteAsk(Integer askId) {
        if (askRepository.existsById(askId)) {
            askRepository.deleteById(askId);
            return true;
        }
        return false;
    }

    @Override
    public List<Ask> listAsk() {
        return askRepository.findAll();
    }

    @Override
    public Ask getAsk(Integer askId) {
        return askRepository.findById(askId)
                .orElseThrow(() -> new NoSuchElementException("La pregunta con el ID: " + askId + " no se encontró"));
    }
}

