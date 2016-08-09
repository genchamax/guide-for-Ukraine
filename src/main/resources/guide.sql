CREATE DATABASE guide;

USE guide;

CREATE TABLE image (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  image_title VARCHAR(255) NULL,
  image_url   TEXT         NOT NULL
);

CREATE TABLE category (
  id            INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(50) NOT NULL
);

CREATE TABLE region (
  id          INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  region_name VARCHAR(50)        NOT NULL,
  image_id    INT                NULL,
  CONSTRAINT fk_region_image FOREIGN KEY (image_id) REFERENCES image (id)
  --  PRIMARY KEY (id)
);

CREATE TABLE city (
  id               INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  city_description TEXT               NULL,
  city_name        VARCHAR(50)        NOT NULL,
  region_id        INT                NOT NULL,
  image_id         INT                NULL,
  CONSTRAINT fk_city_image FOREIGN KEY (image_id) REFERENCES image (id),
  CONSTRAINT fk_city_region FOREIGN KEY (region_id) REFERENCES region (id)
);


CREATE TABLE place (
  id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  place_name VARCHAR(100) NOT NULL,
  city_id    INT          NOT NULL,
  image_id   INT          NULL,
  CONSTRAINT fk_place_image FOREIGN KEY (image_id) REFERENCES image (id),
  CONSTRAINT fk_place_city FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE `user` (
  id                 INT          NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  user_password      CHAR(56)     NOT NULL,
  user_login         VARCHAR(100) NOT NULL,
  email              VARCHAR(100) NULL,
  user_name          VARCHAR(100) NOT NULL,
  user_surname       VARCHAR(100) NULL,
  user_patronymic    VARCHAR(100) NULL,
  user_birth         DATE         NULL,
  user_status        VARCHAR(20)  NOT NULL,
  favourite_posts_id INT          NULL
  --    CONSTRAINT fk_user_favourite_posts FOREIGN KEY (favourite_posts_id) REFERENCES post(id)
);


CREATE TABLE post /* Check grammar */ (
  id           INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  post_name    VARCHAR(50)        NOT NULL,
  post_body  TEXT               NOT NULL,
  place_id     INT                NOT NULL,
  publish_date DATE               NOT NULL,
  image_id     INT                NULL,
  user_id      INT                NOT NULL,
  category_id  INT                NOT NULL,
  CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES `user` (id),
  CONSTRAINT fk_post_image FOREIGN KEY (image_id) REFERENCES image (id),
  CONSTRAINT fk_post_place FOREIGN KEY (place_id) REFERENCES place (id),
  CONSTRAINT fk_post_category FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE `comment` (
  id           INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  comment_body TEXT NOT NULL,
  user_id      INT  NOT NULL,
  comment_date DATE NOT NULL,
  image_id     INT  NULL,
  post_id      INT  NOT NULL,
  CONSTRAINT fk_comment_images FOREIGN KEY (image_id) REFERENCES image (id),
  CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES `user` (id),
  CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES post (id)
);

ALTER TABLE `user`
  ADD CONSTRAINT fk_user_favourite_posts FOREIGN KEY (favourite_posts_id) REFERENCES post (id);