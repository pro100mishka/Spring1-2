DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id bigserial,
    title varchar(255),
    cost decimal ,
    PRIMARY KEY (id));

INSERT INTO product (title, cost) VALUES
('Cheese', 320),
('Milk', 90),
('Apples', 120);

