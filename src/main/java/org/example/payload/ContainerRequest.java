package org.example.payload;

import lombok.Data;

@Data
public class ContainerRequest {

    private Long             id;

    private Double        width;

    private Double       length;

    private Double       height;

    private Double    weightMax;

    private Integer        cost;

}
