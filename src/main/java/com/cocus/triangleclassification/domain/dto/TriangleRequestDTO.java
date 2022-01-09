package com.cocus.triangleclassification.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TriangleRequestDTO implements Serializable {
    private static final long serialVersionUID = -5585752831418450947L;

    private Long sideA;
    private Long sideB;
    private Long sideC;
}

