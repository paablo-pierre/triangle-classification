package com.cocus.triangleclassification.interfaces.usecase;

import com.cocus.triangleclassification.domain.dto.TriangleRequestDTO;
import com.cocus.triangleclassification.domain.dto.TriangleResponseDTO;

public interface TriangleUseCase {
    TriangleResponseDTO execute(TriangleRequestDTO triangleRequestDTO);

    TriangleResponseDTO getAllTriangles();
}
