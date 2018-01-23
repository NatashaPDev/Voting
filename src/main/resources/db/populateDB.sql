DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM users;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name) VALUES
  ('Burger King'),
  ('Tokio');

INSERT INTO dishes (date, description, price, restaurant_id) VALUES
  ('2018-01-01', 'Chicken', 500,  100002),
  ('2018-01-01', 'Salad', 1000,  100002),
  ('2018-01-01', 'Fried potatoes', 500,  100002),
  ('2018-01-01', 'Juice', 300,  100002),
  ('2018-01-01', 'Noodles', 510,  100003),
  ('2018-01-01', 'Salad', 1000,  100003),
  ('2018-01-01', 'Tea', 200,  100003),
  ('2018-01-02', 'Burger', 500,  100002),
  ('2018-01-02', 'Salad', 1000,  100002),
  ('2018-01-02', 'Juice', 300,  100002),
  ('2018-01-02', 'Sushi', 510,  100003),
  ('2018-01-02', 'Salad', 1000,  100003),
  ('2018-01-02', 'Tea', 200,  100003);

INSERT INTO votes (date, time, restaurant_id, user_id) VALUES
  ('2018-01-01', '10:00:00', 100002, 100000),
  ('2018-01-01', '10:00:00', 100002, 100001),
  ('2018-01-02', '10:00:00', 100002, 100000),
  ('2018-01-02', '10:00:00', 100003, 100001);
