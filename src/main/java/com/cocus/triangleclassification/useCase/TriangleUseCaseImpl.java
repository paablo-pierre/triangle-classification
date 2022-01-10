package com.cocus.triangleclassification.useCase;

import com.cocus.triangleclassification.domain.Triangle;
import com.cocus.triangleclassification.domain.dto.TriangleRequestDTO;
import com.cocus.triangleclassification.domain.dto.TriangleResponseDTO;
import com.cocus.triangleclassification.domain.dto.TriangleTypeEnum;
import com.cocus.triangleclassification.exception.ServiceException;
import com.cocus.triangleclassification.interfaces.usecase.TriangleUseCase;
import com.cocus.triangleclassification.interfaces.repository.TriangleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TriangleUseCaseImpl implements TriangleUseCase {

    private final TriangleRepository triangleRepository;

    @Override
    public TriangleResponseDTO execute(TriangleRequestDTO triangleRequestDTO) {
        log.info("Start to execute TriangleUseCaseImpl.execute() with params {}", triangleRequestDTO);

        if(isNotTriangle(triangleRequestDTO) || validateSize(triangleRequestDTO)) {
            log.error("The information doesnt match a triangle");
            throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "The informations doesnt match a triangle");
        }

        var triangle = validateTriangle(triangleRequestDTO);

        if(Objects.isNull(triangle)) {
            log.error("Cant possible to validate if is triangle");
            throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Does not possible validate if is a triangle");
        }

        triangle = triangleRepository.save(triangle);

        log.info("Finished to execute TriangleUseCaseImpl.execute");

        return TriangleResponseDTO.toTriangleTypeDTO(Collections.singletonList(triangle));
    }

    private boolean validateSize(TriangleRequestDTO triangleRequestDTO) {
        if (triangleRequestDTO.getSideA() <= 0
                || triangleRequestDTO.getSideB() <= 0
                || triangleRequestDTO.getSideC() <= 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    @Override
    public TriangleResponseDTO getAllTriangles() {
        log.info("Start to search in database");
        var triangle = triangleRepository.findAll();

        log.info("Finished to search in database with length {}", triangle.size());
        return TriangleResponseDTO.toDTO(triangle);
    }

    private Triangle validateTriangle(TriangleRequestDTO triangleRequestDTO) {

        if(triangleRequestDTO.getSideA().equals(triangleRequestDTO.getSideB())
                && triangleRequestDTO.getSideB().equals(triangleRequestDTO.getSideC())) {
            log.info("Validate if is a equilateral triangle");

            return Triangle.builder()
                    .sideA(triangleRequestDTO.getSideA())
                    .sideB(triangleRequestDTO.getSideB())
                    .sideC(triangleRequestDTO.getSideC())
                    .triangleType(TriangleTypeEnum.EQUILATERAL)
                    .build();
        } else if(Objects.equals(triangleRequestDTO.getSideA(), triangleRequestDTO.getSideB())
                || Objects.equals(triangleRequestDTO.getSideB(), triangleRequestDTO.getSideC())
                || Objects.equals(triangleRequestDTO.getSideC(), triangleRequestDTO.getSideA())) {

            log.info("Validate if is a isosceles triangle");

            return Triangle.builder()
                    .sideA(triangleRequestDTO.getSideA())
                    .sideB(triangleRequestDTO.getSideB())
                    .sideC(triangleRequestDTO.getSideC())
                    .triangleType(TriangleTypeEnum.ISOSCELES)
                    .build();

        } else if(!Objects.equals(triangleRequestDTO.getSideB(), triangleRequestDTO.getSideA())
                || !Objects.equals(triangleRequestDTO.getSideA(), triangleRequestDTO.getSideC())
                || !Objects.equals(triangleRequestDTO.getSideC(), triangleRequestDTO.getSideB())) {

            log.info("Validate if is a scalene triangle");

            return Triangle.builder()
                    .sideA(triangleRequestDTO.getSideA())
                    .sideB(triangleRequestDTO.getSideB())
                    .sideC(triangleRequestDTO.getSideC())
                    .triangleType(TriangleTypeEnum.SCALENE)
                    .build();
        }
        return null;
    }

    private boolean isNotTriangle(TriangleRequestDTO triangleRequestDTO) {

        if(triangleRequestDTO.getSideA() + triangleRequestDTO.getSideB() < triangleRequestDTO.getSideC()
                || triangleRequestDTO.getSideA() + triangleRequestDTO.getSideC() < triangleRequestDTO.getSideB()
                || triangleRequestDTO.getSideB() + triangleRequestDTO.getSideC() < triangleRequestDTO.getSideA()) {
           return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
