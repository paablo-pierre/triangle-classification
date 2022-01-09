package com.cocus.triangleclassification.domain.dto;

import com.cocus.triangleclassification.domain.Triangle;
import com.cocus.triangleclassification.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TriangleResponseDTO implements Serializable {
    private static final long serialVersionUID = 8941161689580450924L;

    private List<TriangleDTO> data;

    public static TriangleResponseDTO toTriangleTypeDTO(List<Triangle> triangle) {

        var triangleType = triangle
                .stream()
                .filter(Objects::nonNull)
                .map(Triangle::getTriangleType)
                .findFirst();

        if(triangleType.isEmpty()) {
            throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "Does not possible validate if is a triangle");
        }

        var triangleDTO = TriangleDTO
                .builder()
                .triangleType(triangleType.get().name()).build();

        return TriangleResponseDTO.builder()
                .data(Collections.singletonList(triangleDTO))
                .build();
    }

    public static TriangleResponseDTO toDTO(List<Triangle> triangle) {
        TriangleDTO triangleDTO;
        List<TriangleDTO> triangleDTOList = new ArrayList<>();

        for(Triangle t : triangle) {
            triangleDTO = TriangleDTO
                    .builder()
                    .triangleType(t.getTriangleType().name())
                    .sideA(t.getSideA())
                    .sideB(t.getSideB())
                    .sideC(t.getSideC())
                    .build();

            triangleDTOList.add(triangleDTO);
        }

        return TriangleResponseDTO.builder()
                .data(triangleDTOList)
                .build();
    }
}
