package com.appstra.aspirante.implementation;

import com.appstra.aspirante.dto.AnswerEvaluationDTO;
import com.appstra.aspirante.dto.QualificationEvaluationDTO;
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
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ResponseEvaluationImpl implements ResponseEvaluationService {
    private final ResponseEvaluationRepository responseEvaluationRepository;
    private final AskRepository askRepository;
    private final ResponseRepository responseRepository;
    private final EvaluationImpl evaluationImpl;

    public ResponseEvaluationImpl(ResponseEvaluationRepository responseEvaluationRepository, AskRepository askRepository, ResponseRepository responseRepository, EvaluationRepository evaluationRepository, EvaluationRepository evaluationRepository1, EvaluationImpl evaluationImpl) {
        this.responseEvaluationRepository = responseEvaluationRepository;
        this.askRepository = askRepository;
        this.responseRepository = responseRepository;
        this.evaluationImpl = evaluationImpl;
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
            try{
                Integer askId = askRepository.findByAskAsk(itera.getName()).getAskId();
                if (askId != null) {
                    responseEvaluation.getAsk().setAskId(askId);
                    Integer responseId = responseRepository.findByAskAskIdAndResponseAnswer(askId, itera.getSelected()).getResponseId();
                    if (responseId != null) {
                        responseEvaluation.getResponse().setResponseId(responseId);
                        responseEvaluationRepository.save(responseEvaluation);
                    }
                }
            }catch (Exception e){
                throw new IllegalArgumentException("Error con la pregunta: " + itera.getName());
            }
        }
        //actualiza el estado de la evaluacion
        if(evaluationImpl.finallyEvaliation(answerEvaluationDTO.get(0).getEvaluationId())){
            Evaluation evaluation = evaluationImpl.getEvaluation(answerEvaluationDTO.get(0).getEvaluationId());
            evaluation.setStateId(5);
            evaluationImpl.updateEvaluation(evaluation);
        }else{
            System.out.println("No ha terminado en su totalidad la prueba");
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

    @Override
    public Map<String, Object> QualificationEvaluationPersonalidad(Integer evaluationId) {
        return responseEvaluationRepository.QualificationEvaluationPersonalidad(evaluationId);
    }

    @Override
    public List<Map<String, Object>> qualificationEvaluationPersonId(Integer personId) {
        return responseEvaluationRepository.QualificationEvaluationPersonId(personId);
    }

    @Override
    public Map<String, Object> QualificationEvaluationPersonalidadPersonId(Integer personId) {
        return responseEvaluationRepository.QualificationEvaluationPersonalidadPersonId(personId);
    }
}

