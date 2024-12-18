package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.Ask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AskRepository extends JpaRepository<Ask,Integer> {
    /**
     * Busca el objeto de la pregunta por el parametro pregunta
     * @param askAsk
     * @return Objeto Ask
     */
    Ask findByAskAsk (String askAsk);
}
