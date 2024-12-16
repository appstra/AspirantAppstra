package com.appstra.aspirante.implementation;

import com.appstra.aspirante.dto.AnswerEvaluationDTO;
import com.appstra.aspirante.entity.Ask;
import com.appstra.aspirante.entity.Evaluation;
import com.appstra.aspirante.entity.Response;
import com.appstra.aspirante.entity.ResponseEvaluation;
import com.appstra.aspirante.repository.AskRepository;
import com.appstra.aspirante.repository.EvaluationRepository;
import com.appstra.aspirante.repository.ResponseEvaluationRepository;
import com.appstra.aspirante.repository.ResponseRepository;
import com.appstra.aspirante.service.ResponseEvaluationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ResponseEvaluationImpl implements ResponseEvaluationService {
    private final ResponseEvaluationRepository responseEvaluationRepository;
    private final AskRepository askRepository;
    private final ResponseRepository responseRepository;

    public ResponseEvaluationImpl(ResponseEvaluationRepository responseEvaluationRepository, AskRepository askRepository, ResponseRepository responseRepository, EvaluationRepository evaluationRepository) {
        this.responseEvaluationRepository = responseEvaluationRepository;
        this.askRepository = askRepository;
        this.responseRepository = responseRepository;
    }

    @Override
    public Boolean saveResponseEvaluation(List<AnswerEvaluationDTO> answerEvaluationDTO) {

        for (AnswerEvaluationDTO itera : answerEvaluationDTO) {
            ResponseEvaluation responseEvaluation = new ResponseEvaluation();
            // Inicializa las propiedades anidadas
            responseEvaluation.setEvaluation(new Evaluation());
            responseEvaluation.setAsk(new Ask());
            responseEvaluation.setResponse(new Response());

            // Guarda fecha
            responseEvaluation.setResponseEvaluationCreationDate(Timestamp.valueOf(LocalDateTime.now()));
            responseEvaluation.setResponseEvaluationEditDate(Timestamp.valueOf(LocalDateTime.now()));

            // Guarda usuario y id evaluacion
            //responseEvaluation.setResponseEvaluationEditUserId(1);
            responseEvaluation.getEvaluation().setEvaluationId(itera.getEvaluationId());

            // Guarda Id de Pregunta
            Integer askId = askRepository.findByAskAsk(itera.getName()).getAskId();
            if (askId != null) {
                responseEvaluation.getAsk().setAskId(askId);
                Integer responseId = responseRepository.findByAskAskIdAndResponseAnswer(askId, String.valueOf(itera.getSelected())).getResponseId();
                if (responseId != null) {
                    responseEvaluation.getResponse().setResponseId(responseId);
                    responseEvaluationRepository.save(responseEvaluation);
                } else {
                    throw new IllegalArgumentException("El responseId es nulo. Error al Guardar");
                }
            } else {
                throw new IllegalArgumentException("El askId es nulo. Error al Guardar");
            }
        }

        return true;

    }

    @Override
    public ResponseEvaluation updateResponseEvaluation(ResponseEvaluation responseEvaluation) {
        ResponseEvaluation existingResponseEvaluation = responseEvaluationRepository.findById(responseEvaluation.getResponseEvaluationId())
                .orElseThrow(() -> new IllegalArgumentException("La evaluación de respuesta no existe: " + responseEvaluation.getResponseEvaluationId()));
        responseEvaluation.setResponseEvaluationCreationDate(existingResponseEvaluation.getResponseEvaluationCreationDate());
        responseEvaluation.setResponseEvaluationEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return responseEvaluationRepository.save(responseEvaluation);
    }

    @Override
    public List<ResponseEvaluation> listResponseEvaluations() {
        return responseEvaluationRepository.findAll();
    }

    @Override
    public ResponseEvaluation getResponseEvaluation(Integer responseEvaluationId) {
        return responseEvaluationRepository.findById(responseEvaluationId)
                .orElseThrow(() -> new NoSuchElementException("La evaluación de respuesta con el ID: " + responseEvaluationId + " no se encontró"));
    }
}

