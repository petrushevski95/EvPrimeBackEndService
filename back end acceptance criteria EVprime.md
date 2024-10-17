# EV PRIME BACK END SERVICE ACCEPTANCE CRITERIA


### Test scenario 1: Successful sign up. (post request)               
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)    
**AND** the body sent request is like this:     
```json
{
    	"email": "email value",	
    	"password": "password value"
}
```
**WHEN** the server response is status code 201Created    
**THEN** server responds back with body result like this:    
```json
{
    	"message": "User created.",
    	"user": {
        "id": "id value",
        "email": "email value"
    },
    	"token": "token value"
}
```


### Test scenario 2: Unsuccessul sign up (invalid email). (post request)      
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)    
**AND** the body sent request is like this:  
```json
{
    	"email": "email value",	(invalid email)
    	"password": "password value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity    
**THEN** server responds back with body result like this:
```json
{
   	"message": "User signup failed due to validation errors.",
    	"errors": {
       		 "email": "Invalid email."
    }
}
```


### Test scenario 3: Unsuccessul sign up (existing email). (post request)   
**Prerequisite:** An email account must already be registered.   
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)    
**AND** the body sent request is like this:
```json
{
    	"email": "email value",	(existing email)
    	"password": "password value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity     
**THEN** server responds back with body result like this:  
```json
{
   	"message": "User signup failed due to validation errors.",
   	"errors": {
        "email": "Email exists already."
    }
}
```


### Test scenario 4: Unsuccessul sign up (empty or short password value). (post request)
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)    
**AND** the body sent request is like this:
```json
{
    	"email": "email value",	
    	"password": "password value" (empty or short password value)
}
```
**WHEN** the server response is status code 422 Unprocessable Entity   
**THEN** server responds back with body result like this:
```json
{
    "message": "User signup failed due to validation errors.",
    "errors": {
        "password": "Invalid password. Must be at least 6 characters long."
    }
}
```


### Test scenario 5: Successful login. (post request)
**Prerequisite:** account need to be registered.  
**GIVEN** post request is sent to the endpoint (localhost:8080/login)   
**AND** the body sent request is like this:    
```json
{
   	"email": "email value",	
    	"password": "password value"
}
```
**WHEN** the server response is status code 200OK  
**THEN** server responds back with body result like this: 
```json
{
    	"token": "token value",
    	"expirationTime": "expiration time value" (expiration time example: 2024-03-28T16:34:42.655Z)
}
```


### Test scenario 6: Unsuccessul login (invalid email format). (post request)
**GIVEN** post request is sent to the endpoint (localhost:8080/login)    
**AND** the body sent request is like this:
```json
{
   	"email": "email value",	(invalid email fromat)
    	"password": "password value"
}
```
**WHEN** the server response is status code 401 Unaothirized       
**THEN** server responds back with body result like this:  
```json
{
   	"message": "Authentication failed."
}
```


