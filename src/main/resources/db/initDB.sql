DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name          VARCHAR NOT NULL,
  email         VARCHAR NOT NULL,
  password      VARCHAR NOT NULL,
  role          VARCHAR NOT NULL,
  registered    TIMESTAMP DEFAULT now(),
  enabled       BOOL DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE restaurants
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name          VARCHAR NOT NULL,
  description   VARCHAR NOT NULL,
  address       VARCHAR NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_address_idx ON restaurants (name, address);

CREATE TABLE menu
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER NOT NULL,
  date          DATE NOT NULL,
  dish          VARCHAR NOT NULL,
  price         NUMERIC(20,2) NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_unique_restaurant_date_dish_idx ON menu (restaurant_id, date, dish);

CREATE TABLE votes (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  date          DATE NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_user_date_idx ON votes(user_id, date);
CREATE INDEX votes_restaurant_date_idx ON votes(restaurant_id, date);
