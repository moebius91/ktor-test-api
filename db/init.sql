CREATE DATABASE IF NOT EXISTS jno_testapi;
USE jno_testapi;

CREATE TABLE rezepte (
    rezept_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    beschreibung TEXT,
    erstellungsdatum DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
