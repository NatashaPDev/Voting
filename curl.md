### API Documentation

Get all users
----
  Returns json data about users.
    
* **URL**

  /voting/rest/admin/users
  
* **Method:**

  `GET`
  
* **URL Params**

  None
  
* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ id : 12, name : "Michael Bloom" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "User doesn't exist" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  `curl -s http://localhost:8081/voting/rest/admin/users --user admin@gmail.com:admin`

Get a single user
----
Returns json data about a single user.
    
* **URL**

  /voting/rest/admin/users/:id
  
* **Method:**

  `GET`
  
* **URL Params**

  **Required:**
   
  `id=[integer]`
  
* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ id : 12, name : "Michael Bloom" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "User doesn't exist" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  `curl -s http://localhost:8081/voting/rest/admin/users/100001 --user admin@gmail.com:admin`


Get the history of dishes
----
Returns json data about all history of dishes.
    
* **URL**

  /voting/rest/dishes
  
* **Method:**

  `GET`
  
* **URL Params**

  None
  
* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ id : 12, name : "Michael Bloom" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "User doesn't exist" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**
    
    `curl -s http://localhost:8081/voting/rest/dishes --user admin@gmail.com:admin`
    
Add a restaurant
----
Creates a new restaurant from json data. Only for admins.
    
* **URL**

  /voting/rest/restaurants
  
* **Method:**

  `POST`
  
* **URL Params**

  None
  
* **Data Params**

  {
  
  "name":[string]
  
  }

* **Success Response:**

  * **Code:** 201 <br />
    **Content:** `{
                     "id": 100019,
                     "name": "Super Star"
                  }`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s -X POST -d '{"name":"Pizza"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8081/voting/rest/restaurants --user admin@gmail.com:admin`
    
Add a dish
----
Adds a new dish in restaurant from json data. Only for admins.
    
* **URL**

  /voting/rest/restaurants/:restaurant_id/dishes
  
* **Method:**

  `POST`
  
* **URL Params**

  **Required:**
     
    `restaurant_id=[integer]`
  
* **Data Params**

  {
  
  "date":[string],
  
  "description":[string],
  
  "price":[integer]
  
  }

* **Success Response:**

  * **Code:** 201 <br />
    **Content:** `{
                     "id": 100033,
                     "date": "2015-06-03",
                     "description": "Fish",
                     "price": 300,
                     "restaurant": null
                  }`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s -X POST -d '{"date":"2015-06-03","description":"Fish","price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8081/voting/rest/restaurants/100002/dishes --user admin@gmail.com:admin`
    
Get all restaurants
----
Returns json data about all restaurants. Only for admins.
    
* **URL**

  /voting/rest/restaurants
  
* **Method:**

  `GET`
  
* **URL Params**

  None
  
* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[
                        {
                        "id": 100024,
                        "name": "Burger King"
                     },
                        {
                        "id": 100020,
                        "name": "Cucumber"
                     },
                  ]`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s http://localhost:8081/voting/rest/restaurants --user admin@gmail.com:admin`
    
Get the restaurant menu for the date
----
Returns json data about dishes in the restaurant for the date.
    
* **URL**

  /voting/rest/restaurants/:restaurant_id/dishes
  
* **Method:**

  `GET`
  
* **URL Params**

  **Required:**
     
    `restaurant_id=[integer]`
  
* **Data Params**

  None

* **Success Response:**

  * **Code:** 201 <br />
    **Content:** `[
                     {
                        "id": 100033,
                        "date": "2015-06-03",
                        "description": "Potato",
                        "price": 200,
                        "restaurant": 100002
                     },
                        {
                        "id": 100034,
                        "date": "2015-06-03",
                        "description": "Fish",
                        "price": 300,
                        "restaurant": 100002
                     }
                  ]`
 
* **Error Response:**

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s http://localhost:8081/voting/rest/restaurants/100002/dishes?date=2015-06-03 --user user@yandex.ru:password`
            
Post vote
----
Add a new vote for the restaurant from user. If user votes again the same day and it is after 11:00 then vote can't be changed.
    
* **URL**

  /voting/rest/votes
  
* **Method:**

  `POST`
  
* **URL Params**

  None
  
* **Data Params**

  {
  
  "dateTime":[string],
  
  "restaurant":{"id":[integer]}
  
  }

* **Success Response:**

  * **Code:** 201 <br />
    **Content:** `{
                     "id": 100035,
                     "dateTime": "2015-06-01T10:30:00",
                     "user": null,
                     "restaurant": {"id": 100003}
                  }`
 
* **Error Response:**

  * **Code:** 406 NOT ACCEPTABLE <br />
    **Content:** `{ error : "It is too late, vote can't be changed" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s -X POST -d '{"dateTime":"2015-06-01T10:30","restaurant":{"id":100003}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8081/voting/rest/votes --user user@yandex.ru:password`
    
Get the history of votes
----
Returns json data about all history of votes.
    
* **URL**

  /voting/rest/votes
  
* **Method:**

  `GET`
  
* **URL Params**

  None
  
* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ id : 12, name : "Michael Bloom" }`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s http://localhost:8081/voting/rest/votes --user admin@gmail.com:admin`
                    