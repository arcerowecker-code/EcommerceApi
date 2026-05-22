# Ecommerce API Lab - Laboratory 7
**Developer:**  Arce & Espelimbergo
**Course:** WS101 - Web Services

##  Project Overview
This is a **Spring Boot REST API** developed as part of Laboratory 7. It serves as a backend for a product catalog, demonstrating the use of the **Model-Service-Controller** architecture. [span_1](start_span)[span_2](start_span)The project handles standard HTTP operations (CRUD) and uses in-memory data storage[span_1](end_span)[span_2](end_span).

## Setup Instructions
1. **[span_3](start_span)Clone/Download** this repository to your local machine[span_3](end_span).
2. [span_4](start_span)Open the project in **VS Code** or **IntelliJ IDEA**[span_4](end_span).
3. Open the terminal in the project root directory.
4. [span_5](start_span)Run the application using the Gradle wrapper[span_5](end_span):
   ```powershell
   ./gradlew bootRun

 5. The API will be accessible at http://localhost:8080/api/v1/products.
## API Endpoint Reference
| Method | Path | Description | Expected Status |
|---|---|---|---|
| *GET* | /api/v1/products | Retrieve all products in the catalog. | 200 OK |
| *GET* | /api/v1/products/{id} | Retrieve a single product by ID. | 200 OK / 404 Not Found |
| *POST* | /api/v1/products | Create a new product entry. | 201 Created |
| *PUT* | /api/v1/products/{id} | Replace an entire product record. | 200 OK |
| *DELETE* | /api/v1/products/{id} | Remove a product from the list. | 204 No Content |
## Sample Request & Response
*POST /api/v1/products*
 * *Request Body:*
   
   {
     "name": "Gaming Mouse",
     "description": "RGB Wireless Mouse",
     "price": 1500.0,
     "category": "Peripherals",
     "stock": 25
   }
   
   
 * *Response (201 Created):*
   
   {
     "id": 11,
     "name": "Gaming Mouse",
     "description": "RGB Wireless Mouse",
     "price": 1500.0,
     "category": "Peripherals",
     "stock": 25
   }
   
   
##  Known Limitations
 * *In-Memory Storage:* This application uses a List<Product> for storage. *Data is volatile* and will be lost whenever the server is restarted.

---

# **2. Code Documentation (Java Files)**
[span_11](start_span)Every public method below includes the mandatory **Javadoc** (`/** ... */`) syntax required for your grade[span_11](end_span).

### **ProductService.java**
```java
package com.ws101.arce.ecommerceapi.service;

import com.ws101.arce.ecommerceapi.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

 @author Arce
 
@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

      * @return a List containing all products in the catalog.
     
    public List<Product> getAllProducts() {
        return products;
    }

    
    
      @param product The product object to be added.
     )@throws IllegalArgumentException
    public void addProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        products.add(product);
    }
}

### *ProductController.java*
package com.ws101.arce.ecommerceapi.controller;

import com.ws101.arce.ecommerceapi.model.Product;
import com.ws101.arce.ecommerceapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

   
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
}

