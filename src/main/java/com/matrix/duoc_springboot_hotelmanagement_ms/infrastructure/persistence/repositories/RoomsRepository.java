package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RoomsRepository {
  private final ArrayList<Room> roomList = new ArrayList<>();

  @PostConstruct
  public void init() {
    this.loadData();
  }

  private void loadData() {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<Room>> typeRef = new TypeReference<>() {};
    InputStream inputStream = TypeReference.class.getResourceAsStream("/data/rooms.json");
    try {
      List<Room> parsedData = mapper.readValue(inputStream, typeRef);
      roomList.addAll(parsedData);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Room> getRooms(int limit) {
    return this.roomList.subList(0, limit);
  }

  public List<Room> getAllRooms() {
    return this.roomList;
  }
}
