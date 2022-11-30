create table products (
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8,2)
);

insert into products (title, price) values
('Bread', 32.00),
('Milk', 120.00),
('Butter', 320.00),
('Cheese', 500.00),
('BreadTest', 400.00),
('TestBread', 400.00),
('Bread1', 32.00),
('Milk1', 120.00),
('Butter1', 320.00),
('Cheese1', 500.00),
('BreadTest1', 400.00),
('TestBread1', 400.00),
('Bread2', 32.00),
('Milk2', 120.00),
('Butter2', 320.00),
('Cheese2', 500.00),
('BreadTest2', 400.00),
('TestBread2', 400.00);

create table orders
(
    id bigserial primary key,
    username varchar(255) not null,
    total_price numeric(8,2) not null,
    address varchar(255),
    phone varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items
(
    id bigserial primary key,
    product_id bigint not null references products (id),
    order_id bigint not null references orders (id),
    quantity int not null,
    price_per_product int not null,
    price numeric(8,2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
