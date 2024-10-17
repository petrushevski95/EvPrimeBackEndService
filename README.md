# EVPRIME BACK END SERVICE DOCUMENETATION
# Users:
### Base url: localhost:8080
### SignUp Endpoint

**Description**: The signup endpoint registers a new user into the database with which later the login will be possible.  
**Route**: `/signup`  
**Type**: `POST` 


**Request body**:
```Json
	{
    		"email": "email value",	 //The email must follow the format "emailValue@provider.com,"
    		"password": "password value"
	}
```
**Response body (successful, status code: 201 Created):**
```Json
	{
    		"message": "User created.",
    		"user": {
        	"id": "id value",	//id example: 853310f8-b896-426a-9b45-05b67afe5008
        	"email": "email value"
    		},
    		"token": "token value"
	}
```
**Response body (unsuccessful: email already exists in database, status code: 422 Unprocessable Entity):** 
```json
	{
   	 	"message": "User signup failed due to validation errors.",
    		"errors": {
        		"email": "Invalid email."
    		}
	}
```
**Response body (unsuccessful: email already exists in database, status code: 422 Unprocessable Entity):**
```Json	
	{
    		"message": "User signup failed due to validation errors.",
    		"errors": {
       			 "email": "Email exists already."
    		}
	}
```


### Login Endpoint

**Description**: The login endpoint enables already created users to access the system and later make new or update events. The endpoint retrieves a token which later will be used for event creation. If the token is not distributed later in the process, the creation of the events won't be possible. The token expires after one hour.  
   **Token Example**:  
`eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RNYWlsbEBtYWlsLmNvbSIsImlhdCI6MTcxMTY0MDA4MiwiZXhwIjoxNzExNjQzNjgyfQ.AEoSH4OpbWxSLthLx_WOZdXb-3g35O71aNmx5FaoRBA`                                  
   **Route**: `/login`  
   **Type**: `POST`

**Request body**:
```Json	
	{
    		"email": "email value",
		"password": "password value"
	}
```
 **Response body (successful, status code: 200 OK):**
 ```Json	
		{
			"token": "token value", 
		   	"expirationTime": "expiration time value"  //expiration time example: 2024-03-28T16:34:42.655Z
		}
```
 **Response body (unsuccessful: wrong email/email does not match any mail in the db, status code: 401 Unauthorized):**
   ```Json
	{
    		"message": "Authentication failed."
	}
 ```
 
 **Response body (unsuccessful: wrong password, status code: 422 Unprocessable Entity):**
 ```Json
	{
    		"message": "Invalid credentials.",
    		"errors": {
       	 		"credentials": "Invalid password entered."
    		}
	}
```
# Events:

### Get All Events Endpoint

**Description**: The get all events endpoint simply retrieves all of the events that are created in the database.  
   **Route**: `/events`  
   **Type**: `GET`  

**Response body (status code: 200 OK):**  
```Json
	{
    		"events": [
        		{
           			"id": "id value",
            			"title": "title value",
            			"image": "image value",
            			"date": "date value",  // date example: 07.12.2024
            			"location": "location value",
            			"description": "description value"
        		},
        		{
            			"id": "id value",
            			"title": "title value",
            			"image": "image value",
            			"date": "date value",
            			"location": "location value",
            			"description": "description value"
    	   		}, ...
    		]
	}
```

### Get single event endpoint

**Description**: The get single event endpoint simply retrieves one of the events that are created in the database based on ID search.  
   **Route**: `/events/{id}`  
   **Type**: `GET`  

**Response Body (status code: 200 OK):**   
```json
	{
    		"events": [
        		{
           			"id": "id value",
            			"title": "title value",
            			"image": "image value",
            			"date": "date value", 
            			"location": "location value",
            			"description": "description value"
        		}
    		]
	}
```



 ### Post an event endpoint
**Description**: The post event endpoint insert a new event into the database which later than can be retrieved  with the Get endpoints.   
   **Route**: `/events`  
   **Type**: `POST` 

