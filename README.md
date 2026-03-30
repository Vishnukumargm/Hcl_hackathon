
# 🍽️ Food Ordering System (MVP)

A full-stack food ordering application built for a hackathon using **React + Spring Boot + MySQL**.

This is a **Minimum Viable Product (MVP)** with a simplified architecture (no authentication).  
Users can browse products and place orders without login.

---

## 🚀 Tech Stack

**Frontend**
- React (Vite)
- Bootstrap
- Axios

**Backend**
- Spring Boot
- Spring Data JPA
- MySQL

---

## 🧠 Architecture

```

Frontend (React)
↓ Axios API Calls
Backend (Spring Boot)
↓
Controller → Service → Repository → Database

````

---

## ⚡ Features

- View all products
- Filter by category
- Add to cart
- Place order
- Automatic stock deduction
- View all orders (demo/admin)

---

## ❗ MVP Limitation

- No authentication (no login/signup)
- No user table
- Open API (anyone can access)

---

## 🗄️ Database Schema

Tables used:
- products
- orders
- order_items

Notes:
- No `users` table
- `customerName` is stored in orders
- No authentication required

---

## 🔌 API Endpoints

### 📦 Products

**GET /api/products**

Response:
```json
{
  "status": "success",
  "message": "Products fetched successfully",
  "data": []
}
````

**GET /api/products?category=PIZZA**

---

### 🧾 Orders

**POST /api/orders**

Request:

```json
{
  "customerName": "Arjun",
  "items": [
    { "productId": 1, "quantity": 2 }
  ]
}
```

Response:

```json
{
  "status": "success",
  "message": "Order placed successfully",
  "data": {
    "orderId": 101,
    "status": "CONFIRMED"
  }
}
```

---

**GET /api/orders**

---

**GET /api/orders/{orderId}**

---

## ⚙️ Backend Setup

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/food_app
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

Run backend:

```bash
mvn spring-boot:run
```

---

## 💻 Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

---

## 🔄 CORS Config

```java
allowedOrigins("http://localhost:5173")
```

---

## 📦 Dependencies

* spring-boot-starter-web
* spring-boot-starter-data-jpa
* mysql-connector-j
* spring-boot-starter-validation
* lombok

---

## 👥 Team

| Member | Role                   |
| ------ | ---------------------- |
| M1     | Backend - Product      |
| M2     | Backend - Order        |
| M3     | Frontend - Menu & Cart |
| M4     | Frontend - Orders      |

---

## 📌 Future Improvements

* Add JWT Authentication
* User accounts
* Payment integration
* Admin dashboard




