package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.TypeTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TypeTestRepository extends JpaRepository<TypeTest,Integer> {
    List<TypeTest> findByStatIdNot(Integer stateId);
    @Query(value = """
        select
            tyte.tyte_id as "typeTestId",
            tyte.tyte_name as "typeTestName",
            comp.comp_id as "competenceId",
            comp.comp_name as "competenceName",
            ask.ask_id as "askId",
            ask.ask_ask as "askAsk",
            ask.ask_type as "askType",
            resp.resp_id as "responseId",
            resp.resp_answer as "responseAnswer"
        from
            parameterization.type_test tyte
            inner join parameterization.competence comp on tyte.tyte_id = comp.tyte_id
            inner join parameterization.ask ask on comp.comp_id = ask.comp_id
            left join parameterization.response resp on ask.ask_id = resp.ask_id
        where
            tyte.tyte_id = ?1 """,nativeQuery = true)
    List<Map<String, Object>> getFullTypeTests (@Param("typeTestId") Integer typeTestId);

}
