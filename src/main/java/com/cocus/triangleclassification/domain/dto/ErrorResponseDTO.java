package com.cocus.triangleclassification.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO implements Serializable {
    private static final long serialVersionUID = 7074303125661945006L;

    private String errorCode;
    private String message;
}
