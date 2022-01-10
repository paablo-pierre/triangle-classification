package com.cocus.triangleclassification.fixture

import com.cocus.triangleclassification.domain.Triangle
import com.cocus.triangleclassification.domain.builder.GenericBuilder
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
                .with(Triangle::setSideA, 2)
                .with(Triangle::setSideB, 2)
                .with(Triangle::setSideC, 2)
                .with(Triangle::setTriangleType, TriangleTypeEnum.SCALENE)
                .build()
    }
}
