CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       nickname VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE posts (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(100) NOT NULL,
                       content TEXT NOT NULL,
                       author_id INT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE comments (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          content TEXT NOT NULL,
                          post_id INT NOT NULL,
                          author_id INT NOT NULL,
                          parent_id INT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,
                          FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (parent_id) REFERENCES comments(id) ON DELETE CASCADE
);