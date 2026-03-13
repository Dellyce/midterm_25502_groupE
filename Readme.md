## Names: Umutesi Delice
## ID: 25502
## Course: Web Technlogy and Internet
## Group : E

# Online Hotel Booking System 

A **Spring Boot REST API** for managing a **Hotel Booking System**.
This project allows users to manage locations, hotels, rooms, amenities, users, and bookings.

The API follows **RESTful design principles** and is tested using **Postman** with **Rwanda-based sample data**.

---

# Features

* Location hierarchy management (Province → District → Sector → Cell → Village)
* Hotel registration and management
* Room creation and management
* Amenity management
* User registration and profile management
* Hotel booking system
* Pagination and sorting for hotel listings
* Search users by name or location
* Many-to-Many relationship between **Rooms and Amenities**
* One-to-One relationship between **Users and Profiles**

---

# Technology Stack

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL **
* **Maven**
* **Postman**

---

# Base URL

```
http://localhost:8080
```

---

# Testing Order

The system has entity relationships, therefore endpoints must be tested in this order:

```
1. Locations
2. Amenities
3. Users
4. User Profiles
5. Hotels
6. Rooms
7. Bookings
```

Example:

* Hotels require **Location ID**
* Rooms require **Hotel ID**
* Bookings require **User ID and Room ID**

---

# API Endpoints Overview

| Method | Endpoint                             | Description             |
| ------ | ------------------------------------ | ----------------------- |
| POST   | `/api/locations/save`                | Create location         |
| POST   | `/api/amenities/save`                | Create amenity          |
| GET    | `/api/amenities/all`                 | Get all amenities       |
| POST   | `/api/users/save`                    | Register user           |
| GET    | `/api/users/all`                     | Get all users           |
| GET    | `/api/users/searchByFirstName`       | Search users            |
| GET    | `/api/users/byLocation/{locationId}` | Users by location       |
| GET    | `/api/users/byProvince`              | Users by province name  |
| POST   | `/api/profiles/save`                 | Create user profile     |
| GET    | `/api/profiles/getByUser`            | Get profile by user     |
| POST   | `/api/hotels/save`                   | Register hotel          |
| GET    | `/api/hotels/all`                    | Get hotels (pagination) |
| POST   | `/api/rooms/save`                    | Create room             |
| POST   | `/api/rooms/addAmenity`              | Add amenity to room     |
| GET    | `/api/rooms/byHotel`                 | Get rooms by hotel      |
| POST   | `/api/bookings/save`                 | Create booking          |
| GET    | `/api/bookings/all`                  | Get all bookings        |
| GET    | `/api/bookings/byUser`               | Get bookings by user    |

---

# 1. Locations API

Location hierarchy in Rwanda:

```
Province → District → Sector → Cell → Village
```

### Create Province

POST `/api/locations/save`

```json
{
  "name": "Kigali City",
  "code": "KGL",
  "type": "PROVINCE"
}
```

---

### Create District

POST

```
/api/locations/save?parentId={province_uuid}
```

```json
{
  "name": "Gasabo District",
  "code": "GSB",
  "type": "DISTRICT"
}
```

---

# 2. Amenities API

### Create Amenity

POST `/api/amenities/save`

```json
{
  "name": "Free WiFi",
  "description": "High-speed wireless internet throughout the hotel"
}
```

Example amenities:

* Free WiFi
* Swimming Pool
* Airport Shuttle
* Restaurant

---

### Get All Amenities

GET

```
/api/amenities/all
```

---

# 3. Users API

### Create User

POST

```
/api/users/save?locationId={Village_uuid}
```

```json
{
  "firstName": "Mutoni",
  "lastName": "Isabelle",
  "email": "mutoni.isabelle@gmail.com"
}
```

---

### Get All Users

GET

```
/api/users/all
```

---

### Search User by First Name

GET

```
/api/users/searchByFirstName?firstName=Mutoni
```

---

# 4. User Profiles API

### Create Profile

POST

```
/api/profiles/save?userId={user_uuid}
```

```json
{
  "phoneNumber": "+250788123456",
  "nationalId": "1199880012345678",
  "address": "KG 123 St, Kimironko, Gasabo, Kigali"
}
```

---

### Get Profile by User

GET

```
/api/profiles/getByUser?userId={user_uuid}
```

---

# 5. Hotels API

### Create Hotel

POST

```
/api/hotels/save?locationId={location_uuid}
```

```json
{
  "name": "Radisson Blu Hotel & Convention Centre Kigali",
  "address": "KG 2 Roundabout, Kigali",
  "starRating": 5
}
```

Other examples:

* Kigali Serena Hotel
* Hotel des Mille Collines
* Gorillas Golf Hotel

---

### Get All Hotels

GET

```
/api/hotels/all?page=0&size=5&sortBy=name
```

Parameters:

| Parameter | Description        |
| --------- | ------------------ |
| page      | Page number        |
| size      | Results per page   |
| sortBy    | name or starRating |

---

# 6. Rooms API

### Create Room

POST

```
/api/rooms/save?hotelId={hotel_uuid}
```

```json
{
  "roomNumber": "101",
  "price": 120.00,
  "roomType": "Standard Single"
}
```

---

### Add Amenity to Room

POST

```
/api/rooms/addAmenity?roomId={room_uuid}&amenityId={amenity_uuid}
```

This creates a **Many-to-Many relationship** between Rooms and Amenities.

---

### Get Rooms by Hotel

GET

```
/api/rooms/byHotel?hotelId={hotel_uuid}
```

---

# 7. Bookings API

### Create Booking

POST

```
/api/bookings/save?userId={user_uuid}&roomId={room_uuid}
```

```json
{
  "checkInDate": "2025-04-10",
  "checkOutDate": "2025-04-15",
  "status": "PENDING"
}
```

Valid status values:

```
PENDING
CONFIRMED
CANCELLED
COMPLETED
```

---

### Get All Bookings

GET

```
/api/bookings/all
```

---

### Get Bookings by User

GET

```
/api/bookings/byUser?userId={user_uuid}
```

---

# Valid Enum Values

### Location Types

```
PROVINCE
DISTRICT
SECTOR
CELL
VILLAGE
```

### Booking Status

```
PENDING
CONFIRMED
CANCELLED
COMPLETED
```


---

# Example Rwanda Data Used

This API uses **Rwanda-based data** such as:

* Kigali City
* Gasabo District
* Kimironko Sector
* Rwandan phone numbers (`+250`)
* Real hotels in Kigali

---

# Author: Umutesi Delice

**Online Hotel Booking System Project**

Spring Boot REST API for managing hotel reservations via Online.

---
