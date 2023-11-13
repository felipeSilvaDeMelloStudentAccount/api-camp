# Campsites API

This is the documentation for the Campsites REST API, designed for managing campsites through CRUD
operations.

## Base URL

The base URL for the API is:

```
http://localhost:9002/api/v1/campsites
```

## Authentication

Authentication is required for all endpoints. Include an API key in the headers of each request:
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

**Request Body:**

```json
{
  "name": "Campsite Name",
  "images": [
    "base64_image_1",
    "base64_image_2"
  ],
  "description": "Campsite Description",
  "location": "Campsite Location",
  "ratings": 4.5
}
```

**Response:**

```json
{
  "id": 123,
  "name": "Campsite Name",
  "images": [
    "base64_image_1",
    "base64_image_2"
  ],
  "description": "Campsite Description",
  "location": "Campsite Location",
  "ratings": 4.5
}
```

### 2. Read Campsite

**Endpoint:** `GET /campsites/{id}`

**Response:**

```json
{
  "id": 123,
  "name": "Campsite Name",
  "images": [
    "base64_image_1",
    "base64_image_2"
  ],
  "description": "Campsite Description",
  "location": "Campsite Location",
  "ratings": 4.5
}
```

### 3. Update Campsite

**Endpoint:** `PUT /campsites/{id}`

**Request Body:**

```json
{
  "name": "Updated Campsite Name",
  "images": [
    "updated_base64_image_1",
    "updated_base64_image_2"
  ],
  "description": "Updated Campsite Description",
  "location": "Updated Campsite Location",
  "ratings": 4.8
}
```

**Response:**

```json
{
  "id": 123,
  "name": "Updated Campsite Name",
  "images": [
    "updated_base64_image_1",
    "updated_base64_image_2"
  ],
  "description": "Updated Campsite Description",
  "location": "Updated Campsite Location",
  "ratings": 4.8
}
```

### 4. Delete Campsite

**Endpoint:** `DELETE /campsites/{id}`

**Response:**

```json
{
  "message": "Campsite deleted successfully"
}
```

## Query Parameters

### 1. Search Campsites

**Endpoint:** `GET /campsites/search`

**Query Parameters:**

- `name` (optional): Search by campsite name.
- `location` (optional): Search by campsite location.

**Response:**

```json
[
  {
    "id": 123,
    "name": "Campsite Name",
    "images": [
      "base64_image_1",
      "base64_image_2"
    ],
    "description": "Campsite Description",
    "location": "Campsite Location",
    "ratings": 4.5
  },
  {
    "id": 124,
    "name": "Another Campsite",
    "images": [
      "other_base64_image_1",
      "other_base64_image_2"
    ],
    "description": "Another Campsite Description",
    "location": "Different Location",
    "ratings": 4.2
  }
]
```

## Status Codes

- `200 OK`: Successful GET request.
- `201 Created`: Successful POST request.
- `204 No Content`: Successful DELETE request.
- `400 Bad Request`: Malformed request or missing parameters.
- `401 Unauthorized`: Missing or invalid Jwt Token.
- `404 Not Found`: Resource not found.
- `500 Internal Server Error`: Server-side error.

## Conclusion

This API provides a simple and intuitive interface for managing campsites. Ensure proper
authentication and handle errors gracefully when integrating with this API. Images are expected to
be provided as base64 encoded strings.