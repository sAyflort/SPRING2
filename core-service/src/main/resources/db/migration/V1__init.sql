create table products (
    id          bigserial primary key,
    title       varchar(255),
    price       int
);

insert into products (title, price) values
('Bread', 32),
('Milk', 120),
('Butter', 320),
('Cheese', 500),
('BreadTest', 400),
('TestBread', 400);

create table orders
(
    id bigserial primary key,
    username varchar(255) not null,
    total_price int not null,
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
    price int not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
