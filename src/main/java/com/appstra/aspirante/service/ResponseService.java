package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.Response;

import java.util.List;

public interface ResponseService {
    Response saveResponse(Response response);
    Response updateResponse(Response response);
    Boolean deleteResponse(Integer responseId);
    List<Response> listResponses();
    Response getResponse(Integer responseId);
    List<Response> findByAskId(Integer AskId);
}
