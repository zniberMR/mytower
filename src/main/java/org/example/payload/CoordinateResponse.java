package org.example.payload;

import lombok.Data;

@Data
public class CoordinateResponse {
    
    private Double x;
    
    private Double y;
    
    private Double z;

    public CoordinateResponse(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
