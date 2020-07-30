DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products(id bigserial, title VARCHAR(255), cost int, PRIMARY KEY (id));
INSERT INTO products (title, cost) VALUES ('Apple', 100), ('Orange', 70), ('Banana', 150), ('Melon', 300), ('Pear', 60), ('Cherries', 130), ('Plum', 150), ('Raspberries', 90), ('Strawberries', 100), ('Grape', 90), ('Kiwi', 40), ('Papaya', 290), ('Mango', 160), ('Avocado', 135), ('Grapefruit', 105), ('Peach', 68), ('Quince', 202), ('Berries', 35), ('Fig', 115), ('Watermelon', 200);