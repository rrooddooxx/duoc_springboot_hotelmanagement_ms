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

#### II. Habitaciones

    - /api/v1/rooms
        - [query] limit
            - ej: /api/v1/rooms?limit={n: integer}
    - /api/v1/rooms/{roomId: integer}

#### III. Clientes

    - /api/v1/clients
        - [query] limit
            - ej: /api/v1/clients?limit={n: integer}
    - /api/v1/clients/{clientId: integer}

### Mantenedor

Sebastián Kravetz (@rrooddooxx)
