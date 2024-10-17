# EV PRIME BACK END SERVICE TEST CASES


### Test case 1: Successful sign up. (post request) (PASS)
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)  
**AND** the body sent request is like this:  
```Json
{
    	"email": "new_email@gmail.com",	
    	"password": "password123"
}
```
**WHEN** the server response is status code 201Created  
**THEN** server responds back with body result like this:  
```Json
{
    	"message": "User created.",
    	"user": {
        "id": "b75bde49-115a-4be7-8e4e-1252ae7d0c30",
        "email": "test1234@gmail.com"
    },
    	"token": "{token value}"
}
```


### Test case 2: Unsuccessul sign up (invalid email format). (post request) (PASS)
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)  
**AND** the body sent request is like this:  
```Json
{
    	"email": "test1234mail.com",	
    	"password": "password123"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
   	"message": "User signup failed due to validation errors.",
    	"errors": {
       		 "email": "Invalid email."
    }
}
```


### Test case 3: Unsuccessul sign up (missing email). (post request) (PASS) 
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)  
**AND** the body sent request is like this:  
```Json
{
    	"email": "", 	
    	"password": "password123"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
   	"message": "User signup failed due to validation errors.",
    	"errors": {
       		 "email": "Invalid email."
    }
}
```


### Test case 4: Unsuccessul sign up (existing email). (post request) (PASS)
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)  
**AND** the body sent request is like this: 
```Json
{
    	"email": "new_email@gmail.com",
    	"password": "password123"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
   	"message": "User signup failed due to validation errors.",
   	"errors": {
        "email": "Email exists already."
    }
}
```

