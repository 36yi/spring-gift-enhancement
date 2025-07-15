create table member
(
    id       bigint       not null auto_increment,
    email    varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

create table product
(
    price     integer      not null,
    id        bigint       not null auto_increment,
    name      varchar(15)  not null,
    image_url varchar(255) not null,
    primary key (id)
);

create table wish
(
    id         bigint not null auto_increment,
    member_id  bigint not null,
    product_id bigint not null,
    primary key (id)
);

alter table member
    add constraint uk_member unique (email);

alter table wish
    add constraint fk_wish_member_id_ref_member_id
        foreign key (member_id)
            references member (id);

alter table wish
    add constraint fk_wish_product_id_ref_product_id
        foreign key (product_id)
            references product (id);