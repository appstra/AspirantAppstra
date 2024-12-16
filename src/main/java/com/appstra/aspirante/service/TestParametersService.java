package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.TestParameters;

import java.util.List;

public interface TestParametersService {
    TestParameters saveTestParameters(TestParameters testParameters);
    TestParameters updateTestParameters(TestParameters testParameters);
    Boolean deleteTestParameters(Integer testParametersId);
    List<TestParameters> listTestParameters();
    TestParameters getTestParameters(Integer testParametersId);
}

