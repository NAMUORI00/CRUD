-- users 테이블 생성
CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       nickname VARCHAR(50) NOT NULL,
                       role VARCHAR(50) NOT NULL DEFAULT 'user',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- users 테이블에 더미데이터 삽입
INSERT INTO users (username, password, email, nickname, role)
VALUES ('john', 'password1', 'john@example.com', 'John', 'admin'),
       ('jane', 'password2', 'jane@example.com', 'Jane', 'user'),
       ('mark', 'password3', 'mark@example.com', 'Mark', 'user');

-- posts 테이블 생성
CREATE TABLE posts (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(100) NOT NULL,
                       content TEXT NOT NULL,
                       author_id INT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);

-- posts 테이블에 더미데이터 삽입
INSERT INTO posts (title, content, author_id)
VALUES ('Post 1', 'This is the content of Post 1', 1),
       ('Post 2', 'This is the content of Post 2', 2),
       ('Post 3', 'This is the content of Post 3', 3);

-- comments 테이블 생성
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

-- comments 테이블에 더미데이터 삽입
INSERT INTO comments (content, post_id, author_id, parent_id)
VALUES ('This is a comment on Post 1', 1, 2, NULL),
       ('This is a comment on Post 1', 1, 3, NULL),
       ('This is a reply to the first comment on Post 1', 1, 1, 1),
       ('This is a comment on Post 2', 2, 1, NULL),
       ('This is a comment on Post 3', 3, 2, NULL);