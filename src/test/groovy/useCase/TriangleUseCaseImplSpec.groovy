package useCase

import com.cocus.triangleclassification.domain.Triangle
import com.cocus.triangleclassification.domain.dto.TriangleRequestDTO
import com.cocus.triangleclassification.domain.dto.TriangleTypeEnum
import com.cocus.triangleclassification.exception.ServiceException
import com.cocus.triangleclassification.interfaces.usecase.TriangleUseCase
import com.cocus.triangleclassification.interfaces.repository.TriangleRepository
import com.cocus.triangleclassification.useCase.TriangleUseCaseImpl
import fixture.TriangleRepositoryTemplate
import spock.lang.Specification

class TriangleUseCaseImplSpec extends Specification {

    TriangleRepository triangleRepository = Mock()
    TriangleUseCase triangleUseCase

    def setup() {
        triangleUseCase = new TriangleUseCaseImpl(triangleRepository)
    }

    def "Given I have three equals numbers the return should be a Equilateral Triangle"() {
        given: "given I have three numbers"
        def sideA = 2
        def sideB = 2
        def sideC = 2

        def triangle = TriangleRequestDTO.builder()
                .sideA(sideA)
                .sideB(sideB)
                .sideC(sideC)
                .build()
        and: "a repository mock"
        triangleRepository.save(_ as Triangle) >> TriangleRepositoryTemplate.buildEquilateralTriangle()

        when: "I called the useCase"
        def isosceles = triangleUseCase.execute(triangle)

        then: "should return a triangle type"
        isosceles.getData().get(0).getTriangleType().equals TriangleTypeEnum.EQUILATERAL.name()
    }

    def "Given I have one different number the return should be a Isosceles Triangle"() {
        given: "given I have three numbers"
        def sideA = 2
        def sideB = 8
        def sideC = 8

        def triangle = TriangleRequestDTO.builder()
                .sideA(sideA)
                .sideB(sideB)
                .sideC(sideC)
                .build()
        and: "a repository mock"
        triangleRepository.save(_ as Triangle) >> TriangleRepositoryTemplate.buildIsoscelesTriangle()

        when: "I called the useCase"
        def isosceles = triangleUseCase.execute(triangle)

        then: "should return a triangle type"
        isosceles.getData().get(0).getTriangleType().equals TriangleTypeEnum.ISOSCELES.name()
    }

    def "Given I have three different numbers the return should be a Scanele Triangle"() {
        given: "given I have three numbers"
        def sideA = 5
        def sideB = 7
        def sideC = 10

        def triangle = TriangleRequestDTO.builder()
                .sideA(sideA)
                .sideB(sideB)
                .sideC(sideC)
                .build()
        and: "a repository mock"
        triangleRepository.save(_ as Triangle) >> TriangleRepositoryTemplate.buildScaleneTriangle()

        when: "I called the useCase"
        def isosceles = triangleUseCase.execute(triangle)

        then: "should return a triangle type"
        isosceles.getData().get(0).getTriangleType().equals TriangleTypeEnum.SCALENE.name()
    }

    def "Given the sum between sideA and sideB less than sideC should thrown a ServiceException"() {
        given: "given I have three numbers"
        def sideA = 2
        def sideB = 3
        def sideC = 8

        def triangle = TriangleRequestDTO.builder()
                .sideA(sideA)
                .sideB(sideB)
                .sideC(sideC)
                .build()

        when: "I validate if is a triangle"
        triangleUseCase.execute(triangle)

        then: "should thrown a ServiceException"
        thrown(ServiceException)
    }

    def "Given I have one of the sides equals 0 then should thrown a ServiceException"() {
        given: "given I have three numbers"
        def sideA = 2
        def sideB = 9

        and: "one of then equals 0"
        def sideC = 0

        def triangle = TriangleRequestDTO.builder()
                .sideA(sideA)
                .sideB(sideB)
                .sideC(sideC)
                .build()

        when: "I validate if is a triangle"
        triangleUseCase.execute(triangle)

        then: "should thrown a ServiceException"
        thrown(ServiceException)
    }

    def "Given the sum between sideA and sideC less than sideB should thrown a ServiceException"() {
        given: "given I have three numbers"
        def sideA = 2
        def sideB = 9
        def sideC = 5

        def triangle = TriangleRequestDTO.builder()
                .sideA(sideA)
                .sideB(sideB)
                .sideC(sideC)
                .build()

        when: "I validate if is a triangle"
        triangleUseCase.execute(triangle)

        then: "should thrown a ServiceException"
        thrown(ServiceException)
    }

    def "Given the sum between sideB and sideC less than sideA should thrown a ServiceException"() {
        given: "given I have three numbers"
        def sideA = 10
        def sideB = 1
        def sideC = 8

        def triangle = TriangleRequestDTO.builder()
                .sideA(sideA)
                .sideB(sideB)
                .sideC(sideC)
                .build()

        when: "I validate if is a triangle"
        triangleUseCase.execute(triangle)

        then: "should thrown a ServiceException"
        thrown(ServiceException)
    }
}
