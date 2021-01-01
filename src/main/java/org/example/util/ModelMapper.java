package org.example.util;

import org.example.domain.Container;
import org.example.domain.Coordinate;
import org.example.domain.Item;
import org.example.domain.PackingSolution;
import org.example.payload.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {
    private static List<BigDecimal> xCoordinateList;
    private static List<BigDecimal> yCoordinateList;
    private static List<BigDecimal> zCoordinateList;
    
    public static PackingSolution mapPackingRequestToPackingSolution(PackingRequest packingRequest){
        PackingSolution packingSolution = new PackingSolution();

        List<Item> itemList = packingRequest.getItemList().stream().map(itemRequest -> {
            Item item = new Item(itemRequest.getId(), itemRequest.getWidth(), itemRequest.getLength(),
                    itemRequest.getHeight(), itemRequest.getWeight(), itemRequest.getIsReversible());
            return item;
        }).collect(Collectors.toList());

        List<Container> containerList = packingRequest.getContainerList().stream().map(containerRequest -> {
            return new Container(containerRequest.getId(), containerRequest.getWidth(), containerRequest.getLength(),
                    containerRequest.getHeight(), containerRequest.getWeightMax(), containerRequest.getCost());
        }).collect(Collectors.toList());

        itemList.forEach(item -> {
            item.setContainer(containerList.get(0));
            item.setCoordinates(new Coordinate());
        });

        packingSolution.setItemList(itemList);
        packingSolution.setContainerList(containerList);
        packingSolution.setCoordinateList(createCoordinateList(containerList.get(0).getWidth(),
                containerList.get(0).getLength(),
                containerList.get(0).getHeight()));

        return packingSolution;
    }

    public static PackingResponse mapPackingSolutionToPackingResponse(PackingSolution packingSolution) {
        PackingResponse packingResponse = new PackingResponse();

        List<ItemResponse> itemResponses = packingSolution.getItemList().stream().map(item -> {
            ItemResponse itemResponse = new ItemResponse();
            itemResponse.setId(item.getId());
            itemResponse.setWidth(item.getWidth());
            itemResponse.setLength(item.getLength());
            itemResponse.setHeight(item.getHeight());
            itemResponse.setCoordinates(new CoordinateResponse(item.getCoordinates().getX(),
                    item.getCoordinates().getY(), item.getCoordinates().getZ()));
            return itemResponse;
        }).collect(Collectors.toList());

        List<ContainerResponse> containerResponses = packingSolution.getContainerList().stream().map(container -> {
            ContainerResponse containerResponse = new ContainerResponse();
            containerResponse.setId(container.getId());
            containerResponse.setWidth(container.getWidth());
            containerResponse.setLength(container.getLength());
            containerResponse.setHeight(container.getHeight());
            return containerResponse;
        }).collect(Collectors.toList());

        packingResponse.setItemList(itemResponses);
        packingResponse.setContainerList(containerResponses);

        packingResponse.setHard(packingSolution.getScore().getHardScore());
        packingResponse.setSoft(packingSolution.getScore().getSoftScore());

        return packingResponse;
    }

    private static List<Coordinate> createCoordinateList(double width, double length, double height) {
        createXCoordinateList(width, 0);
        createYCoordinateList(length, 0);
        createZCoordinateList(height, 0);
        List<Coordinate> coordinateList = new ArrayList<>();

        for (BigDecimal x : xCoordinateList) {
            for(BigDecimal y : yCoordinateList) {
                for(BigDecimal z : zCoordinateList) {
                    coordinateList.add(new Coordinate(x, y, z));
                }
            }
        }

        return coordinateList;
    }

    private static void createYCoordinateList(final double length, final double yUnit) {
        yCoordinateList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= yUnit; j++) {
                BigDecimal bd = new BigDecimal("" + i + "." + j);

                bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                yCoordinateList.add(bd);
            }
        }
    }

    private static void createZCoordinateList(final double height, final double zUnit) {
        zCoordinateList = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j <= zUnit; j++) {
                BigDecimal bd = new BigDecimal("" + i + "." + j);

                bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                zCoordinateList.add(bd);
            }
        }
    }

    private static void createXCoordinateList(final double width, final double xUnit) {
        xCoordinateList = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            for (int j = 0; j <= xUnit; j++) {
                BigDecimal bd = new BigDecimal("" + i + "." + j);

                bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                xCoordinateList.add(bd);
            }
        }
    }
}
