-- Create Table untuk people dengan auto increment
CREATE TABLE people (
    id AUTO_INCREMENT PRIMARY KEY,  
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    img VARCHAR(50) NOT NULL,
    web VARCHAR(50) NOT NULL,
    note VARCHAR(50) NOT NULL,
    password VARCHAR(255),
    waktu VARCHAR(50),
    favorite BOOLEAN
);

-- Create Table untuk sampah dengan auto increment
CREATE TABLE sampah (
    id AUTO_INCREMENT PRIMARY KEY,  
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    img VARCHAR(50) NOT NULL,
    web VARCHAR(50) NOT NULL,
    note VARCHAR(50) NOT NULL,
    password VARCHAR(255),
    waktu VARCHAR(50),
    favorite BOOLEAN
);

-- Insert dummy data untuk table people
INSERT INTO people (name, email, img, web, note, password, waktu, favorite) 
VALUES 
    ('John Doe', 
     'john@example.com', 
     'john-profile.jpg', 
     'www.johndoe.com', 
     'Senior Developer', 
     'hashed_password_123', 
     '2024-03-20 10:00:00', 
     true),
    ('Jane Smith', 
     'jane@example.com', 
     'jane-profile.jpg', 
     'www.janesmith.com', 
     'UI/UX Designer', 
     'hashed_password_456', 
     '2024-03-20 11:00:00', 
     false);

-- Insert dummy data untuk table sampah
INSERT INTO sampah (name, email, img, web, note, password, waktu, favorite) 
VALUES 
    ('Deleted User 1', 
     'deleted1@example.com', 
     'deleted1.jpg', 
     'www.deleted1.com', 
     'Inactive Account', 
     'old_password_123', 
     '2024-03-19 15:30:00', 
     false),
    ('Deleted User 2', 
     'deleted2@example.com', 
     'deleted2.jpg', 
     'www.deleted2.com', 
     'Spam Account', 
     'old_password_456', 
     '2024-03-19 16:45:00', 
     true);

-- Perintah untuk melihat data yang telah dimasukkan
-- SELECT * FROM people;
-- SELECT * FROM sampah;
