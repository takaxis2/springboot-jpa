-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values (1, 'David', 'david@gmail.com', now(), now());

-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values (2, 'dennis', 'dennis@fastcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values (3, 'sophia', 'sophia@slowcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values (4, 'james', 'james@slowcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values (5, 'martin', 'martin@another.com', now(), now());

insert into publisher(`id`, `name`) values (1, 'fastcampus');

insert into book(`id`, `name`, `publisher_id`,`deleted`, `status`) values (1, 'JPA package', 1, false, 100);

insert into book(`id`, `name`, `publisher_id`,`deleted`, `status`) values (2, 'Spring Security package', 1, false, 200);

insert into book(`id`, `name`, `publisher_id`,`deleted`, `status`) values (3, 'SpringBoot package', 1, true, 100);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (1, 'change book', 'good', 5.0, 1, 1);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (2, 'so fast', 'so so', 3.0, 2, 2);

insert into comment(`id`, `comment`, `review_id`) values (1, 'like either', 1);

insert into comment(`id`, `comment`, `review_id`) values (2, 'not bad', 1);

insert into comment(`id`, `comment`, `review_id`) values (3, 'not bad too', 2);