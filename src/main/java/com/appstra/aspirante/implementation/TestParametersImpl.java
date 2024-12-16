package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.TestParameters;
import com.appstra.aspirante.repository.TestParametersRepository;
import com.appstra.aspirante.service.TestParametersService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TestParametersImpl implements TestParametersService {
    private final TestParametersRepository testParametersRepository;

    public TestParametersImpl(TestParametersRepository testParametersRepository) {
        this.testParametersRepository = testParametersRepository;
    }

    @Override
    public TestParameters saveTestParameters(TestParameters testParameters) {
        testParameters.setTestParametersCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        testParameters.setTestParametersEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return testParametersRepository.save(testParameters);
    }

    @Override
    public TestParameters updateTestParameters(TestParameters testParameters) {
        TestParameters existingTestParameters = testParametersRepository.findById(testParameters.getTestParametersId())
                .orElseThrow(() -> new IllegalArgumentException("Los parámetros del test no existen: " + testParameters.getTestParametersId()));
        testParameters.setTestParametersCreationDate(existingTestParameters.getTestParametersCreationDate());
        return testParametersRepository.save(testParameters);
    }

    @Override
    public Boolean deleteTestParameters(Integer testParametersId) {
        if (testParametersRepository.existsById(testParametersId)) {
            testParametersRepository.deleteById(testParametersId);
            return true;
        }
        return false;
    }

    @Override
    public List<TestParameters> listTestParameters() {
        return testParametersRepository.findAll();
    }

    @Override
    public TestParameters getTestParameters(Integer testParametersId) {
        return testParametersRepository.findById(testParametersId)
                .orElseThrow(() -> new NoSuchElementException("Los parámetros del test con el ID: " + testParametersId + " no se encontraron"));
    }
}
