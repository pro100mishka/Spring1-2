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

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id                    bigserial,
  phone                 VARCHAR(30) NOT NULL UNIQUE,
  password              VARCHAR(80),
  email                 VARCHAR(50) UNIQUE,
  first_name            VARCHAR(50),
  last_name             VARCHAR(50),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id                    serial,
  name                  VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles (
  user_id               INT NOT NULL,
  role_id               INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id)
  REFERENCES users (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO users (phone, password, first_name, last_name, email)
VALUES
('11111111','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Admin','Admin','admin@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3);

DROP TABLE IF EXISTS address;
CREATE TABLE address (
    id bigserial,
    phone varchar(255),
    city varchar(255),
    state varchar(255),
    zip varchar(255),
    street varchar(255),
    home varchar(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS order_table cascade;
CREATE TABLE order_table(
    id bigserial,
    user_id integer,
    address_id integer,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES address(id)
);


DROP TABLE IF EXISTS order_Item cascade;
CREATE TABLE order_Item (
    id bigserial,
    product_id integer,
    count integer,
    price decimal,
    total_price decimal,
    order_id integer,
    PRIMARY KEY (id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES order_table(id)
);