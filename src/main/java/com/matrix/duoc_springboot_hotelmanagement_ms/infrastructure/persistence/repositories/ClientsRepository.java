package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ClientsRepository {
  private final ArrayList<Client> clientsList = new ArrayList<>();

  @PostConstruct
  public void init() {
    this.loadData();
  }

  private void loadData() {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<Client>> typeRef = new TypeReference<>() {};
    InputStream inputStream = TypeReference.class.getResourceAsStream("/data/clients.json");
    try {
      List<Client> parsedData = mapper.readValue(inputStream, typeRef);
      clientsList.addAll(parsedData);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Client> getClients(int limit) {
    return this.clientsList.subList(0, limit);
  }

  public List<Client> getAllClients() {
    return this.clientsList;
  }
}
