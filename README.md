[API documentation](https://github.com/NatashaPDev/Voting#api-documentation)

* [Get all users](https://github.com/NatashaPDev/Voting#get-all-users)
* [Get user](https://github.com/NatashaPDev/Voting#get-user)
* [Add user](https://github.com/NatashaPDev/Voting#add-user)
* [Get restaurants](https://github.com/NatashaPDev/Voting#get-restaurants)
* [Add restaurant](https://github.com/NatashaPDev/Voting#add-restaurant)
* [Get restaurant menu by date](https://github.com/NatashaPDev/Voting#get-restaurant-menu-by-date)
* [Add dish](https://github.com/NatashaPDev/Voting#add-dish)
* [Post vote](https://github.com/NatashaPDev/Voting#post-vote)
* [Get dishes history](https://github.com/NatashaPDev/Voting#get-dishes-history)
* [Get votes history](https://github.com/NatashaPDev/Voting#get-votes-history)

[Caching](https://github.com/NatashaPDev/Voting#caching)

# API Documentation

Get all users
----
  Returns JSON data about users. Only for admins.
    
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
    **Content:** `[
                        {
                        "id": 100001,
                        "name": "Admin",
                        "email": "admin@gmail.com",
                        "password": "admin",
                        "enabled": true,
                        "registered": "2018-01-08T10:29:42.409+0000",
                        "roles": ["ROLE_ADMIN"]
                     },
                        {
                        "id": 100000,
                        "name": "User",
                        "email": "user@yandex.ru",
                        "password": "password",
                        "enabled": true,
                        "registered": "2018-01-08T10:29:42.409+0000",
                        "roles": ["ROLE_USER"]
                     }
                  ]`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**

  `curl -s http://localhost:8080/voting/rest/admin/users --user admin@gmail.com:admin`

Get user
----
Returns JSON data about a single user.
    
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
    **Content:** `{
                     "id": 100000,
                     "name": "User",
                     "email": "user@yandex.ru",
                     "password": "password",
                     "enabled": true,
                     "registered": "2018-01-08T10:29:42.409+0000",
                     "roles": ["ROLE_USER"]
                  }`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**

  `curl -s http://localhost:8080/voting/rest/admin/users/100001 --user admin@gmail.com:admin`
  
Add user
----
  Creates a new user. Only for admins.
    
* **URL**

  /voting/rest/admin/users
  
* **Method:**

  `POST`
  
* **URL Params**

  None
  
* **Data Params**

  {
  
  "name":[string],
  
  "email":[string],
  
  "password":[string],
  
  "enabled":[boolean],
  
  "registered":[timestamp],
  
  "roles":["ROLE_USER"] or ["ROLE_ADMIN"]
  
  }

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `   {
                        "id": 100001,
                        "name": "Admin",
                        "email": "admin@gmail.com",
                        "password": "admin",
                        "enabled": true,
                        "registered": "2018-01-08T10:29:42.409+0000",
                        "roles": ["ROLE_ADMIN"]
                     }`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**

  `curl -s -X POST -d '{"name":"user3","email":"user3@yandex.ru","password":"password","enabled":true,"registered":"2018-01-03T10:54:53.575+0000","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/admin/users --user admin@gmail.com:admin`  

Get restaurants
----
Returns JSON data about restaurants. With "date" parameter returns only restaurants having a menu for the date.
    
* **URL**

  /voting/rest/restaurants?date=:date
  
* **Method:**

  `GET`
  
* **URL Params**

  **Optional:**
     
    `date=[string]`
  
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
    
    `curl -s http://localhost:8080/voting/rest/restaurants?date=2018-01-01 --user admin@gmail.com:admin`
       
   
Add restaurant
----
Creates a new restaurant from JSON data. Only for admins.
    
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
    
    `curl -s -X POST -d '{"name":"Pizza"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/restaurants --user admin@gmail.com:admin`
 
Get restaurant menu by date
----
Returns JSON data about dishes in the restaurant for the date.
    
* **URL**

  /voting/rest/restaurants/:restaurant_id/dishes?date=:date
  
* **Method:**

  `GET`
  
* **URL Params**

  **Required:**
     
    `restaurant_id=[integer], date=[string]`
  
* **Data Params**

  None

* **Success Response:**

  * **Code:** 201 <br />
    **Content:** `[
                     {
                        "id": 100033,
                        "date": "2018-01-01",
                        "description": "Potato",
                        "price": 200,
                        "restaurant": 100002
                     },
                        {
                        "id": 100034,
                        "date": "2018-01-01",
                        "description": "Fish",
                        "price": 300,
                        "restaurant": 100002
                     }
                  ]`
 
* **Error Response:**

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s http://localhost:8080/voting/rest/restaurants/100002/dishes?date=2018-01-01 --user user@yandex.ru:password`
 
    
Add dish
----
Adds a new dish to a restaurant from JSON data. Only for admins.
    
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
                     "date": "2018-01-03",
                     "description": "Fish",
                     "price": 300,
                     "restaurant": null
                  }`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s -X POST -d '{"date":"2018-01-03","description":"Fish","price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/restaurants/100002/dishes --user admin@gmail.com:admin`
    

            
Post vote
----
Adds a new vote for the restaurant from a user. If a user votes again the same day and it is after 11:00 then vote can't be changed.
    
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
                     "dateTime": "2018-01-03T10:30:00",
                     "user": null,
                     "restaurant": {"id": 100002}
                  }`
 
* **Error Response:**

  * **Code:** 406 NOT ACCEPTABLE <br />
    **Content:** `{ error : "It is too late, vote can't be changed" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s -X POST -d '{"dateTime":"2015-01-03T10:30","restaurant":{"id":100002}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/votes --user user@yandex.ru:password`
 
Get dishes history
----
Returns JSON data about all history of dishes.
    
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
    **Content:** `[
                        {
                        "id": 100004,
                        "date": "2018-01-01",
                        "description": "Chicken",
                        "price": 500,
                        "restaurant": 100002
                     },
                        {
                        "id": 100005,
                        "date": "2018-01-01",
                        "description": "Salad",
                        "price": 1000,
                        "restaurant": 100002
                     }
                    ]` 
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />
  
    OR
  
  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s http://localhost:8080/voting/rest/dishes --user admin@gmail.com:admin` 
    
Get votes history
----
Returns JSON data about all history of votes.
    
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
    **Content:** 
    `[
           {
              "id": 100019,              
              "dateTime": "2018-01-02T10:00:00",
              "restaurant": 100002,
              "user": 100000
           },
              {
              "id": 100020,
              "dateTime": "2018-01-02T10:00:00",
              "restaurant": 100003,
              "user": 100001
           }
    ]`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

  OR

  * **Code:** 401 UNAUTHORIZED <br />

* **Sample Call:**
    
    `curl -s http://localhost:8080/voting/rest/votes --user admin@gmail.com:admin`
                    
# Caching 

2nd Level
----        
         
* **User**
* **Restaurant**
* **Dish**

Query
----

* **`UserServiceImpl List<User> getAll()`**
* **`DishServiceImpl List<Dish> getByDate(LocalDate date, int restaurantId)`**
* **`DishServiceImpl List<Restaurant> getRestaurantsByDate(LocalDate date)`**          
