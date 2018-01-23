DROP TABLE user_roles
IF EXISTS;
DROP TABLE dishes
IF EXISTS;
DROP TABLE votes
IF EXISTS;
DROP TABLE restaurants
IF EXISTS;
DROP TABLE users
IF EXISTS;

DROP SEQUENCE global_seq
IF EXISTS;

CREATE SEQUENCE IF NOT EXISTS GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE IF NOT EXISTS users
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL,
  email            VARCHAR(255)            NOT NULL,
  password         VARCHAR(255)            NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS users_unique_email_idx ON USERS (email);

CREATE TABLE IF NOT EXISTS user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS restaurants
(
  id      INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS dishes
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  date          DATE         NOT NULL,
  description   VARCHAR(255) NOT NULL,
  price         INT          NOT NULL,
  restaurant_id INTEGER      NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS dishes_restaurant_date_idx ON dishes (restaurant_id, date);
CREATE INDEX IF NOT EXISTS dishes_date_idx ON dishes (date);

CREATE TABLE IF NOT EXISTS votes
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  date          DATE    NOT NULL,
  time          TIME    NOT NULL,
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE,
  user_id       INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX IF NOT EXISTS votes_unique_user_date_idx
  ON votes (user_id, date)


