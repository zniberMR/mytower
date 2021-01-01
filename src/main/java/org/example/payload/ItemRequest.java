package org.example.payload;

import lombok.Data;

@Data
public class ItemRequest {

    private Long       id;

    private Double  width;

    private Double length;

    private Double height;

    private Double weight;

    private Boolean isReversible;

}