### Test case 5: Unsuccessul sign up (empty password). (post request) (PASS)
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)   
**AND** the body sent request is like this:  
```Json
{
    	"email": "new_test_email@gmail.com",	
    	"password": ""
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:
```Json
{
    "message": "User signup failed due to validation errors.",
    "errors": {
        "password": "Invalid password. Must be at least 6 characters long."
    }
}
```


### Test case 6: Unsuccessul sign up (short password "less than 6 characters"). (post request) (PASS)
**GIVEN** post request is sent to the endpoint (localhost:8080/signup)  
**AND** the body sent request is like this:  
```Json
{
    	"email": "new_test_email@gmail.com",	
    	"password": "pass1"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "User signup failed due to validation errors.",
    "errors": {
        "password": "Invalid password. Must be at least 6 characters long."
    }
}
```


### Test case 7: Successful login. (post request) (PASS)
**Prerequisite:** account need to be registered.  
**GIVEN** post request is sent to the endpoint (localhost:8080/login)  
**AND** the body sent request is like this:  
```Json
{
   	"email": "new_email@gmail.com",	
    	"password": "password123"
}
```
**WHEN** the server response is status code 200OK  
**THEN** server responds back with body result like this:  
```Json
{
    	"token": "{token value}",
    	"expirationTime": "2024-10-05T12:07:03.555Z" 
}
```


### Test case 8: Unsuccessul login (invalid email). (post request) (PASS)
**Prerequisite:** account need to be registered.  
**GIVEN** post request is sent to the endpoint (localhost:8080/login)  
**AND** the body sent request is like this:  
```Json
{
   	"email": "new_email1@gmail.com",  (email should be "new_email@gmail.com")
    	"password": "password123"
}
```
**WHEN** the server response is status code 401 Unaothirized  
**THEN** server responds back with body result like this:  
```Json
{
   	"message": "Authentication failed."
}
```


### Test case 9: Unsuccessul login (invalid password). (post request) (PASS)
**Prerequisite:** account need to be registered.
**GIVEN** post request is sent to the endpoint (localhost:8080/login)  
**AND** the body sent request is like this:  
```Json
{
   	"email": "new_email@gmail.com",	
    	"password": "password1234" (password should be "password123")
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this  
```Json
{
    	"message": "Invalid credentials.",
   	"errors": {
        "credentials": "Invalid email or password entered." >>> different then the documentation
    }
}
```


### Test case 10: Unsuccessul login (empty password). (post request) (PASS)
**Prerequisite:** account need to be registered.  
**GIVEN** post request is sent to the endpoint (localhost:8080/login)  
**AND** the body sent request is like this:  
```Json
{
   	"email": "test123@gmail.com",	
    	"password": "" 
}
```
**WHEN** the server response is status code 422 Unprocessable Entity   
**THEN** server responds back with body result like this  
```Json
{
    	"message": "Invalid credentials.",
   	"errors": {
        "credentials": "Invalid email or password entered." >>> different then the documentation
    }
}
```


### Test case 11: Successful get all events. (get request) (PASS)
**Prerequisite:** at least one event must be created before the get request is made  
**GIVEN** get request is sent to the endpoint (localhost:8080/events)  
**WHEN**  the server response is status code 200OK  
**THEN** server responds back with body result like this:  
```Json
{
    "events": [
        {
            "id": "963296d4-403d-491f-9ae9-d1e077e67cde",
            "title": "Big happening",
            "image": "https://www.google.com/search?sca_esv=c017efeea7a130ca&sxsrf=ADLYWILY4Ca4rqGXwGB5ZOOY--SF-lDpbQ:1727967010258&q=image&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWd8nbOJfsBGGB5IQQO6L3J_86uWOeqwdnV0yaSF-x2jon2iao6KWCaVjfn7ahz_sfz4kQc-hbvsXJ2gNx0RnV2nl305mvoek0YK94ylYY2a4b3Q-OEwW5lKppi2wujywZWmdIJVp8wrsv_g-eh5sWEDXx8JNpgmjsiKj2mZMvftPlZJZz&sa=X&ved=2ahUKEwiGytHeuvKIAxUfRvEDHc8SIKwQtKgLegQIFRAB&biw=1920&bih=919&dpr=1#vhid=tYmxDgFq4MrkJM&vssid=mosaic",
            "date": "random date",
            "location": "random location",
            "description": "random desc"
        },
        {
            "id": "abea75ac-2ffd-40a7-88da-8f91e807e931",
            "title": "test",
            "image": "https://www.google.com/search?sca_esv=c017efeea7a130ca&sxsrf=ADLYWILY4Ca4rqGXwGB5ZOOY--SF-lDpbQ:1727967010258&q=image&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWd8nbOJfsBGGB5IQQO6L3J_86uWOeqwdnV0yaSF-x2jon2iao6KWCaVjfn7ahz_sfz4kQc-hbvsXJ2gNx0RnV2nl305mvoek0YK94ylYY2a4b3Q-OEwW5lKppi2wujywZWmdIJVp8wrsv_g-eh5sWEDXx8JNpgmjsiKj2mZMvftPlZJZz&sa=X&ved=2ahUKEwiGytHeuvKIAxUfRvEDHc8SIKwQtKgLegQIFRAB&biw=1920&bih=919&dpr=1#vhid=tYmxDgFq4MrkJM&vssid=mosaic",
            "date": "test 123",
            "location": "test",
            "description": "test123"
        },
        {
            "id": "208f62e5-2f4a-4db5-9c61-8e3e5cb1c364",
            "title": "test123",
            "image": "https://www.google.com/search?sca_esv=c017efeea7a130ca&sxsrf=ADLYWILY4Ca4rqGXwGB5ZOOY--SF-lDpbQ:1727967010258&q=image&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWd8nbOJfsBGGB5IQQO6L3J_86uWOeqwdnV0yaSF-x2jon2iao6KWCaVjfn7ahz_sfz4kQc-hbvsXJ2gNx0RnV2nl305mvoek0YK94ylYY2a4b3Q-OEwW5lKppi2wujywZWmdIJVp8wrsv_g-eh5sWEDXx8JNpgmjsiKj2mZMvftPlZJZz&sa=X&ved=2ahUKEwiGytHeuvKIAxUfRvEDHc8SIKwQtKgLegQIFRAB&biw=1920&bih=919&dpr=1#vhid=tYmxDgFq4MrkJM&vssid=mosaic",
            "date": "test 123",
            "location": "test",
            "description": "test123"
        }...
    ]
}
```


### Test case 12: Successful get single event. (get request) (PASS)
**Prerequisite:** an event must be created before the get request is made, values for the event are:
```Json
{
    "events": [
        {
            "id": "abea75ac-2ffd-40a7-88da-8f91e807e931",
            "title": "test",
            "image": "https://www.google.com/search?sca_esv=c017efeea7a130ca&sxsrf=ADLYWILY4Ca4rqGXwGB5ZOOY--SF-lDpbQ:1727967010258&q=image&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWd8nbOJfsBGGB5IQQO6L3J_86uWOeqwdnV0yaSF-x2jon2iao6KWCaVjfn7ahz_sfz4kQc-hbvsXJ2gNx0RnV2nl305mvoek0YK94ylYY2a4b3Q-OEwW5lKppi2wujywZWmdIJVp8wrsv_g-eh5sWEDXx8JNpgmjsiKj2mZMvftPlZJZz&sa=X&ved=2ahUKEwiGytHeuvKIAxUfRvEDHc8SIKwQtKgLegQIFRAB&biw=1920&bih=919&dpr=1#vhid=tYmxDgFq4MrkJM&vssid=mosaic",
            "date": "test 123",
            "location": "test",
            "description": "test123"
        }
    ]
}
```
**GIVEN** get request is sent to the endpoint (localhost:8080/events/abea75ac-2ffd-40a7-88da-8f91e807e931)  
**AND** get request is made for the event with id "abea75ac-2ffd-40a7-88da-8f91e807e931"  
**WHEN** the server response is status code 200OK  
**THEN** server responds back with body result like this:  
```Json
{
    "events": [
        {
            "id": "abea75ac-2ffd-40a7-88da-8f91e807e931",
            "title": "test",
            "image": "https://www.google.com/search?sca_esv=c017efeea7a130ca&sxsrf=ADLYWILY4Ca4rqGXwGB5ZOOY--SF-lDpbQ:1727967010258&q=image&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWd8nbOJfsBGGB5IQQO6L3J_86uWOeqwdnV0yaSF-x2jon2iao6KWCaVjfn7ahz_sfz4kQc-hbvsXJ2gNx0RnV2nl305mvoek0YK94ylYY2a4b3Q-OEwW5lKppi2wujywZWmdIJVp8wrsv_g-eh5sWEDXx8JNpgmjsiKj2mZMvftPlZJZz&sa=X&ved=2ahUKEwiGytHeuvKIAxUfRvEDHc8SIKwQtKgLegQIFRAB&biw=1920&bih=919&dpr=1#vhid=tYmxDgFq4MrkJM&vssid=mosaic",
            "date": "test 123",
            "location": "test",
            "description": "test123"
        }
    ]
}
```


### Test case 13: Successful post an event. (post request) (PASS)
**Prerequisite:** must be authenticated  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**AND** the server response is status code 201 Created  
**AND** server responds back with body result like this:  
```Json
{
   	 "message": "Successfully created an event with id: "7ea82369-4aed-4a40-a923-f6440f1b22af".
}
```
**AND** "id" for the created event is autogenerated into id column in the database  
**AND** "title" for the created event is stored into the title column in the database  
**AND** "image" for the created event is stored into the date column in the database  
**AND** "date" for the created event is stored into the date column in the database  
**AND** "location" for the created event is stored into the location column in the database  
**AND** "description" for the created event is stored into the description column in the database  
**WHEN** a query with the following body is executed in the database: "SELECT * FROM events WHERE title = 'Chelsea vs Real Madrid';"  
**THEN** the results are displayed with all relevant information for the event "Chelsea vs Real Madrid."  

### Test case 14: Unsuccessful post an event (empty title value). (post request) (PASS)
**Prerequisite:** must be authenticated  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "title": "Invalid title."
    }
}
```


### Test case 15: Unsuccessful post an event (incorrect image path). (post request) (PASS)
**Prerequisite:** must be authenticated  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "Chelsea vs Real Madrid",
    "image": "test image",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "image": "Invalid image."
    }
}
```


