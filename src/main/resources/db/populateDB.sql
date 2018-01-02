DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name) VALUES
  ('Mamba-Jumba'),
  ('Tokio');

INSERT INTO dishes (date_time, description, price, restaurant_id) VALUES
  ('2015-05-28 00:00:00', 'Завтрак', 500,  100002),
  ('2015-05-29 00:00:00', 'Обед', 1000,  100002),
  ('2015-05-30 00:00:00', 'Ужин', 500,  100002),
  ('2015-05-31 00:00:00', 'Завтрак', 500,  100002),
  ('2015-05-27 00:00:00', 'Обед', 1000,  100002),
  ('2015-05-26 00:00:00', 'Ужин', 510,  100003),
  ('2015-06-01 00:00:00', 'Админ ланч', 510,  100003),
  ('2015-06-02 00:00:00', 'Админ ужин', 1500,  100002);
