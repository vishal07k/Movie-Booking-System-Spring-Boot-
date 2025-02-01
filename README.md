# Movie Booking Application

## Overview

The **Movie Booking Application** allows users to browse and book movie tickets, while administrators can manage the movie listings, theaters, and showtimes. The application is designed to provide a seamless movie booking experience, where users can select movies, view movie details, and reserve seats for available shows. The admin has the ability to add new movies, theaters, and manage shows in the system.

## Features

### For Users:
- **Login and Authentication**: Users can create an account and log in to the application.
- **Search Movies**: Users can search for movies by title or genre.
- **Browse Movie Categories**: Users can explore movies based on categories such as "Latest Movies", "Popular Movies", etc.
- **Movie Details**: Users can view detailed information about movies, including genre, duration, and poster.
- **Book Tickets**: Users can book tickets for a specific movie show at a theater of their choice.
- **Booking History**: Users can view their booking history and track their past movie bookings.
- **Responsive UI**: The application offers a responsive design, making it accessible on desktops and mobile devices.

### For Admin:
- **Admin Login**: Admins can log in to the application to access the admin dashboard.
- **Add Movies**: Admins can add new movies to the platform, providing movie name, genre, poster, duration, and description.
- **Manage Theaters**: Admins can add new theaters to the system, specifying the theater's name, location, and available seating capacity.
- **Manage Showtimes**: Admins can add or update showtimes for different movies in different theaters.
- **View User Bookings**: Admins can see the movie bookings made by users, with details about the movie, theater, and the user who made the booking.

## Technologies Used

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Java, Spring Boot (Thymeleaf)
- **Database**: MySQL (or any preferred relational database)
- **Authentication**: Spring Security
- **Deployment**: (Add your deployment method here, e.g., Heroku, AWS)

## Installation

### Prerequisites:
- JDK 11 or higher
- MySQL database server
- Maven (for building the project)
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Steps to Run the Application:

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/your-username/movie-booking-app.git
    cd movie-booking-app
    ```

2. **Setup Database**:
   - Create a new MySQL database for the application.
   - Configure your database connection in the `application.properties` file located in `src/main/resources`.

   Example configuration for `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/movie_booking
   spring.datasource.username=root
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true


