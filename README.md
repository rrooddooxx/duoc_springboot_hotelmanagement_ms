# MicroServicio Hotel

Proyecto de Asignatura

```
Desarrollo Full Stack I
Ingeniería en Desarrollo de Software
Duoc UC

Profesor: Carlos Valverde
Estudiante: Sebastián Kravetz

```

### Stack

- Spring Boot v3
- Spring Boot Web Starter
- Jackson Modules
- Lombok
- JDK 22

---

## Endpoints:

> Base Host: localhost:8080

#### I. Reservas

    - /api/v1/bookings
        - [query] limit
            - ej: /api/v1/bookings?limit={n: integer}
    - /api/v1/bookings/{bookingId: string}
    - /api/v1/bookings/{bookingId: string}/fee

   ```bash
# peticiones cURL

curl -X GET 'localhost:8080/api/v1/bookings'
curl -X GET 'localhost:8080/api/v1/bookings?limit=2'
curl -X GET 'localhost:8080/api/v1/bookings/1'
curl -X GET 'localhost:8080/api/v1/bookings/1/fee'
curl -X POST 'localhost:8080/api/v1/bookings' -H 'Content-Type: application/json' -d '{
  "client_id": 2,
  "room_id": 3,
  "date_request": "2024-09-13T14:30:00.000Z",
  "date_start": "2024-09-20T14:30:00.000Z",
  "date_end": "2024-09-22T14:30:00.000Z",
  "fee": 32000
}'

```

#### II. Habitaciones

    - /api/v1/rooms
        - [query] limit
            - ej: /api/v1/rooms?limit={n: integer}
    - /api/v1/rooms/{roomId: integer}

   ```bash
# peticiones cURL

curl -X GET 'localhost:8080/api/v1/rooms'
curl -X GET 'localhost:8080/api/v1/rooms?limit=2'
curl -X GET 'localhost:8080/api/v1/rooms/3'
curl -X POST 'localhost:8080/api/v1/rooms' -H 'Content-Type: application/json' -d '{
  "room_type": "TRIPLE",
  "availability": "AVAILABLE",
  "guest_count": 3,
  "bed_count": 3,
  "price": 36890.00
}'

```

#### III. Clientes

    - /api/v1/clients
        - [query] limit
            - ej: /api/v1/clients?limit={n: integer}
    - /api/v1/clients/{clientId: integer}

   ```bash
# peticiones cURL

curl -X GET 'localhost:8080/api/v1/clients'
curl -X GET 'localhost:8080/api/v1/clients?limit=2'
curl -X GET 'localhost:8080/api/v1/clients/2'
curl -X POST 'localhost:8080/api/v1/clients' -H 'Content-Type: application/json' -d '{
  "first_name": "Luis",
  "last_name": "Serrano",
  "email": "lserrano@uchile.cl",
  "phone": "987678567"
}'

```

#### VII. Editar Estado de Habitaciones

    - /api/v1/rooms/{roomId: integer}

   ```bash
# peticiones cURL

curl -X PUT 'localhost:8080/api/v1/rooms/7' -H 'Content-Type: application/json' -d '{
  "availability_status": 2
}'

```

#### VIII. Editar Estado de Reservas

    - /api/v1/bookings/{bookingId: string}/status

   ```bash
# peticiones cURL

curl -X PUT 'localhost:8080/api/v1/bookings/1/status' -H 'Content-Type: application/json' -d '{
  "status": "APPROVED"
}'

```

#### IX. Eliminar Reservas

    - /api/v1/bookings/{bookingId: string}

   ```bash
# peticiones cURL

curl -X DELETE 'localhost:8080/api/v1/bookings/2'

```

### Mantenedor

Sebastián Kravetz (@rrooddooxx)