**Request body** 
```Json	
	{
    		"title": "title value",
		"image": "image value",
    		"date": "date value",
    		"location": "location value",
    		"description": "description value"
	}
```
**Response body (successful, status code: 201 Created):** 
```Json	
	{
    		"message": "Successfully created an event with id: {id value}"
	}
```
**Response body (unsuccessful: empty title, status code: 422 Unprocessable Entity):** 
```Json	
	{
    		"message": "Adding the event failed due to validation errors.",
		"errors": {
        		"title": "Invalid title."
    		}
	}
```
**Response body (unsuccessful: empty image or does not start with http, status code: 422 Unprocessable Entity):** 
```Json	
	{
    		"message": "Adding the event failed due to validation errors.",
		"errors": {
        		"title": "Invalid image."
    		}
	}
```
**Response body (unsuccessful: empty date, status code: 422 Unprocessable Entity):** 
```Json	
	{
    		"message": "Adding the event failed due to validation errors.",
		"errors": {
        		"title": "Invalid date."
    		}
	}
```
**Response body (unsuccessful: empty location, status code: 422 Unprocessable Entity):** 
```Json	
	
	{
    		"message": "Adding the event failed due to validation errors.",
		"errors": {
        		"title": "Invalid location."
    		}
	}
```
**Response body (unsuccessful: empty description, status code: 422 Unprocessable Entity):** 
```Json	
	{
    		"message": "Adding the event failed due to validation errors.",
		"errors": {
        		"title": "Invalid description."
    		}
	}
```
**Response body (unsuccessful: no authentication (missing token), status code: 401 Unauthorized):** 
```Json
	{
    		"message": "Not authenticated."
	}
```
### Update an event endpoint
**Description**: The update event endpoint updates an already created event from the database.           
**Route**: `/events/{id}`   
 **Type**: `PUT` 

**Request body** 
```Json	
	{
    		"title": "title value",
	    	"image": "https image value", 
  		"date": "date value", 
    		"location": "location value",
	    	"description": "description value"
	}
```
**Response body (successful, status code: 201 Created): ** 
```Json	
	{
    		 "message": "Successfully updated the event with id: {id value}"
	}
```
**Response body (unsuccessful: empty title, status code: 422 Unprocessable Entity):** 
```Json	
	{
    		"message": "Updating the event failed due to validation errors.",
    		"errors": {
        		"title": "Invalid title."
    		}
	}
```
**response body (unsuccessful: empty image or does not start with http, status code: 422 Unprocessable Entity):** 
```Json	
	{
    		"message": "Updating the event failed due to validation errors.",
    		"errors": {
        		"title": "Invalid image."
    		}
	}
```
**Response body (unsuccessful: empty date, status code: 422 Unprocessable Entity):** 
```Json	
	{
    		"message": "Updating the event failed due to validation errors.",
    		"errors": {
        		"title": "Invalid date."
    		}
	}
```
**Response body (unsuccessful: empty location, status code: 422 Unprocessable Entity):** 
```Json	
	{
    		"message": "Updating the event failed due to validation errors.",
    		"errors": {
        		"title": "Invalid location."
    		}
	}
```
**Response body (unsuccessful: empty description, status code: 422 Unprocessable Entity):** 
```Json	
	
	{
    		"message": "Updating the event failed due to validation errors.",
    		"errors": {
        		"title": "Invalid description."
    		}
	}
```
**Response body (unsuccessful: no authentication (missing token), status code: 401 Unauthorized):** 
```Json	
	{
    		"message": "Not authenticated."
	}
```

### Delete event endpoint
**Description**: The delete event endpoint deletes an already created event from the database.         
 **Route**: `/events/{id}`   
 **Type**: `DELETE` 

**Response body (successful, status code: 200 OK):** 
```Json	
	{
    		 "message": "Successfully deleted the event with id: 1"
	}
```
**Response body (unsuccessful: no authentication (missing token), status code: 401 Unauthorized):** 
```Json	
	{
    		"message": "Not authenticated."
	}
```





#
