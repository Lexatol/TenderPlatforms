create table IF NOT EXISTS status (
        status_id           bigserial primary key,
        title               varchar(50)
);

insert into status (title) values
    ('DRAFT'),
    ('ANNOUNCED'),
    ('WAIT_FOR_CONFIRMATION'),
    ('CONFIRMED'),
    ('COMPLETED'),
    ('DELETED'),
    ('SENT'),
    ('ACCEPTED'),
    ('REJECTED');


create table IF NOT EXISTS tenders(
        id           bigserial primary key,
        title               varchar(100) not null,
        data_start          varchar(20),
        date_finish         varchar(20),
        address             varchar(100),
        description         varchar(1000),
        contractor_id       UUID,
        customer_id         UUID not null,
        min_price           numeric(8, 1) not null,
        max_price           numeric(8, 1) not null,
        status_id           bigint references status(status_id),
        specialization_id   bigint,
        created_at          timestamp default current_timestamp,
        announce_date       timestamp default current_timestamp
);


create table if not exists responds(
        id                  bigserial primary key,
        client_id           UUID,
        tender_id           bigint references tenders (id),
        description         varchar(1000),
        price               numeric(8, 1) not null,
        term                varchar(100),
        status_id           bigint references status(status_id)
);

--
insert into tenders (title, customer_id, contractor_id, min_price, max_price, status_id, address, specialization_id) values
('Строительство коттеджа 600м2', '694836c0-b2d0-4bbe-83dc-98c5beef1d5a', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0', 2000000.0,2500000.0, 2, 'Московская область, Новорижское шоссе, снт Итренок', 43),
('Строительство коттеджа 400м2', '694836c0-b2d0-4bbe-83dc-98c5beef1d5a', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0', 1500000.0, 2000000.0, 3, 'Московская область, Калужское шоссе', 43),
('Строительство коттеджа 200м2', '694836c0-b2d0-4bbe-83dc-98c5beef1d5a', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0', 1000000.0, 1500000.0, 2, 'Московская область, Киевское шоссе', 43),
('Строительство коттеджа 120м2', '694836c0-b2d0-4bbe-83dc-98c5beef1d5a',null, 800000.0,1200000.0, 3, 'Московская область, Киевское шоссе', 43),
('Ремонт квартиры 250м2', '694836c0-b2d0-4bbe-83dc-98c5beef1d5a', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0', 800000.0,900000.0, 2, 'Москва, Малая ордынка, 19', 42),
('Ремонт квартиры 140м2', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0',null, 250000.0,450000.0, 3, 'Москва, Старый арбат, 56', 42),
('Ремонт квартиры 290м2', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0',null, 500000.0, 600000.0, 3, 'Москва, Мосфильмовская, 35', 42),
('Ремонт квартиры 340м2', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0',null, 550000.0,850000.0, 2, 'Москва, Краснопролетарская, 7', 42),
('Ремонт квартиры 450м2', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0',null, 700000.0, 980000.0, 3, 'Москва, Месницкая, 11', 42),
('Дизайнерский ремонт квартиры 350м2', 'e4e839c5-4f9b-45d2-8e06-c3e598406cf0','694836c0-b2d0-4bbe-83dc-98c5beef1d5a', 1100000.0, 1400000.0, 3, 'Москва, ул Минская 13', 42);
