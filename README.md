```markdown
# SCOR - Smart Content Organizer

**SCOR** (Smart Content Organizer) is a web application designed to help users manage and organize all their notes, social content, and (soon) passwords, all in one place. 
Users can create, update, delete, search, and share content across different sections (referred to as "scors") for easy and efficient management.

## ✨ Features

- **Create, Update, Delete Scors**: Organize content into different categories (e.g., physics, job, etc.)
- **Search**: Quickly find notes or content from any section
- **Share**: Easily share specific scors or content with others
- **Organize Social Content**: Attach and manage social media links within specific scors
- **Future Feature**: Manage passwords in a secure subsection

## 🛠 Tech Stack

- **Frontend:** HTML, CSS, JavaScript, Thymeleaf, Tailwind CSS
- **Backend:** Spring Boot (Java)
- **Database:** PostgreSQL

## 🚀 Project Structure

```bash
SCOR/
├── frontend/                # HTML, CSS, JavaScript files for UI
├── backend/                 # Spring Boot Application
│   ├── src/
│   └── resources/
├── db/                      # Database scripts and schemas
└── README.md                # Project documentation
```

## 🏁 Getting Started

### Prerequisites

- **Java 17** or above
- **Node.js** (for frontend dependencies)
- **PostgreSQL** for the database

### Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/pr4jwal-19/SCOR.git
   cd SCOR
   ```

2. **Backend (Spring Boot):**

    - Set up the PostgreSQL database with the required schema.
    - Update the `application.properties` with your database credentials.

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/scor_db
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   ```

    - Run the Spring Boot application:

   ```bash
   ./mvnw spring-boot:run
   ```

3. **Frontend:**

    - Navigate to the `frontend` folder:

      ```bash
      cd frontend
      ```

    - Install dependencies and start the frontend server:

      ```bash
      npm install
      npm start
      ```

## 📂 API Endpoints

| Endpoint                     | Method | Description                          |
| ----------------------------- | ------ | ------------------------------------ |
| `/api/scors`                  | GET    | Get all scors (sections)             |
| `/api/scors/{id}`             | GET    | Get specific scor by ID              |
| `/api/scors`                  | POST   | Add new scor                         |
| `/api/scors/{id}`             | PUT    | Update scor by ID                    |
| `/api/scors/{id}`             | DELETE | Delete scor by ID                    |
| `/api/search?query={term}`    | GET    | Search scors or content              |

## 🔐 Security

- User authentication and authorization via Spring Security
- Future password management feature with encryption for storing user passwords

## 🧑‍💻 Contributing

We welcome contributions to SCOR! To contribute:

1. Fork the repository
2. Create a new branch: `git checkout -b feature-name`
3. Make your changes and commit: `git commit -m 'Add feature'`
4. Push to the branch: `git push origin feature-name`
5. Open a pull request

## 💬 Contact

- **Project Maintainer:** Prajwal Nakure - [Email](mailto:prajwal.dvl.2025.19@gmail.com)
- **GitHub:** [Github](https://github.com/pr4jwal-19)
```
