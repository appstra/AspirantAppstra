package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.TypeTest;
import com.appstra.aspirante.repository.TypeTestRepository;
import com.appstra.aspirante.service.TypeTestService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TypeTestImpl implements TypeTestService {
    private final TypeTestRepository typeTestRepository;

    public TypeTestImpl(TypeTestRepository typeTestRepository) {
        this.typeTestRepository = typeTestRepository;
    }

    @Override
    public TypeTest saveTypeTest(TypeTest typeTest) {
        typeTest.setTypeTestCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        typeTest.setTypeTestEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return typeTestRepository.save(typeTest);
    }

    @Override
    public TypeTest updateTypeTest(TypeTest typeTest) {
        TypeTest existingTypeTest = typeTestRepository.findById(typeTest.getTypeTestId())
                .orElseThrow(() -> new IllegalArgumentException("El tipo de test no existe: " + typeTest.getTypeTestId()));
        typeTest.setTypeTestCreationDate(existingTypeTest.getTypeTestCreationDate());
        typeTest.setTypeTestEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return typeTestRepository.save(typeTest);
    }

    @Override
    public Boolean deleteTypeTest(Integer typeTestId) {
        if (typeTestRepository.existsById(typeTestId)) {
            TypeTest typeTest = typeTestRepository.findById(typeTestId)
                    .orElseThrow(() -> new IllegalArgumentException("El tipo de test no existe: " + typeTestId));
            typeTest.setStatId(13);
            typeTest.setTypeTestEditDate(Timestamp.valueOf(LocalDateTime.now()));

            typeTestRepository.save(typeTest);
            return true;
        }
        return false;
    }

    @Override
    public List<TypeTest> listTypeTests() {
        return typeTestRepository.findByStatIdNot(13);
    }

    @Override
    public TypeTest getTypeTest(Integer typeTestId) {
        return typeTestRepository.findById(typeTestId)
                .orElseThrow(() -> new NoSuchElementException("El tipo de test con el ID: " + typeTestId + " no se encontró"));
    }
}

