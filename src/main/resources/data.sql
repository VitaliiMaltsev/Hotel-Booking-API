INSERT INTO category (id, description, occupancy)
VALUES
(1, 'single', 1),
(2, 'double', 2),
(3, 'studio', 2),
(4, 'presidential', 6);

INSERT INTO additional_option (id, option , price)
VALUES
(1, 'breakfast', 25),
(2, 'cleaning', 25),
(3, 'transfer', 50);


INSERT INTO `user`(id, email, username)
VALUES
(1, 'pedro@email.com','Pedro Carvalho'),
(2, 'manuel@email.com','Manuel Bastos'),
(3, 'tiago@email.com','Tiago Pereira'),
(4, 'rui@email.com','Rui Abreu'),
(5, 'luis@email.com','Luis Piedade');

insert into room (id, floor, room_number, category_id, price) values (1, 1, '101', 1, 50);
insert into room (id, floor, room_number, category_id, price) values (2, 1, '102', 1, 75);
insert into room (id, floor, room_number, category_id, price) values (3, 1, '103', 1, 100);
insert into room (id, floor, room_number, category_id, price) values (4, 1, '104', 1, 200);
insert into room (id, floor, room_number, category_id, price) values (5, 1, '105', 1, 50);
insert into room (id, floor, room_number, category_id, price) values (6, 2, '201', 1, 75);
insert into room (id, floor, room_number, category_id, price) values (7, 2, '202', 1, 100);
insert into room (id, floor, room_number, category_id, price) values (8, 2, '203', 1, 200);
insert into room (id, floor, room_number, category_id, price) values (9, 2, '204', 1, 50);
insert into room (id, floor, room_number, category_id, price) values (10, 2, '205', 1, 75);
insert into room (id, floor, room_number, category_id, price) values (11, 1, '101', 2, 50);
insert into room (id, floor, room_number, category_id, price) values (12, 1, '102', 2, 75);
insert into room (id, floor, room_number, category_id, price) values (13, 1, '103', 2, 100);
insert into room (id, floor, room_number, category_id, price) values (14, 1, '104', 2, 200);
insert into room (id, floor, room_number, category_id, price) values (15, 1, '105', 2, 50);
insert into room (id, floor, room_number, category_id, price) values (16, 2, '201', 2, 75);
insert into room (id, floor, room_number, category_id, price) values (17, 2, '202', 2, 100);
insert into room (id, floor, room_number, category_id, price) values (18, 2, '203', 2, 200);
insert into room (id, floor, room_number, category_id, price) values (19, 2, '204', 2, 50);
insert into room (id, floor, room_number, category_id, price) values (20, 2, '205', 2, 75);
insert into room (id, floor, room_number, category_id, price) values (21, 1, '301', 3, 50);
insert into room (id, floor, room_number, category_id, price) values (22, 3, '302', 4, 500);
insert into room (id, floor, room_number, category_id, price) values (23, 3, '303', 4, 500);

insert into booking (id, begin_date, end_date) values (1, '2018-11-29', '2018-12-02');
insert into booking (id, begin_date, end_date) values (2, '2018-11-27', '2018-11-29');
insert into booking (id, begin_date, end_date) values (3, '2018-11-27', '2018-11-28');
insert into booking (id, begin_date, end_date) values (4, '2018-10-27', '2018-11-7');
insert into booking (id, begin_date, end_date) values (5, '2018-11-27', '2018-11-28');
insert into booking (id, begin_date, end_date) values (6, '2018-04-27', '2018-11-8');


insert into booking_rooms (bookings_id, rooms_id) values (1, 1);
insert into booking_rooms (bookings_id, rooms_id) values (1, 16);
insert into booking_rooms (bookings_id, rooms_id) values (2, 2);
insert into booking_rooms (bookings_id, rooms_id) values (3, 5);
insert into booking_rooms (bookings_id, rooms_id) values (4, 17);
insert into booking_rooms (bookings_id, rooms_id) values (5, 20);
insert into booking_rooms (bookings_id, rooms_id) values (6, 20);

insert into user_bookings (bookings_id, user_id) values (1,1);
insert into user_bookings (bookings_id, user_id) values (2,3);
insert into user_bookings (bookings_id, user_id) values (3,4);
insert into user_bookings (bookings_id, user_id) values (4,2);
insert into user_bookings (bookings_id, user_id) values (5,1);
insert into user_bookings (bookings_id, user_id) values (6,1);

insert into room_additional_options(room_id, additional_options_id)
values (1,1),(1,2),(1,3);
