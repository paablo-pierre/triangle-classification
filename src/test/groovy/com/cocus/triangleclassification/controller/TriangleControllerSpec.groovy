package com.cocus.triangleclassification.controller

import com.cocus.triangleclassification.controller.TriangleController
import com.cocus.triangleclassification.interfaces.repository.TriangleRepository
import com.cocus.triangleclassification.interfaces.usecase.TriangleUseCase
import com.cocus.triangleclassification.useCase.TriangleUseCaseImpl
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import spock.lang.Specification

class TriangleControllerSpec extends Specification {

    TriangleUseCase triangleUseCase = Mock()
    TriangleRepository triangleRepository = Mock()

    def setup() {
        triangleUseCase = new TriangleUseCaseImpl(triangleRepository)
    }
}
