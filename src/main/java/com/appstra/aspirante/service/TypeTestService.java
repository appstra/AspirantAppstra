package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.TypeTest;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TypeTestService {
    TypeTest saveTypeTest(TypeTest typeTest);
    TypeTest updateTypeTest(TypeTest typeTest);
    Boolean deleteTypeTest(Integer typeTestId);
    List<TypeTest> listTypeTests();
    TypeTest getTypeTest(Integer typeTestId);
    List<Map<String, Object>> getFullTypeTests (Integer typeTestId);
    List<Map<String, Object>> getFullTypeTestsEvaluation (Integer evaluationId, Integer typeTestId);
}

