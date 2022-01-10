package com.cocus.triangleclassification.fixture

import com.cocus.triangleclassification.domain.Triangle
import com.cocus.triangleclassification.domain.builder.GenericBuilder
import com.cocus.triangleclassification.domain.dto.TriangleDTO
import com.cocus.triangleclassification.domain.dto.TriangleResponseDTO
import com.cocus.triangleclassification.domain.dto.TriangleTypeEnum

class TriangleRepositoryTemplate {

    static Triangle buildIsoscelesTriangle() {
        return GenericBuilder.of(Triangle::new)
                .with(Triangle::setSideA, 10)
                .with(Triangle::setSideB, 10)
                .with(Triangle::setSideC, 8)
                .with(Triangle::setTriangleType, TriangleTypeEnum.ISOSCELES)
                .build()
    }

    static Triangle buildEquilateralTriangle() {
        return GenericBuilder.of(Triangle::new)
                .with(Triangle::setSideA, 2)
                .with(Triangle::setSideB, 2)
                .with(Triangle::setSideC, 2)
                .with(Triangle::setTriangleType, TriangleTypeEnum.EQUILATERAL)
                .build()
    }

    static Triangle buildScaleneTriangle() {
        return GenericBuilder.of(Triangle::new)
                .with(Triangle::setSideA, 10)
                .with(Triangle::setSideB, 8)
                .with(Triangle::setSideC, 9)
                .with(Triangle::setTriangleType, TriangleTypeEnum.SCALENE)
                .build()
    }

    static TriangleResponseDTO listAllTriangles() {
        def triangles = Arrays.asList(buildScaleneTriangle(), buildEquilateralTriangle(), buildIsoscelesTriangle())

        return TriangleResponseDTO.toDTO(triangles)
    }

    static TriangleResponseDTO returnTriangleSaved() {
        return TriangleResponseDTO.toTriangleTypeDTO(Collections.singletonList(buildScaleneTriangle()))
    }
}
