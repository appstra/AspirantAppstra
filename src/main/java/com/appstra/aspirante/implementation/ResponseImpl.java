package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.Response;
import com.appstra.aspirante.repository.ResponseRepository;
import com.appstra.aspirante.service.ResponseService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ResponseImpl implements ResponseService {
    private final ResponseRepository responseRepository;

    public ResponseImpl(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public Response saveResponse(Response response) {
        response.setResponseCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        response.setResponseEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return responseRepository.save(response);
    }

    @Override
    public Response updateResponse(Response response) {
        Response existingResponse = responseRepository.findById(response.getResponseId())
                .orElseThrow(() -> new IllegalArgumentException("La respuesta no existe: " + response.getResponseId()));
        response.setResponseCreationDate(existingResponse.getResponseCreationDate());
        response.setResponseEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return responseRepository.save(response);
    }

    @Override
    public Boolean deleteResponse(Integer responseId) {
        if (responseRepository.existsById(responseId)) {
            responseRepository.deleteById(responseId);
            return true;
        }
        return false;
    }

    @Override
    public List<Response> listResponses() {
        return responseRepository.findAll();
    }

    @Override
    public Response getResponse(Integer responseId) {
        return responseRepository.findById(responseId)
                .orElseThrow(() -> new NoSuchElementException("La respuesta con el ID: " + responseId + " no se encontró"));
    }

    @Override
    public List<Response> findByAskId(Integer AskId) {
        return responseRepository.findByAskAskId(AskId);
    }
}

