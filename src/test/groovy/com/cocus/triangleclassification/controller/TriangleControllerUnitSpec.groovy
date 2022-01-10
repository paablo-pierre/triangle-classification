package com.cocus.triangleclassification.controller

import com.cocus.triangleclassification.controller.TriangleController
import com.cocus.triangleclassification.domain.dto.TriangleRequestDTO
import com.cocus.triangleclassification.domain.dto.TriangleTypeEnum
import com.cocus.triangleclassification.exception.ServiceException
import com.cocus.triangleclassification.fixture.TriangleRepositoryTemplate
import com.cocus.triangleclassification.interfaces.usecase.TriangleUseCase
import org.springframework.http.HttpStatus
import spock.lang.Specification

class TriangleControllerUnitSpec extends Specification {

    TriangleUseCase triangleUseCase = Mock()
    TriangleController controller

    def setup() {
        controller = new TriangleController(triangleUseCase)
    }

    def "Given I call the path list should return all triangles saved on database"() {
        given: "I have the triangles saved on database"
        1 * triangleUseCase.getAllTriangles() >> TriangleRepositoryTemplate.listAllTriangles()

        when: "I called a GET endpoint"
        def response = controller.getAllTriangles()

        then: "should return a http status 200 OK"
        assert response
        assert response.getStatusCode() == HttpStatus.OK

        def body = response.getBody()

        assert body.getData().size() == 3

        verifyAll (body.getData().get(0)) {
            it.triangleType.equals TriangleTypeEnum.SCALENE.name()
            it.sideA == 10
            it.sideB == 8
            it.sideC == 9
        }
    }

    def "Given I call the path to save a new triangle should return the type of triangle"() {
        given: "I have three sides"

        def request = TriangleRequestDTO.builder()
                .sideA(10)
                .sideB(8)
                .sideC(9)
                .build()
        1 * triangleUseCase.execute(request) >> TriangleRepositoryTemplate.returnTriangleSaved()
        when: "I called the post endpoint"
        def response = controller.saveTriangle(request)

        then: "should return a http status 201"
        HttpStatus.CREATED == response.getStatusCode()

        and: "the triangle type equals SCALENE"

        def body = response.getBody()

        assert body.getData().size() == 1

        verifyAll (body.getData().get(0)) {
            it.triangleType.equals TriangleTypeEnum.SCALENE.name()
        }

    }

    def "Given I call the path to save a new triangle and send wrong values should return an Exception"() {
        given: "I have three sides"

        def request = TriangleRequestDTO.builder()
                .sideA(7)
                .sideB(2)
                .sideC(0)
                .build()

        1 * triangleUseCase.execute(request) >> { throw new ServiceException(_ as String, _ as String) }

        when: "I called the post endpoint"
        controller.saveTriangle(request)

        then: "should return a http status 400"
        thrown(ServiceException)

    }

}
