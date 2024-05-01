CREATE TABLE "user" (
    email VARCHAR (255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    authority VARCHAR(255) NOT NULL
);

INSERT INTO "user" (email, password, authority)
VALUES ('user@mail.com', '{bcrypt}$2a$10$Dj3yQt1rG/CnCDg25gu/5.ni3qoqMrJhVZUTc2S/Jf8V40IJNuM/2', 'USER');