### Test scenario 7: Unsuccessul login (invalid or empty password). (post request)
**Prerequisite:** account need to be registered.  
**GIVEN**  post request is sent to the endpoint (localhost:8080/login)  
**AND** the body sent request is like this:  
```json
{
   	"email": "email value",	
    	"password": "password value" (invalid or empty password)
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this  
```json
{
    	"message": "Invalid credentials.",
   	"errors": {
        "credentials": "Invalid email or password entered."
    }
}
```


### Test scenario 8: Successful get all events. (get request) 
**Prerequisite:** at least one event must be created before the get request is made
**GIVEN** get request is sent to the endpoint (localhost:8080/events)   
**WHEN**  the server response is status code 200OK   
**THEN** server responds back with body result like this:    
```json
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
    	   	}, ..... 
	]
}
```


### Test scenario 9: Successful get single event. (get request) 
**Prerequisite:** event must be created before the get request is made   
**GIVEN** get request is sent to the endpoint (localhost:8080/events[id])   
**WHEN**  the server response is status code 200OK   
**THEN** server responds back with body result like this:    
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


### Test scenario 10: Successful post an event. (post request)
**Prerequisite:** must be authenticated.    
**GIVEN** post request is sent to the endpoint (localhost:8080//events)   
**AND** the body sent request is like this:    
```json
{
    	"title": "title value",
	"image": "image value",
    	"date": "date value",
    	"location": "location value",
    	"description": "description value"
}
```
**WHEN** the server response is status code 201 Created   
**THEN** server responds back with body result like this:    
```json
{
   	 "message": "Successfully created an event with id: [id value]"
}
```
**AND** the event is successfuly created into the database.  


### Test scenario 11: Unsuccessful post an event (empty title value). (post request)  
**Prerequisite:** must be authenticated.  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```json
{
    	"title": "", (empty value)
	"image": "image value",
    	"date": "date value",
    	"location": "location value",
    	"description": "description value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:   
```json
{
    	"message": "Adding the event failed due to validation errors.",
	"errors": {
        	"title": "Invalid title."
    	}
}
```


### Test scenario 12: Unsuccessful post an event (empty or incorrect image path). (post request)
**Prerequisite:** must be authenticated.    
**GIVEN** post request is sent to the endpoint (localhost:8080//events)   
**AND** the body sent request is like this:
```json
{
    	"title": "title value",
	"image": "",  (empty image or does not start with http:)
    	"date": "date value",
    	"location": "location value",
    	"description": "description value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity     
**THEN** server responds back with body result like this:    
```json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "image": "Invalid image."
    }
}
```


### Test scenario 13: Unsuccessful post an event (empty date value). (post request)
**Prerequisite:** must be authenticated.      
**GIVEN** post request is sent to the endpoint (localhost:8080//events)     
**AND** the body sent request is like this:     
```json
{
    	"title": "title value",
	"image": "image value",  
    	"date": "", (empty value)
    	"location": "location value",
    	"description": "description value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity    
**THEN** server responds back with body result like this:    
```json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "date": "Invalid date."
    }
}
```


### Test scenario 14: Unsuccessful post an event (empty location value). (post request) (BUG FOUND: check Test Case 18)
**Prerequisite:** must be authenticated.       
**GIVEN** post request is sent to the endpoint (localhost:8080//events)      
**AND** the body sent request is like this:    
```json
{
    	"title": "title value",
	"image": "image value",  
    	"date": "date value", 
    	"location": "", (empty value)
    	"description": "description value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity     
**THEN** server responds back with body result like this:    
```json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "description": "Invalid location." 
    }
}
```


### Test scenario 15: Unsuccessful post an event (empty description value). (post request) 
**Prerequisite:** must be authenticated.         
**GIVEN** post request is sent to the endpoint (localhost:8080//events)      
**AND** the body sent request is like this:      
```json
{
    	"title": "title value",
	"image": "image value",  
    	"date": "date value", 
    	"location": "location value", 
    	"description": "" (empty value)
}
```
**WHEN** the server response is status code 422 Unprocessable Entity      
**THEN** server responds back with body result like this:      
```json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "description": "Invalid description."
    }
}
```


### Test scenario 16: Unsuccessful post an event (empty values). (post request) (BUG FOUND: check Test Case 20)
**Prerequisite:** must be authenticated.    
**GIVEN** post request is sent to the endpoint (localhost:8080//events)      
**AND** the body sent request is like this:    
```json
{
    	"title": "title value",
	"image": "image value",  
    	"date": "date value", 
    	"location": "location value", 
    	"description": "" (empty value)
}
```
**WHEN** the server response is status code 422 Unprocessable Entity   
**THEN** server responds back with body result like this:  
```json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "description": "Invalid description."
    }
}
```

### Test scenario 17: Unsuccessful post an event (no authentication). (post request)     
**GIVEN** post request is sent to the endpoint (localhost:8080//events)    
**AND** the body sent request is like this:    
```json
{
    	"title": "title value",
	"image": "image value",  
    	"date": "date value", 
    	"location": "location value", 
    	"description": "description value"
}
```
**WHEN** the server response is status code 401 Unauthorized    
**THEN** server responds back with body result like this:   
```json
{
    	"message": "Not authenticated."
}
```


### Test scenario 18: Successful update an event. (put request)
**Prerequisite:** must be authenticated and event must be created before the put request is made  
**GIVEN** put request is sent to the endpoint (localhost:8080//events/[id])    
**AND** the body sent request is like this:   
```json
{	
    	"title": "title value",
	"image": "image value",  
    	"date": " date value", 
    	"location": "location value", 
    	"description": "description value"
}
```
**WHEN** the server response is status code 201 Created    
**THEN** server responds back with body result like this:   
```json
{
   	"message": "Successfully updated the event with id: [id value]"
}
```


### Test scenario 19: Unsuccessful update an event. (empty title value) (put request)
**Prerequisite:** must be authenticated and event must be created before the put request is made    
**GIVEN** put request is sent to the endpoint (localhost:8080//events/[id])     
**AND** the body sent request is like this:    
```json
{	
    	"title": "", (empty value)
	"image": "image value",  
    	"date": "date value", 
    	"location": "location value", 
    	"description": "description value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this: 
```json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "image": "Invalid image."
    }
}
```


### Test scenario 20: Unsuccessful update an event. empty or incorrect image path) (put request)
**Prerequisite:** must be authenticated and event must be created before the put request is made     
**GIVEN** put request is sent to the endpoint (localhost:8080//events/[id])      
**AND** the body sent request is like this:      
```json
{	
    	"title": "title value", 
	"image": "", (empty image or does not start with http:)   
    	"date": " date value", 
    	"location": "location value", 
    	"description": "description value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity   
**THEN** server responds back with body result like this:    
```json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "image": "Invalid image."
    }
}
```

### Test scenario 21: Unsuccessful update an event. (empty date value) (put request)
**Prerequisite:** must be authenticated and event must be created before the put request is made  
**GIVEN** put request is sent to the endpoint (localhost:8080//events/[id])  
**AND** the body sent request is like this:  
```json
{	
    	"title": "title value", 
	"image": "image value",   
    	"date": "", (empty value) 
    	"location": "location value", 
    	"description": "description value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity   
**THEN** server responds back with body result like this:   
```json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "date": "Invalid date."
    }
}
```


### Test scenario 22: Unsuccessful update an event. (empty location value) (put request) (BUG FOUND: check test case 27)
**Prerequisite:** must be authenticated and event must be created before the put request is made  
**GIVEN** put request is sent to the endpoint (localhost:8080//events/[id])  
**AND** the body sent request is like this:  
```json
{	
    	"title": "title value", 
	"image": "image value",   
    	"date": "date value",  
    	"location": "", (empty value)
    	"description": "description value"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "description": "Invalid location."
    }
}
```

### Test scenario 23: Unsuccessful update an event. (empty description value) (put request)
**Prerequisite:** must be authenticated and event must be created before the put request is made  
**GIVEN** put request is sent to the endpoint (localhost:8080//events/[id])  
**AND** the body sent request is like this: 
```json
{	
    	"title": "title value", 
	"image": "image value",   
    	"date": "date value",  
    	"location": "location value", 
    	"description": "" (empty value)
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:
```json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "description": "Invalid description."
    }
}
```


### Test scenario 24: Unsuccessful update an event. (empty values) (put request) (BUG FOUND check test case 29)
**Prerequisite:** must be authenticated and event must be created before the put request is made  
**GIVEN** put request is sent to the endpoint (localhost:8080//events/[id])  
**AND** the body sent request is like this:  
```json
{	
    	"title": "title value", (empty value)
	"image": "image value", (empty value) 
    	"date": "date value", (empty value)  
    	"location": "location value", (empty value)  
    	"description": "description value" (empty value)
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:
```json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "title": "Invalid title.",
        "description": "Invalid location.",
        "date": "Invalid date.",
        "image": "Invalid image."
    }
}
```


### Test scenario 25: Unsuccessful update an event. (no authentication) (put request)
**GIVEN** put request is sent to the endpoint (localhost:8080//events/[id])  
**AND** the body sent request is like this:  
```json
{	
    	"title": "title value", 
	"image": "image value",   
    	"date": "date value",  
    	"location": "location value", 
    	"description": "" (empty value)
}
```
**WHEN** the server response is status code 401 Unauthorized  
**THEN** server responds back with body result like this:  
```json
{
    	"message": "Not authenticated."
}
```


### Test scenario 26: Successful delete event. (delete request)
**Prerequisite:** must be authenticated and event must be created before the delete request is made  
**GIVEN** delete request is sent to the endpoint (localhost:8080//events/[id])  
**WHEN** the server response is status code 200OK  
**THEN** server responds back with body result like this:  
```json
{
   	 "message": "Successfully deleted the event with id: [id value]"
}
```
AND information from the database is also removed.

