# Campsites API

This is the documentation for the Campsites REST API, designed for managing campsites through CRUD
operations.

## Base URL

The base URL for the API is:

```
http://localhost:9002/api/v1/campsites
```

## Authentication

Authentication is required for most endpoints. Include an API key in the headers of each request:

#### Request Header

- **Authorization** : Bearer Token from login

```
Authorization: Bearer YOUR_API_KEY
```

## Error Handling

The API returns standard HTTP status codes. In case of an error, a JSON response with an error
message will be provided. For example:

```json
{
  "cause": "JwtToken",
  "message": "Invalid JwtToken"
}
```

## Endpoints

### 1. Create Campsite

**Endpoint:** `POST /campsites`

#### Request Header

- **Authorization**: Bearer Token from login

#### Request Body Validation Mandatory Fields

- **name**: Required, String, 1-100 characters
- **rating**: Required, Integer, 1-5
- **addressDetails**: Required, Object
    - **city**: Required, String, 1-100 characters
    - **country**: Required, String, 1-100 characters


- **Request Body:**

```json
{
  "name": "Example Campsite",
  "description": "A beautiful campsite in a serene location.",
  "rating": 4,
  "addressDetails": {
    "addressLine1": "123 Main Street",
    "addressLine2": "Apt 4B",
    "addressLine3": null,
    "addressLine4": null,
    "addressLine5": null,
    "city": "Example City",
    "country": "Example Country",
    "eirCode": "E12345",
    "geoLocation": {
      "latitude": 40.7128,
      "longitude": -74.0060
    }
  }
}
```

**Response:**
201
Location Header: v1/campsites/{id}

### 2. Read Campsite

**Endpoint:** `GET /campsites/{id}`

**Response:**

```json
{
  "id": "1",
  "name": "Gorgeous Campsite",
  "description": "A wonderful campsite with great views.",
  "rating": 4,
  "createdDate": "2023-11-20T12:30:00",
  "addressDetails": {
    "addressLine1": "123",
    "addressLine2": "The green",
    "addressLine3": null,
    "addressLine4": "Mountainville",
    "addressLine5": "Dublin",
    "country": "Guessland",
    "eirCode": "W12345",
    "geoLocation": {
      "latitude": 40.7128,
      "longitude": -74.0060
    }
  },
  "author": {
    "username": "camp_author",
    "userId": "user123"
  }
}
```

### 3. Update Campsite

**Endpoint:** `PUT /campsites/{id}`

#### Request Header

- **Authorization**: Bearer Token from login

**Request Body:**

```json
{
  "name": "Updated Campsite Name",
  "description": "Updated Campsite Description",
  "ratings": 4.8,
  "addressDetails": "Updated Campsite AddressDetails"
}
```

**Response:**
Response: 200

### 4. Delete Campsite

**Endpoint:** `DELETE /campsites/{id}`

#### Request Header

- **Authorization**: Bearer Token from login

**Response:**

```json
{
  "message": "Campsite deleted successfully"
}
```

**Response:**
Response: 200

### 5. Read All Campsite

**Endpoint:** `GET /campsites/all`

**Response:**

```json
[
  {
    "id": "6558b07282e7b27a9bde6d3f",
    "name": "Camp 354 Campsite",
    "description": "A beautiful campsite in a serene location.",
    "rating": 4,
    "createdDate": null,
    "addressDetails": {
      "addressLine1": "123 Main Street",
      "addressLine2": "Apt 4B",
      "addressLine3": null,
      "addressLine4": null,
      "addressLine5": null,
      "country": "Example Country",
      "eirCode": "E12345",
      "geoLocation": {
        "latitude": 40,
        "longitude": -74
      }
    },
    "author": {
      "username": "Jay Mack",
      "userId": "655872e1001f711e41f4cc75"
    }
  }
]
```

### 6. Upload Image

**Endpoint:** `POST /campsites/{id}/images`

#### Request Header

- **Authorization**: Bearer Token from login

**Request Body:** multipart/form-data
file=@/path/to/image.jpg

**Response:**
Response: 200

### 7. Get Images by Campsite ID

**Endpoint:** `GET /campsites/{id}/images`

**Response:**

```json

[
  {
    "id": "image1",
    "campsiteId": "1",
    "fileId": "607d1e8f5a2aef001e41ef44",
    "base64Image": "/9j/4AAQSkZJRgABAQEAYABgAAD/4TDKRXhpZgAATU0AKgAAAAgABAExAAIAAAALAAAQSodpAAQAAAABAAAQVoglAAQAAAABAAAgouocAAcAABAMAAAAPgAAAAAc6gAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
  },
  {
    "id": "image2",
    "campsiteId": "1",
    "fileId": "607d1e995a2aef001e41ef45",
    "base64Image": "/9j/4AAQSkZJRgABAQEAYABgAAD/4TDKRXhpZgAATU0AKgAAAAgABAExAAIAAAALAAAQSodpAAQAAAABAAAQVoglAAQAAAABAAAgouocAAcAABAMAAAAPgAAAAAc6gAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
  }
]

```

### 8. Delete Image

**Endpoint:** `DELETE /campsites/{id}/images/{imageId}`

#### Request Header

- **Authorization**: Bearer Token from login

**Response:**
Response: 200

### 9. Post Comment

**Endpoint:** `POST /campsites/{campsiteid}/comments`

#### Request Header

- **Authorization**: Bearer Token from login

#### Request Body Validation Mandatory Fields

- **text**: Required, String, 1-1500 characters

- **Request Body:**

```json
{
  "text": "This is a sample comment.",
  "author": {
    "username": "john_doe",
    "userId": "123456"
  }
}
```

### 10. Get Comments by Campsite ID

**Endpoint:** `GET /campsites/{id}/comments`

**Response:**

```json

[
  {
    "id": "comment1",
    "text": "This is a sample comment.",
    "author": {
      "username": "john_doe",
      "userId": "123456"
    }
  },
  {
    "id": "comment2",
    "text": "Another sample comment.",
    "author": {
      "username": "jane_doe",
      "userId": "654321"
    }
  }
]
```

**Response:**
Response: 200

```json
{
  "message": "Comment posted successfully",
  "commentId": "your_comment_id_here"
}
```

### 11. Delete Comment

**Endpoint:** `DELETE /campsites/{id}/comments/{commentId}`

#### Request Header

- **Authorization**: Bearer Token from login

**Response:**
Response: 200
**Response:**
Response: 200

```json
{
  "message": "Comment deleted successfully",
  "commentId": "your_comment_id_here"
}
```

## Status Codes

- `200 OK`: Successful GET request.
- `201 Created`: Successful POST request.
- `204 No Content`: Successful DELETE request.
- `400 Bad Request`: Malformed request or missing parameters.
- `401 Unauthorized`: Missing or invalid Jwt Token.
- `404 Not Found`: Resource not found.
- `500 Internal Server Error`: Server-side error.

## Running the Application

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--DBURI=mongodb+srv://<username>:<password>@camp.s1dnkux.mongodb.net/dev -f pom.xml
```