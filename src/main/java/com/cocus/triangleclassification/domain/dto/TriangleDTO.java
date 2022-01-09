package com.cocus.triangleclassification.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TriangleDTO implements Serializable {
    private static final long serialVersionUID = -4920067293288300295L;

    private String triangleType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long sideA;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long sideB;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long sideC;


}
