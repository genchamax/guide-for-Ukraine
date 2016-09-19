CREATE DATABASE IF NOT EXISTS guide;

USE guide;

CREATE TABLE category (
  id            INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(50) NOT NULL
);

CREATE TABLE region (
  id          INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  region_name VARCHAR(50)        NOT NULL
);

CREATE TABLE city (
  id               INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  city_description TEXT               NULL,
  city_name        VARCHAR(50)        NOT NULL,
  region_id        INT                NOT NULL,
  CONSTRAINT fk_city_region FOREIGN KEY (region_id) REFERENCES region (id)
);


CREATE TABLE place (
  id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  place_name VARCHAR(100) NOT NULL,
  city_id    INT          NOT NULL,
  CONSTRAINT fk_place_city FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE `role` (
  id        INT         NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  role_name VARCHAR(30) NOT NULL
);

CREATE TABLE `user` (
  id              INT          NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  user_password   CHAR(56)     NOT NULL,
  user_login      VARCHAR(100) NOT NULL,
  email           VARCHAR(100) NULL,
  user_name       VARCHAR(100) NOT NULL,
  user_surname    VARCHAR(100) NULL,
  user_patronymic VARCHAR(100) NULL,
  user_birth      DATE         NULL,
  enabled         BOOLEAN      NOT NULL DEFAULT FALSE ,
  role_id         INT          NOT NULL,
  CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES `role` (id)
);

CREATE TABLE post (
  id           INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  post_name    VARCHAR(50)        NOT NULL,
  post_body    TEXT               NOT NULL,
  place_id     INT                NOT NULL,
  publish_date DATE               NOT NULL,
  user_id      INT                NOT NULL,
  category_id  INT                NOT NULL,
  CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES `user` (id),
  CONSTRAINT fk_post_place FOREIGN KEY (place_id) REFERENCES place (id),
  CONSTRAINT fk_post_category FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE `comment` (
  id           INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  comment_body TEXT NOT NULL,
  user_id      INT  NOT NULL,
  comment_date DATE NOT NULL,
  post_id      INT  NOT NULL,
  CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES `user` (id),
  CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES post (id)
);

#ManyToMany User and Favourite post
CREATE TABLE USER_FAVOURITE_POST (
  id      INT PRIMARY KEY NOT NULL,
  user_id INT             NOT NULL,
  post_id INT             NOT NULL,
  FOREIGN KEY (user_id) REFERENCES `user` (id),
  FOREIGN KEY (post_id) REFERENCES post (id)
);

INSERT INTO region (region_name) VALUES
  ("Вінницька область"),
  ("Волинська область"),
  ("Дніпропетровська область"),
  ("Донецька область"),
  ("Житомирська область"),
  ("Закарпатська область"),
  ("Запорізька область"),
  ("Івано-Франківська область"),
  ("Київська область"),
  ("Кіровоградська область"),
  ("Луганська область"),
  ("Львівська область"),
  ("Миколаївська область"),
  ("Одеська область"),
  ("Полтавська область"),
  ("Рівненська область"),
  ("Сумська область"),
  ("Тернопільська область"),
  ("Харківська область"),
  ("Херсонська область"),
  ("Хмельницька область"),
  ("Черкаська область"),
  ("Чернівецька область"),
  ("Чернігівська область"),
  ("АР Крим");


INSERT INTO city (city_description, city_name, region_id) VALUES
  ("SOME CITY", "afaf", 2),
  ("SOME CITdsfY", "afadfdsf", 3),
  ("SOME CdfsfITY", "879", 2),
  ("SOME CqqqqqqITY", "3re3", 2);

INSERT INTO `role` (role_name) VALUES
  ("ADMIN"),
  ("USER");

INSERT INTO `user` (user_password, user_login, email, user_name, user_surname, user_patronymic, user_birth, role_id)
VALUES
  ("loloco97", "vikaafanaseva", "vikalorax@mail.ru", "Вікторія", "Афанасьєва", "Віталіївна", "1997-07-10", 1),
  ("password", "somelogin", "test@test.com", "Іван", "Іванов", "Іванович", "1987-05-3", 2);