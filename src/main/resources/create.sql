create table client
(
	email varchar(20) not null,
	phone varchar(20) not null,
	name varchar(20) not null,
	sex varchar(10) not null,
	birth date not null,
	constraint client_pk
		primary key (email)
);

create table goods
(
	goods_id bigint auto_increment,
	goods_name varchar(30) not null,
	inventory int not null,
	min_inventory int not null,
	price float not null,
	constraint goods_pk
		primary key (goods_id)
);

create table user
(
	id bigint auto_increment,
	password varchar(30) not null,
	position varchar(20) not null,

	email varchar(20) not null,
	phone varchar(20) not null,
	name varchar(20) not null,
	sex varchar(10) not null,
	birth date not null,
	constraint user_pk
		primary key (id)
);

create table `order`
(
	id bigint auto_increment,
	date date not null,
	address varchar(40) not null,
	state varchar(15) not null,
	sum float not null,

	client_email varchar(20) not null,
	salesman_id bigint not null,
    goods_list json not null,
	constraint order_pk
		primary key (id)
);

create table dispatch_order
(
	id bigint auto_increment,
	date date not null,
	address varchar(40) not null,
	state varchar(15) not null,
    sum float not null,

    goods_id bigint not null,
	number int not null,
	order_id bigint not null,
	sales_keeper_id bigint null,
    logistics_id bigint null ,
	warehouse_keeper_id bigint null,
    client_purchase_order_id bigint not null,
	constraint dispatch_order_pk
		primary key (id)
);

create table purchase_order
(
	id bigint auto_increment,
	date date not null,
	address varchar(40) not null,
	state varchar(15) not null,

	goods_id bigint not null,
	number int null,
    logistics_id bigint null ,
    warehouse_keeper_id_confirm bigint null,
    warehouse_keeper_id_close bigint null,
    constraint purchase_order_pk
		primary key (id)
);

create table client_purchase_order
(
    id bigint auto_increment,
    date date not null,
    address varchar(40) not null,
    state varchar(15) not null,
    sum float not null,

    goods_list json not null,
    order_id bigint not null,
    constraint purchase_order_pk
        primary key (id)
);