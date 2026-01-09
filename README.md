# 🏟️ Sports Booking Application

A backend service to manage sports venues, time slots, availability, and bookings with strict consistency guarantees.
Built using **Spring Boot + MySQL**, fully containerized using **Docker Compose**, and database-managed using **Flyway migrations**.

---

## 📌 Project Overview

This application allows users to:

* Create and list sports venues
* Create non-overlapping time slots per venue
* Fetch available slots for a given sport and time range
* Book a slot safely (no double booking)
* Cancel bookings and immediately free slots

The system is designed to be **safe, deterministic, and production-aligned**, even when run inside Docker.

---

## 🛠️ Why Flyway Is Used

Flyway is used for **database schema and data versioning**.

**Reasons:**

* Ensures schema consistency across environments
* Automatically applies DB changes on app startup
* Prevents accidental schema drift
* Works reliably with Dockerized databases
* Allows safe evolution of schema and seed data

**In this project:**

* `V1__init_schema.sql` → Creates tables, constraints, indexes
* `V2__insert_sports.sql` → Inserts sports data

Flyway guarantees migrations run **once and only once**, tracked via `flyway_schema_history`.

---

## 📋 Assumptions

* One booking maps to exactly **one slot**
* Slot timing is **immutable** once created
* Cancelled bookings **immediately free** the slot
* No overlapping slots per venue
* No double booking allowed
* Single MySQL instance
* No external cache
* Sports are **not hardcoded** in application logic

---

## 🧩 Sports Constraint

Sports must originate from the public API:

```
GET https://stapubox.com/sportslist/
```

Only `sport_id` / `sport_code` is stored in DB.

---

## 🔌 APIs Implemented

### Venue APIs

* `POST /venues`
* `GET /venues`

### Slot APIs

* `POST /venues/{venueId}/slots`
* `GET /venues/available`

### Booking APIs

* `POST /bookings`
* `PUT /bookings/{id}/cancel`

---

## 🧪 Sample cURL Commands (Tested)

### Create Venue

```
curl -X POST http://localhost:8080/venues \
-H "Content-Type: application/json" \
-d '{"name":"City Sports Arena","location":"Bangalore"}'
```

---

### List Venues

```
curl http://localhost:8080/venues
```

---

### Create Slot

```
curl -X POST http://localhost:8080/venues/1/slots \
-H "Content-Type: application/json" \
-d '{"sportId":7061509,"startTime":"2026-01-12T06:00","endTime":"2026-01-12T07:00"}'
```

---

### Get Available Slots

```
curl "http://localhost:8080/venues/available?sportId=7061509&start=2026-01-12T05:00&end=2026-01-12T08:00"
```

---

### Book Slot

```
curl -X POST http://localhost:8080/bookings \
-H "Content-Type: application/json" \
-d '{"slotId":1}'
```

---

### Prevent Double Booking

```
curl -X POST http://localhost:8080/bookings \
-H "Content-Type: application/json" \
-d '{"slotId":1}'
```

---

### Cancel Booking

```
curl -X PUT http://localhost:8080/bookings/1/cancel
```

---

### Verify Slot Availability After Cancel

```
curl "http://localhost:8080/venues/available?sportId=7061509&start=2026-01-12T05:00&end=2026-01-12T08:00"
```

---

## 🐳 Docker Execution

```
docker-compose up --build
```

Starts:

* MySQL 8
* Spring Boot application
* Flyway migrations

---

## ✅ Submission Compliance

* Dockerized application
* MySQL schema with constraints & indexes
* No overlapping slots
* No double booking
* Flyway-managed schema & seed data
* Fully tested via curl

---

## 🎯 Final Note

This project focuses on **correctness, clarity, and real-world backend practices**.
