DROP TABLE IF EXISTS category;
CREATE TABLE category(
    id bigserial PRIMARY KEY,
    title varchar (255)
);

INSERT INTO category (title) VALUES
('science'),
('milk');

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id bigserial,
    title varchar(255),
    cost decimal,
    category_id integer REFERENCES category(id),
    PRIMARY KEY (id)
);

INSERT INTO product (title, cost, category_id) VALUES
('Cheese', 320, 1),
('Milk', 90, 1),
('Apples', 120, 1);

