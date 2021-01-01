package org.example.payload;

import lombok.Data;

@Data
public class ItemResponse {

    private Long       id;

    private Double  width;

    private Double length;

    private Double height;

    private CoordinateResponse coordinates;
}
