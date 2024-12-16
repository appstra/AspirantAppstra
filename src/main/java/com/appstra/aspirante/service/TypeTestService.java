package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.TypeTest;

import java.util.List;

public interface TypeTestService {
    TypeTest saveTypeTest(TypeTest typeTest);
    TypeTest updateTypeTest(TypeTest typeTest);
    Boolean deleteTypeTest(Integer typeTestId);
    List<TypeTest> listTypeTests();
    TypeTest getTypeTest(Integer typeTestId);
}

