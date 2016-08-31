DELETE FROM votes;
DELETE FROM menu;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- user
INSERT INTO users (name, email, password, role) VALUES
  ('Bill', 'bill@microsoft.com', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', 'ROLE_USER'), -- 100000
  ('Jeff', 'jeff@amazon.com', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', 'ROLE_USER'), -- 100001
  ('Larry', 'larry@oracle.com', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', 'ROLE_USER'); -- 100002

-- admin
INSERT INTO users (name, email, password, role)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju', 'ROLE_ADMIN');    -- 100003

INSERT INTO restaurants (name, description, address) VALUES
  ('Эль Гаучо', 'Стейк-хаус, Морепродукты, Аргентинская, Южноамериканская', 'Большой Козловский переулок, д.3, корп. 2, Москва 107078, Россия'), -- 100004
  ('Хачапури', 'Восточноевропейская', 'Украинский бульвар, д. 7, Москва 121059, Россия'),                                                        -- 100005
  ('Стейк Хаус Бизон', 'Американская, Стейк-хаус, Барбекю, Европейская, Центральноевропейская', 'Профсоюзная, 66, Москва 117393, Россия'),       -- 100006
  ('Обломов', 'Русская, Восточноевропейская, Европейская, Центральноевропейская', '1-й Монетчиковский пер., д. 5., Москва 115054, Россия');      -- 100007

INSERT INTO menu (restaurant_id, date, dish, price) VALUES
  (100004, '2016-08-21', 'Севиче из тунца', 750),                   -- 100008
  (100004, '2016-08-21', 'Сырное ассорти', 990),                    -- 100009
  (100004, '2016-08-20', 'Бефстроганов', 990),                      -- 100010
  (100004, '2016-08-20', 'Помидоры с красным луком', 650),          -- 100011
  (100005, '2016-08-21', 'Курица Чкмерули в чесночном соусе', 490), -- 100012
  (100005, '2016-08-21', 'Суп из мацони с зеленью и яйцом', 230),   -- 100013
  (100005, '2016-08-20', 'Крылья индейки в сладком ткемали', 490),  -- 100014
  (100005, '2016-08-20', 'Чахохбили со сладким перцем', 430);       -- 100015

INSERT INTO votes (user_id, restaurant_id, date) VALUES
  (100000, 100004, '2016-08-21'),  -- 100016
  (100001, 100004, '2016-08-21'),  -- 100017
  (100002, 100005, '2016-08-21'),  -- 100018
  (100000, 100004, '2016-08-20'),  -- 100019
  (100001, 100005, '2016-08-20'),  -- 100020
  (100002, 100005, '2016-08-20');  -- 100021
