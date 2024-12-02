Journey of Joy
A dynamic travel management platform where users can plan packages, choose destinations, and get personalized hotel recommendations with pricing based on 
ratings and discounts.

Table of Contents
Project Overview
Features
Technology Stack
Installation
Usage
Pricing Logic
Database Schema
Screenshots
Contributing




Project Overview
Journey of Joy is a travel platform that simplifies vacation planning. Users can select packages, specify their desired destination, and choose travel dates.
The system recommends hotels in the selected location with detailed pricing and discounts. The pricing is dynamically calculated based on the hotel’s rating and stay 
price per day, ensuring a personalized and budget-friendly experience.

Features

User Features:

Package Selection: Users can explore and choose from multiple packages.
Destination and Date Selection: Plan trips by specifying destinations, start dates, and end dates.
Hotel Recommendations:
Displays hotels at the selected destination.
Provides detailed pricing, including discounts based on hotel ratings.
Dynamic Pricing: Calculates total stay cost based on hotel rating and price per day.
Admin Features:

Manage holiday packages, destinations, and hotel details.
Monitor user bookings and platform activities.
Other Highlights:

Responsive and user-friendly design.
Real-time data updates for destinations and packages.
Technology Stack
Frontend: ReactJS
Backend: Spring Boot
Database: MySQL
Tools & Libraries:
Postman (API testing)
Axios (HTTP client)
CSS Frameworks (e.g., Bootstrap)
GitHub (Version control)
Installation
Prerequisites:
Node.js
Java JDK 8 or later
MySQL Server
Git
Steps:
Clone the repository:


Set up the Backend:

Navigate to the backend folder:
bash
Copy code
cd backend  
Configure the database connection in application.properties:
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/journey_of_joy  
spring.datasource.username=your_username  
spring.datasource.password=your_password  
Build and run the Spring Boot application:
bash
Copy code
mvn clean install  
mvn spring-boot:run  
Set up the Frontend:

Navigate to the frontend folder:
bash
Copy code
cd frontend  
Install dependencies and start the development server:
bash
Copy code
npm install  
npm start  
Usage
Open the frontend in your browser at http://localhost:3000.
Register as a new user or log in.
Select a holiday package, specify your destination and travel dates.
View hotel options with pricing details and book your stay.
Pricing Logic
The total price of a hotel stay is calculated dynamically using the following formula:

mathematica
Copy code
Total Price = (Price per Day × Number of Days) - Discount  
Discount: Based on the hotel’s rating (higher-rated hotels may have better discounts).
Number of Days: Calculated using the selected start and end dates.
Database Schema
The database consists of the following main tables:

Users: Stores user details like ID, name, email, and role (user/admin).
Packages: Contains details of available holiday packages.
Destinations: Stores destination-specific information.
Hotels: Includes hotel details such as name, rating, price per day, and discounts.
Bookings: Tracks user bookings and associated details.
Screenshots
Package Selection:

Hotel Recommendations:

Admin Dashboard:

Contributing
We welcome contributions to enhance Journey of Joy! Follow these steps:

Fork the repository.
Create a new branch (feature/your-feature-name).
Commit your changes and push to your branch.
Open a pull request.