### Test case 16: Unsuccessful post an event (empty image value). (post request) (PASS)
**Prerequisite:** must be authenticated  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "Chelsea vs Real Madrid",
    "image": "",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "image": "Invalid image."
    }
}
```


### Test case 17: Unsuccessful post an event (empty date value). (post request) (PASS)  
**Prerequisite:** must be authenticated  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "date": "Invalid date."
    }
}
```


### Test case 18: Unsuccessful post an event (empty location value). (post request) (BUG FOUND: wrong error message)
**Prerequisite:** must be authenticated  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "description": "Invalid location." 
    }
}
```


### Test case 19: Unsuccessful post an event (empty description value). (post request) (PASS)  
**Prerequisite:** must be authenticated  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": ""
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:   
```Json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "description": "Invalid description."
    }
}
```


### Test case 20: Unsuccessful post an event (empty values). (post request) (BUG FOUND: location field is missing and description has wrong error message into the response body)  
**Prerequisite:** must be authenticated  
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "",
    "image": "",
    "date": "",
    "location": "",
    "description": ""
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "Adding the event failed due to validation errors.",
    "errors": {
        "title": "Invalid title.",
        "description": "Invalid location.", 
        "date": "Invalid date.",
        "image": "Invalid image."
    }
}
```


### Test case 21: Unsuccessful post an event (no authentication). (post request) (PASS)
**GIVEN** post request is sent to the endpoint (localhost:8080//events)  
**AND** the body sent request is like this:  
```Json
{
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**WHEN** the server response is status code 401 Unauthorized  
**THEN** server responds back with body result like this:  
```Json
{
    	"message": "Not authenticated."
}
```


### Test case 22: Successful update an event. (put request) (PASS) 
**Prerequisite:** must be authenticated and event must be created before the put request is made, values for the created event are:
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)   
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"   
**AND** "title" value is changed to "Arsenal vs Chelsea"   
**AND** "image" value is changed to "https://www.google.com/search?q=chelsea+vs+arsenal&sa=X&sca_esv=6449538529ffda2f&sxsrf=ADLYWIKHcRdWSpPaLnw1KOmPktotjV7T8A:1728128222871&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWZJPk1C9buWu--tLPKEpSxLqGfZiWMqdk6VF37sVUbkcCZzyIYxI7k-o7WO4KAo4tz3ieDaNHs6EVSrt1BHiTIFtTDRi6YXThzSUwJRrSnm5zMwBGoy-jpViZmj9dV_1iOy6ac-YsEW3O7aX6bYvKKQOJ5K8oOAZuxJxWhTMtW78jCySK&ved=2ahUKEwijy-amk_eIAxXfBtsEHUphDjUQtKgLegQIARAJ&biw=1920&bih=919&dpr=1#vhid=cKYBxi5wmvXE5M&vssid=mosaic"   
**AND** "date" value is changed to "22.10.2023"  
**AND** "location" value is changed to "Hornsey Rd, London N7 7AJ, UK"    
**AND** "description" value is changed to "The biggest London derby is getting ready. Arsenal are welcoming Chelsea to the Emirates"   
**AND** the body sent request is like this:  
```Json
{
    "title": "Arsenal vs Chelsea",
    "image": "https://www.google.com/search?q=chelsea+vs+arsenal&sa=X&sca_esv=6449538529ffda2f&sxsrf=ADLYWIKHcRdWSpPaLnw1KOmPktotjV7T8A:1728128222871&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWZJPk1C9buWu--tLPKEpSxLqGfZiWMqdk6VF37sVUbkcCZzyIYxI7k-o7WO4KAo4tz3ieDaNHs6EVSrt1BHiTIFtTDRi6YXThzSUwJRrSnm5zMwBGoy-jpViZmj9dV_1iOy6ac-YsEW3O7aX6bYvKKQOJ5K8oOAZuxJxWhTMtW78jCySK&ved=2ahUKEwijy-amk_eIAxXfBtsEHUphDjUQtKgLegQIARAJ&biw=1920&bih=919&dpr=1#vhid=cKYBxi5wmvXE5M&vssid=mosaic",
    "date": "22.10.2023",
    "location": "Hornsey Rd, London N7 7AJ, UK",
    "description": "The biggest London derby is getting ready. Arsenal are welcoming Chelsea to the Emirates"
}
```
**AND** the server response is status code 201 Created  
**AND** server responds back with body result like this:  
```Json
{
   	"message": "Successfully updated the event with id: bf5249a7-f4a9-482e-af40-8f496aed0b96"
}
```
**AND** "title" for the event is updated in the title column into the database  
**AND** "image" for the event is updated in the image column into the database  
**AND** "date" for the event is updated in the date column into the database  
**AND** "location" for the event is updated in the location column into the database  
**AND** "description" for the event is updated in the description column into the database  
**WHEN** a query with the following body is executed into the database: "SELECT * FROM events WHERE title = 'Arsenal vs Chelsea';"  
**THEN** the results are displayed with all updated information for the event "Arsenal vs Chelsea."  


### Test case 23: Unsuccessful update an event. (empty title value) (put request) (PASS)
**Prerequisite:** must be authenticated and event must be created before the put request is made, values for the created event are:
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Arsenal vs Chelsea",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "22.10.2023",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "The biggest London derby is getting ready. Arsenal are welcoming Chelsea to the Emirates"
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)  
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"  
**AND** "title" value is left empty ""  
**AND** "image" value is changed to "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic"  
**AND** "date" value is changed to "19.11.2023"  
**AND** "location" value is changed to "Camp nou stadium"  
**AND** "description" value is changed to "Barcelona clashed Real Mdrid with outstanding 5-0"  
**AND** the body sent request is like this:  
```Json
{
    "title": "",
    "image": "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic",
    "date": "19.11.2023",
    "location": "Camp nou stadium",
    "description": "Barcelona clashed Real Mdrid with outstanding 5-0"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "Updating the event failed due to validation errors.",  
    "errors": {
        "title": "Invalid title."
    }
}
```


### Test case 24: Unsuccessful update an event. (incorrect image path) (put request) (PASS)
**Prerequisite:** must be authenticated and event must be created before the put request is made, values for the created event are:   
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)  
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"  
**AND** "title" value is changed to "Barcelona vs Real Madrid"  
**AND** "image" value is changed to "www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic"  
**AND** "date" value is changed to "19.11.2023"  
**AND** "location" value is changed to "Camp nou stadium"  
**AND** "description" value is changed to "Barcelona clashed Real Mdrid with outstanding 5-0"  
**AND** the body sent request is like this:  
```Json
{
    "title": "Barcelona vs Real Madrid",
    "image": "www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic",
    "date": "19.11.2023",
    "location": "Camp nou stadium",
    "description": "Barcelona clashed Real Mdrid with outstanding 5-0"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "image": "Invalid image."
    }
}
```


### Test case 25: Unsuccessful update an event. (empty image value) (put request) (PASS)
**Prerequisite:** must be authenticated and event must be created before the put request is made, values for the created event are:  
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)  
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"  
**AND** "title" value is changed to "Barcelona vs Real Madrid"  
**AND** "image" value is left empty ""  
**AND** "date" value is changed to "19.11.2023"  
**AND** "location" value is changed to "Camp nou stadium"  
**AND** "description" value is changed to "Barcelona clashed Real Mdrid with outstanding 5-0"  
**AND** the body sent request is like this:
```Json
{
    "title": "Barcelona vs Real Madrid",
    "image": "",
    "date": "19.11.2023",
    "location": "Camp nou stadium",
    "description": "Barcelona clashed Real Mdrid with outstanding 5-0"
}
```
**WHEN** the server response is status 422 Unprocessable Entity  
**THEN** server responds back with body result like this:
```Json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "image": "Invalid image."
    }
}
```


### Test case 26: Unsuccessful update an event. (empty date value) (put request) (PASS)
**Prerequisite:** must be authenticated and event must be created before the put request is made, values for the created event are:  
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)  
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"  
**AND** "title" value is changed to "Barcelona vs Real Madrid"  
**AND** "image" value is changed to "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic"  
**AND** "date" value is left empty ""  
**AND** "location" value is changed to "Camp nou stadium"  
**AND** "description" value is changed to "Barcelona clashed Real Mdrid with outstanding 5-0"   
**AND** the body sent request is like this:  
```Json 
{
    "title": "Barcelona vs Real Madrid",
    "image": "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic",
    "date": "",
    "location": "Camp nou stadium",
    "description": "Barcelona clashed Real Mdrid with outstanding 5-0"
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:
```Json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "date": "Invalid date."
    }
}
```


### Test case 27: Unsuccessful update an event. (empty location value) (put request) (BUG FOUND: invalid error message into the response body)
**Prerequisite:** must be authenticated and event must be created before the put request is made, values for the created event are:  
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"
**AND** "title" value is changed to "Barcelona vs Real Madrid"
**AND** "image" value is changed to "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic"      
**AND** "date" value is changed to "19.11.2023"  
**AND** "location" value is left empty ""  
**AND** "description" value is changed to "Barcelona clashed Real Mdrid with outstanding 5-0"  
**AND** the body sent request is like this:
```Json
{
    "title": "Barcelona vs Real Madrid",
    "image": "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic",
    "date": "19.11.2023",
    "location": "",
    "description": "Barcelona clashed Real Mdrid with outstanding 5-0"
}
```
**WHEN** the server response is status 422 Unprocessable Entity
**THEN** server responds back with body result like this:
```Json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "description": "Invalid location."
    }
}
```


### Test case 28: Unsuccessful update an event. (empty description value) (put request) (PASS)
**Prerequisite:** must be authenticated and event must be created before the put request is made, values for the created event are:   
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)  
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"  
**AND** "title" value is changed to "Barcelona vs Real Madrid"  
**AND** "image" value is changed to "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic"  
**AND** "date" value is changed to "19.11.2023"    
**AND** "location" value is changed to "Camp nou stadium"    
**AND** "description" value is left empty ""   
**AND** the body sent request is like this:   
```Json
{
    "title": "Barcelona vs Real Madrid",
    "image": "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic",
    "date": "19.11.2023",
    "location": "Camp nou stadium",
    "description": ""
}
```
**WHEN** the server response is status code 422 Unprocessable Entity   
**THEN** server responds back with body result like this:    
```Json
{
    "message": "Updating the event failed due to validation errors.",
    "errors": {
        "description": "Invalid description."
    }
}
```


