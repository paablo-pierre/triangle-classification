package com.cocus.triangleclassification.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TriangleRequestDTO implements Serializable {
    private static final long serialVersionUID = -5585752831418450947L;

    @Min(value = 1, message = "must be equal or greater than 1")
    private Long sideA;

    @Min(value = 1, message = "must be equal or greater than 1")
    private Long sideB;

    @Min(value = 1, message = "must be equal or greater than 1")
    private Long sideC;
}

