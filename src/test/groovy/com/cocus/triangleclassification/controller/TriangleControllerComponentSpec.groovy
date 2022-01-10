package com.cocus.triangleclassification.controller

import com.cocus.triangleclassification.domain.dto.TriangleRequestDTO
import com.cocus.triangleclassification.domain.dto.TriangleResponseDTO
import com.cocus.triangleclassification.domain.dto.TriangleTypeEnum
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class TriangleControllerComponentSpec extends Specification{

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    private String user = "admin"

    private String password = "cm9sZV91c2Vy"

    private String baseUrl = "/api/v1/triangle"

    def "Given I call the path list should return all triangles saved on database"() {

        when: "I called a GET endpoint"
        def response = mockMvc.perform(get(baseUrl+"/list")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(user, password)))
                .andReturn()

        then: "should return a http status 200 OK"
        HttpStatus.OK.value() equals(response.getResponse().getStatus())
    }

    def "Given I call the path to save a new triangle should return the type of triangle"() {
        given: "I have three sides"

        def request = TriangleRequestDTO.builder()
                .sideA(10)
                .sideB(8)
                .sideC(9)
                .build()

        def bodyAsString = objectMapper.writeValueAsString(request);

        when: "I called the post endpoint"
        def response = mockMvc.perform(post(baseUrl)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(user, password))
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyAsString))
                .andReturn()

        then: "should return a http status 201"
        HttpStatus.CREATED.value() equals(response.getResponse().getStatus())

        and: "the triangle type equals SCALENE"
        var contentAsString = response.getResponse().getContentAsString();
        var responseTriangle = objectMapper.readValue(contentAsString, TriangleResponseDTO.class);

        responseTriangle.getData() != null
        responseTriangle.getData().size() == 1
        responseTriangle.getData().get(0).triangleType.equals TriangleTypeEnum.SCALENE.name()

    }

    def "Given I call the path to save a new triangle and send wrong values should return an Exception"() {
        given: "I have three sides"

        def request = TriangleRequestDTO.builder()
                .sideA(7)
                .sideB(2)
                .sideC(3)
                .build()

        def bodyAsString = objectMapper.writeValueAsString(request);

        when: "I called the post endpoint"
        def response = mockMvc.perform(post(baseUrl)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(user, password))
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyAsString))
                .andReturn()

        then: "should return a http status 400"
        HttpStatus.BAD_REQUEST.value() equals(response.getResponse().getStatus())

    }

    def "Given I call the path to save a new triangle and send a value equals 0 should return an Exception"() {
        given: "I have three sides"

        def request = TriangleRequestDTO.builder()
                .sideA(0)
                .sideB(2)
                .sideC(3)
                .build()

        def bodyAsString = objectMapper.writeValueAsString(request);

        when: "I called the post endpoint"
        def response = mockMvc.perform(post(baseUrl)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(user, password))
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyAsString))
                .andReturn()

        then: "should return a http status 400"
        HttpStatus.BAD_REQUEST.value() equals(response.getResponse().getStatus())

    }

    def "Given I call the path to save a new triangle and send three values without authentication should return an Exception"() {
        given: "I have three sides"

        def request = TriangleRequestDTO.builder()
                .sideA(0)
                .sideB(2)
                .sideC(3)
                .build()

        def bodyAsString = objectMapper.writeValueAsString(request);

        when: "I called the post endpoint"
        def response = mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyAsString))
                .andReturn()

        then: "should return a http status 401"
        HttpStatus.UNAUTHORIZED.value() equals(response.getResponse().getStatus())

    }

}
