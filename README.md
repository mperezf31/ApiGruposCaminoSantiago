# Recipe Manger

**Authentication**
----
To use this API you need the "Authentication" header with the token: 

  ```
  VYpL501h7NNg4lzUmW54kXGgRamZh7FihnoRIa
  ```
  
**Get recipes**
----
  Returns the list of recipes.

* **URL**

  /recipes

* **Method:**

  `GET`
  
* **Accept:**

  `application/json` OR `application/xml`
  
* **Accept-Language:**

  `es` OR `en`
  
*  **URL Params**
 
    **Optional:**

   `category=[string]`

* **Data Params**

    **None**

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 404 Not found <br />
  * **Code:** 406 Not Acceptable <br />

* **Sample Call:**

  ```
  http://localhost:9000/recipes?category=tortilla
  ```

* **Sample Response Json:**

  ```
  [
       {
            "id": 8,
            "whenCreated": 1548005503000,
            "title": "Tortilla al horno",
            "description": "Receta de Tortilla al horno rellena y jugosa",
            "serves": 4,
            "preparationTime": 30,
            "ingredients": [{
                    "id": 3,
                    "name": "huevos"
                },
                {
                    "id": 4,
                    "name": "patatas"
                },...],
            "steps": [{
                "id": 15,
                "description": "Mezcla en la batidora los huevos junto con la harina, la nata y una pizca de sal."
            },...],
            "categories": [{
                "id": 7,
                "name": "tortilla"
            }...],
            "nutritionalData": {
                "calories": 12,
                "protein": 17,
                "carbohydrates": 223,
                "fat": 123
            }
       }
  ]
    
  ```
  
**Get recipe**
----

Get a recipe by ID.

* **URL**

  /recipe/:id

* **Method:**

  `GET`
  
* **Accept:**

  `application/json` OR `application/xml`
  
* **Accept-Language:**

  `es` OR `en`
  
*  **URL Params**
 
    **None**

* **Data Params**

    **None**

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 404 Not found <br />
  * **Code:** 406 Not Acceptable <br />
  
* **Sample Response Json:**

  ```
   {
   	"id": 8,
   	"whenCreated": 1548005503000,
   	"title": "Tortilla al horno",
   	"description": "Receta de Tortilla al horno rellena y jugosa",
   	"serves": 4,
   	"preparationTime": 30,
   	"ingredients": [{
   			"id": 3,
   			"name": "huevos"
   		},
   		{
   			"id": 4,
   			"name": "patatas"
   		},...],
   	"steps": [{
   		"id": 15,
   		"description": "Mezcla en la batidora los huevos junto con la harina, la nata y una pizca de sal."
   	},...],
   	"categories": [{
   		"id": 7,
   		"name": "tortilla"
   	}...],
   	"nutritionalData": {
   		"calories": 12,
   		"protein": 17,
   		"carbohydrates": 223,
   		"fat": 123
   	}
   }
    
  ```
    
* **Sample Response Xml:**

  ```
    <recipe>
        <id>8</id>
        <whenCreated>2019-01-20 18:31:43.0</whenCreated>
        <title>Tortilla al horno</title>
        <description>Receta de Tortilla al horno rellena y jugosa</description>
        <serves>4</serves>
        <preparationTime>30</preparationTime>
        <ingredients>
            <ingredient>
                <id>3</id>
                <name>huevos</name>
            </ingredient>
            <ingredient>
                <id>4</id>
                <name>patatas</name>
            </ingredient>
            <ingredient>
                <id>5</id>
                <name>aceite</name>
            </ingredient>
            ...
        </ingredients>
        <steps>
            <step>
                <id>15</id>
                <description>Mezcla en la batidora los huevos junto con la harina, la nata y una pizca de sal.</description>
            </step>
            ...
        </steps>
        <categories>
            <category>
                <id>7</id>
                <name>tortilla</name>
            </category>
            ...
        </categories>
        <nutritionalData>
            <calories>12</calories>
            <protein>17</protein>
            <carbohydrates>223</carbohydrates>
            <fat>123</fat>
        </nutritionalData>
    </recipe>
  ```
 
**Add recipe**
----

* **URL**

  /recipe

* **Method:**

  `POST`

* **Accept:**

  `application/json` OR `application/xml`
  
* **Accept-Language:**

  `es` OR `en`
  
*  **URL Params**
 
    **None**

* **Data Params**

    **Required:**
    
   `title=[string]`
   
   `description=[string]`
   
   `serves=[integer]`
      
   `ingredients=[integer]`
   
   `steps=[Steps]`
   
   `categories=[Categories]`
   
    **Optional:**

   `preparationTime=[integer]`
   
   `nutritionalData=[NutritionalData]`


* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 400 Bad Request <br />
  * **Code:** 406 Not Acceptable <br />
  
* **Sample Call:**

  ```
  {
    "title": "Tortilla al horno",
    "description": "Receta de Tortilla al horno rellena y jugosa",
    "serves": 4,
    "preparationTime": 30,
    "ingredients": [{
        "name": "huevos"
    }, {
        "name": "harina"
    }, {
        "name": "aceite"
    },...],
    "steps": [{
        "description": "Mezcla en la batidora los huevos junto con la harina, la nata y una pizca de sal."
    },...],
    "categories": [{
         "name": "tortilla"
    },...],
    "nutritionalData": {
        "calories": 12,
        "protein": 17,
        "carbohydrates": 223,
        "fat": 123
    }
  }
  ```

**Update recipe**
----

Update the recipe data.

* **URL**

  /recipe/:id 

* **Method:**

  `PUT`

* **Accept:**

  `application/json` OR `application/xml`
  
* **Accept-Language:**

  `es` OR `en`
  
*  **URL Params**
 
    **None**

* **Data Params**

  **Required:**
    
   `title=[string]`
   
   `description=[string]`
   
   `serves=[integer]`
      
   `ingredients=[integer]`
   
   `steps=[Steps]`
   
   `categories=[Categories]`
   
    **Optional:**

   `preparationTime=[integer]`
   
   `nutritionalData=[NutritionalData]`

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 404 Not found <br />
  * **Code:** 400 Bad Request <br />
  * **Code:** 406 Not Acceptable <br />
  
  
**Delete recipe**
----

Delete a specific recipe.

* **URL**

  /recipe/:id

* **Method:**

  `DELETE`
  
*  **URL Params**
 
    **None**

* **Data Params**

    **None**

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 404 Not found <br />
  


