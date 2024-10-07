# Password Manager Application

A desktop application built with JavaFX and PHP backend for managing passwords with features including password history, favorites, and search functionality. This is second
version of passmanager-javafx, more responsive, more feature, modern UI, and can be accesed any PC's because this app using API to store it's data

## 🚀 Features

- CRUD operations for password management
- Password encryption using Vigenère cipher
- Trash/deletion history
- Favorite passwords marking
- Search functionality
- Real-time password editing
- Custom styling with CSS
- Responsive design
- RESTful API integration

## 🏗️ Project Structure

```
password-manager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── id.dojo.account/
│   │   │       ├── handler/
│   │   │       │   ├── Handler.java
│   │   │       │   ├── Request.java
│   │   │       │   └── VigenereCipher.java
│   │   │       ├── appmodel.java
│   │   │       ├── HelloApplication.java
│   │   │       ├── ItemController.java
│   │   │       ├── NewController.java
│   │   │       └── Response.java
│   │   └── resources/
│   │       ├── backend/
│   │       │   └── sampah/
│   │       │       └── index.php
│   │       ├── icons/
│   │       └── id.dojo.account/
│   │           ├── component.fxml
│   │           ├── hello-view.fxml
│   │           └── style.css
└── module-info.java
```

## 🔧 Prerequisites

- Java Development Kit (JDK) 22 or higher
- JavaFX SDK 21.0.4 or higher
- Maven Dependency Management
- PHP 7.4 or higher
- Web server (Apache/Nginx) for PHP backend
- MySQL/MariaDB/Postgresql (Recomended, i use Postgree) database

## ⚙️ Installation & Setup

1. Clone the repository:
```bash
git clone https://github.com/Zachry2906/passmanager-javafx-v2.0.git
```

2. Install JavaFX SDK:
```bash
# Example for Ubuntu/Debian
sudo apt-get install openjfx
```
or you can download manually [at ](https://gluonhq.com/products/javafx/)

3. Set up the PHP backend:
   - Deploy the PHP files to your web server
   - Configure your database settings in the backend configuration file

4. Run the application:
5. you can run manually from your IDE without compiling and install, but you have to config javafx on your IDE or you can follow step bellow
```bash
java -jar --module-path /path/to/javafx-sdk-22.0.4/lib --add-modules javafx.controls,javafx.fxml /path/to/account-1.0-SNAPSHOT.jar
```

Note: Replace `/path/to/` with your actual JavaFX SDK and application JAR paths.

## 🔌 Backend API

The application communicates with a PHP backend that returns JSON responses. The API endpoints handle:

- Password CRUD operations
- Favorite marking
- Trash management
- Search functionality

## 🎨 Styling

The application uses custom CSS for styling, located in `resources/id.dojo.account/style.css`. The UI is designed to be modern and user-friendly.

## 🔐 Security Features

- Password encryption using Vigenère cipher
- Secure communication with backend API
- Protection against unauthorized access

## 🤝 Contributing

1. Fork the repository
2. Create a new branch
3. Make your changes
4. Submit a pull request


## 👥 Authors

- Ahmad Zakaria

## 🙏 Acknowledgments

- JavaFX community
