package com.cocus.triangleclassification.controller;

import com.cocus.triangleclassification.domain.dto.TriangleRequestDTO;
import com.cocus.triangleclassification.domain.dto.TriangleResponseDTO;
import com.cocus.triangleclassification.interfaces.usecase.TriangleUseCase;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/list")
    public ResponseEntity<TriangleResponseDTO> getAllTriangles() {
        return ResponseEntity.ok(triangleUseCase.getAllTriangles());
    }

    @PostMapping
    public ResponseEntity<TriangleResponseDTO> saveTriangle(@RequestBody TriangleRequestDTO triangleRequestDTO) {
        var triangle = triangleUseCase.execute(triangleRequestDTO);

        return ResponseEntity.ok(triangle);
    }
}
