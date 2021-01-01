package org.example.payload;

import lombok.Data;

import java.util.List;

@Data
public class PackingResponse {

    private List<ItemResponse> itemList;

    private List<ContainerResponse> containerList;

    private Integer hard;

    private Integer soft;
}
