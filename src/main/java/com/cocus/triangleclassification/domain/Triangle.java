package com.cocus.triangleclassification.domain;

import com.cocus.triangleclassification.domain.dto.TriangleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TRIANGLE")
public class Triangle extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3075190564284466960L;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private TriangleTypeEnum triangleType;

    @Column(name = "SIDE_A")
    private Long sideA;

    @Column(name = "SIDE_B")
    private Long sideB;

    @Column(name = "SIDE_C")
    private Long sideC;

}