### Test case 29: Unsuccessful update an event. (empty values) (put request) (FAILED) (BUG FOUND: location field is missing and description has wrong error message into the response body)
**Prerequisite:** must be authenticated and event must be created before the put request is made, values for the created event are:
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)  
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"  
**AND** "title" value is left empty to ""  
**AND** "image" value is left empty ""  
**AND** "date" value is left empty ""  
**AND** "location" value is left empty ""  
**AND** "description" value is left empty ""  
**AND** the body sent request is like this:  
```Json
{
    "title": "",
    "image": "",
    "date": "",
    "location": "",
    "description": ""
}
```
**WHEN** the server response is status code 422 Unprocessable Entity  
**THEN** server responds back with body result like this:  
```Json
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


### Test case 30: Unsuccessful update an event. (without authorization) (put request) (PASS)
**Prerequisite:** event must be created before the put request is made, values for the created event are:  
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid",
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** put request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)  
**AND** put request is sent to update an event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"  
**AND** "title" value is changed to "Barcelona vs Real Madrid"  
**AND** "image" value is changed to "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic"  
**AND** "date" value is changed to "19.11.2023"    
**AND** "location" value is changed to "Camp nou stadium"  
**AND** "description" value is changed to "Barcelona clashed Real Mdrid with outstanding 5-0"  
**AND** the body sent request is like this:   
```Json
{
    "title": "Barcelona vs Real Madrid",
    "image": "https://www.google.com/search?q=barcelona+vs+real+madrid+5-0&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIK_1v0OQEu0PvCLW9HnS5alNJAL3Q:1728129238410&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWfbQph1uib-VfD_izZO2Y5sC3UdQE5x8XNnxUO1qJLaRUGL3qWeTjomUBn_ET6Fuv_qSaM3rhwwsquyvDNiYf4AhvdBxGGsWflOn_SbrhPXYX5yM2t20jqoy2rNsk0mrBYfHYbJ0YV2cKhkr5ymbgfG1LLBFkDU7vvLETjiF9Gtx3xeMX&ved=2ahUKEwiTmYaLl_eIAxUkQ_EDHUGjJwsQtKgLegQIARAH#vhid=x2lrDEtUdViTaM&vssid=mosaic",
    "date": "19.11.2023",
    "location": "Camp nou stadium",
    "description": "Barcelona clashed Real Mdrid with outstanding 5-0"
}
```
**WHEN** the server response is status code 401 Unauthorized  
**THEN** server responds back with body result like this:  
```Json
{
    	"message": "Not authenticated."
}
```

### Test case 31: Successful delete event. (delete request)  
**Prerequisite:** must be authenticated and event must be created before the delete request is made, values for the created event are:   
```Json
{
    "id": "bf5249a7-f4a9-482e-af40-8f496aed0b96",
    "title": "Chelsea vs Real Madrid", 
    "image": "https://www.google.com/search?q=chelsea+real+madrid+champions+league&sa=X&sca_esv=6449538529ffda2f&biw=1920&bih=919&sxsrf=ADLYWIKkxV_14VMyWjuLeQqYe3fQxr176Q:1728127105555&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWtG_mNb-HwafvV8cKK_h1a5eSkyfXNgRC6a9SKsMT7FKGHfCChT2AOXKYl9WIi4ozGsTO9EqXyT6BUycaiOtZASilP0aswwYMt3ji6-Gvj7uCIIu12wy_q-WqlXZ29dkbzUPlWHWh2_-ilqmjZ8mMeT3ihVnPGKxgitNMubrmzNRUnHH9&ved=2ahUKEwi1hoOSj_eIAxUhVvEDHY5qKyYQtKgLegQIBhAH#vhid=cFOGWgfQvoVOXM&vssid=mosaic",
    "date": "27.04.2021",
    "location": "Fulham Rd., London SW6 1HS, UK, Stamford Bridge",
    "description": "Real Madrid host Chelsea in the UEFA Champions League semi-final first leg on Tuesday 27 April at 21:00 CET."
}
```
**GIVEN** delete request is sent to the endpoint (localhost:8080//events/bf5249a7-f4a9-482e-af40-8f496aed0b96)   
**AND** a delete request is made for the event with id "bf5249a7-f4a9-482e-af40-8f496aed0b96"  
**AND** the server response is status code 200OK  
**AND** server responds back with body result like this:  
```Json
{
    "message": "Successfully deleted the event with id: bf5249a7-f4a9-482e-af40-8f496aed0b96"
}
```
**WHEN** a query with the following body is executed in the database: "SELECT * FROM events WHERE title = 'Chelsea vs Real Madrid';"   
**THEN** no event with the id "bf5249a7-f4a9-482e-af40-8f496aed0b96" is existing into the database    

