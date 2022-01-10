    package com.cocus.triangleclassification.controller;

import com.cocus.triangleclassification.domain.dto.TriangleRequestDTO;
import com.cocus.triangleclassification.domain.dto.TriangleResponseDTO;
import com.cocus.triangleclassification.interfaces.usecase.TriangleUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/triangle")
@RequiredArgsConstructor
public class TriangleController {

    private final TriangleUseCase triangleUseCase;

    @ApiOperation(value = "Path to list all triangles saved")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Returns empty theres is not data"),
            @ApiResponse(code = 200, message = "Returns an array with data saved"),
            @ApiResponse(code = 400, message = "An exception was thrown"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "URL not found"),
    })
    @GetMapping(path = "/list", produces="application/json")
    public ResponseEntity<TriangleResponseDTO> getAllTriangles() {
        var triangle = triangleUseCase.getAllTriangles();

        return ResponseEntity.ok(triangle);
    }

    @ApiOperation(value = "Path to save a triangle")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns a created status with triangle type"),
            @ApiResponse(code = 400, message = "An exception was thrown"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "URL not found"),
    })
    @PostMapping
    public ResponseEntity<TriangleResponseDTO> saveTriangle(@RequestBody TriangleRequestDTO triangleRequestDTO) {
        var triangle = triangleUseCase.execute(triangleRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(triangle);
    }
}
