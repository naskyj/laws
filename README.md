# Legal Laws API

A Spring Boot REST API application for managing and accessing legal documents with bilingual support (English and Yoruba). The application provides a comprehensive legal database with automatic translation capabilities using Microsoft Translator API.

## 🚀 Features

- **Legal Document Management**: Store and manage laws, chapters, sections, and subsections
- **Bilingual Support**: Automatic translation from English to Yoruba using Microsoft Translator API
- **Search Functionality**: Search laws in both English and Yoruba languages
- **Pagination**: Efficient data retrieval with pagination support
- **Common Laws Filter**: Special category for frequently accessed laws
- **RESTful API**: Clean and well-structured REST endpoints
- **Database Integration**: MySQL database with JPA/Hibernate ORM

## 🏗️ Architecture

### Data Model
The application follows a hierarchical structure:
```
Law
├── Chapter
    ├── Section
        └── Subsection
```

### Key Components
- **Models**: `Law`, `Chapter`, `Section`, `Subsection`, `Translation`
- **Controllers**: `LawsController`, `TranslateContainer`, `IndexController`
- **Services**: `LawService`, `ChapterService`, `SectionService`, `SubsectionService`
- **Utilities**: `TranslateUtil`, `UpdateTranslationUtil`

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.2
- **Java Version**: 17
- **Database**: MySQL
- **ORM**: Spring Data JPA with Hibernate
- **Translation API**: Microsoft Translator API
- **HTTP Client**: Unirest Java
- **Build Tool**: Gradle
- **Lombok**: For reducing boilerplate code

## 📋 Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Gradle 7.0 or higher
- Microsoft Translator API key

## ⚙️ Configuration

### Database Setup
1. Create a MySQL database named `laws_db`
2. Copy `application-example.properties` to `application.properties`
3. Update the database connection details in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/laws_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Translation API Setup
1. Get a Microsoft Translator API key from [Azure Cognitive Services](https://azure.microsoft.com/en-us/services/cognitive-services/translator/)
2. Update the configuration in `application.properties`:
```properties
translate.key=your_microsoft_translator_key
transate.url=https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=yo
```

### Environment Variables (Recommended for Production)
For production deployment, use environment variables instead of hardcoded values:
```bash
export DB_URL="your_database_url"
export DB_USERNAME="your_database_username"
export DB_PASSWORD="your_database_password"
export TRANSLATE_API_KEY="your_translator_api_key"
```

## 🚀 Running the Application

1. **Clone the repository**:
```bash
git clone <repository-url>
cd laws-api
```

2. **Build the project**:
```bash
./gradlew build
```

3. **Run the application**:
```bash
./gradlew bootRun
```

The application will start on port `9010` (configurable in `application.properties`).

## 📚 API Endpoints

### Laws Management
- `POST /api/v1/laws` - Create a new law
- `GET /api/v1/laws/find?id={id}` - Get law by ID
- `GET /api/v1/laws/all?page={page}&size={size}` - Get all laws (paginated)
- `GET /api/v1/laws/common?page={page}&size={size}` - Get common laws (paginated)
- `GET /api/v1/laws/search?q={query}&lang={language}&page={page}&size={size}` - Search laws

### Translation
- `GET /api/v1/translate?q={text}` - Translate text from English to Yoruba

### General
- `GET /` - Home page

### Request/Response Examples

#### Create a Law
```bash
POST /api/v1/laws
Content-Type: application/json

{
    "title": "Traffic Law",
    "description": "Regulations governing traffic in the jurisdiction",
    "dateEnacted": "2023-01-15",
    "common": true
}
```

#### Search Laws
```bash
GET /api/v1/laws/search?q=traffic&lang=EN&page=0&size=10
```

#### Translate Text
```bash
GET /api/v1/translate?q=Hello World
```

## 🔧 Key Features Explained

### Automatic Translation
When a new law is created, the system automatically:
1. Translates the title and description to Yoruba
2. Updates the law record with translated content
3. Marks the law as translated
4. Handles translation failures gracefully

### Search Capabilities
- **Language-specific search**: Search in English (`EN`) or Yoruba (`YR`)
- **Case-insensitive**: Searches are not case-sensitive
- **Partial matching**: Supports partial text matching
- **Pagination**: Results are paginated for performance

### Data Relationships
- **One-to-Many**: Law → Chapters → Sections → Subsections
- **Eager Loading**: Related entities are loaded efficiently
- **JSON Serialization**: Proper handling of circular references

## 🗄️ Database Schema

The application uses the following main entities:
- **Law**: Main legal document with title, description, and metadata
- **Chapter**: Subdivision of a law
- **Section**: Subdivision of a chapter
- **Subsection**: Subdivision of a section
- **Translation**: Stores translation metadata

## 🚦 CORS Configuration

The application is configured to accept requests from `http://localhost:3000` for frontend integration.

## 📝 Response Format

All API responses follow a consistent format:
```json
{
    "responseCode": "00",
    "responseMessage": "Success",
    "data": { ... }
}
```

## 🔒 Security Features

- **Environment Variables**: Support for environment variable configuration
- **SSL Enabled**: Database connections use SSL by default
- **CORS Security**: Restricted CORS headers for better security
- **No Hardcoded Secrets**: All sensitive data uses placeholder values
- **Example Configuration**: Separate example file for easy setup

**Important**: Never commit actual credentials to version control. Use environment variables or secure configuration management for production deployments.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is for demonstration purposes and is part of a portfolio showcase.

## 🎯 Future Enhancements

- User authentication and authorization
- Advanced search with filters
- Document upload and management
- API rate limiting
- Caching for improved performance
- Swagger/OpenAPI documentation
- Unit and integration tests
