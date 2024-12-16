package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.Ask;

import java.util.List;

public interface AskService {
    Ask saveAsk(Ask ask);
    Ask updateAsk(Ask ask);
    Boolean deleteAsk(Integer askId);
    List<Ask> listAsk();
    Ask getAsk(Integer askId);
}
