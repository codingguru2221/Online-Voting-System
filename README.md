# 🗳️ Online Voting System (Spring Boot + MySQL)

An **industry-level Online Voting System** built with **Java, Spring Boot, MySQL, JWT, and Spring Security**.
This project is designed for **secure, transparent, and one-time voting**, with **role-based access** for Voters, Admins, and Candidates.

---

## ⚡ Tech Stack

* **Backend:** Java 17+, Spring Boot
* **Database:** MySQL (InnoDB)
* **ORM:** Spring Data JPA (Hibernate)
* **Security:** Spring Security + JWT + BCrypt
* **Build Tool:** Maven
* **Testing:** JUnit + Mockito
* **Deployment Ready:** Docker + AWS (optional)

---

## 📂 Project Structure

```
online-voting-system/
│── src/main/java/com/codex/voting/
│   ├── config/          # Security + JWT config
│   ├── controller/      # REST APIs
│   ├── dto/             # Request/Response models
│   ├── entity/          # JPA Entities (Voter, Candidate, Election, Admin)
│   ├── repository/      # JPA Repositories
│   ├── security/        # JWT filters + Auth classes
│   ├── service/         # Business logic
│   └── OnlineVotingSystemApplication.java
│
│── src/main/resources/
│   ├── application.yml  # MySQL + JWT + server configs
│   └── schema.sql       # DB initialization (optional)
│
└── pom.xml              # Dependencies (Spring Boot + MySQL + Security + JWT)
```

---

## 📊 Database Design

### `voters`

\| id | voter\_id | name | email | password | dob | legal\_document | has\_voted |

### `candidates`

\| id | mark\_name (unique) | description | vote\_count |

### `admins`

\| id | email | password | created\_at |

### `elections`

\| id | title | start\_time | end\_time | is\_active |

---

## 🔑 Code Flow

* **Voter Signup**

  * `/api/auth/register` → validates age (≥18) + legal document, assigns `UUID` as voterId.
* **Login**

  * `/api/auth/login` → JWT-based authentication (roles: VOTER / ADMIN).
* **Admin**

  * Created at first startup (in `admins` table).
  * Only Admin can create new Admins & Candidates.
* **Candidate Creation**

  * `/api/admin/candidate` → creates candidate with unique `markName`.
* **Voting**

  * `/api/voter/vote/{candidateId}` → marks `has_voted=true` & increments candidate’s votes.
* **Results**

  * `/api/results` → shows results (votes per candidate).

---

## ⚙️ pom.xml (Dependencies)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.codex</groupId>
    <artifactId>online-voting-system</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>Online Voting System</name>
    <description>Spring Boot Online Voting System with MySQL</description>

    <properties>
        <java.version>17</java.version>
        <spring-boot.version>3.2.3</spring-boot.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <!-- MySQL Driver -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## 🚀 Running the Project

1. Clone the repo

   ```sh
   git clone https://github.com/yourusername/online-voting-system.git
   cd online-voting-system
   ```
2. Configure **MySQL** in `application.yml`

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/votingdb
       username: root
       password: yourpassword
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   jwt:
     secret: your_jwt_secret
     expiration: 3600000
   ```
3. Run the app

   ```sh
   mvn spring-boot:run
   ```
4. Access APIs at → `http://localhost:8080/api/...`
