package org.example.payload;

import lombok.Data;

import java.util.List;

@Data
public class PackingRequest {

    private List<ItemRequest> itemList;

    private List<ContainerRequest> containerList;

}
