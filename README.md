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
curl -X GET 'localhost:8080/api/v1/bookings/{bookingId}'
curl -X GET 'localhost:8080/api/v1/bookings/{bookingId}/fee'

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
curl -X GET 'localhost:8080/api/v1/rooms/{roomId}'

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
curl -X GET 'localhost:8080/api/v1/clients/{clientId}'

```

### Mantenedor

Sebastián Kravetz (@rrooddooxx)
