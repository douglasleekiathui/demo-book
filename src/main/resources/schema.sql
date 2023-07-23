create table book(
	isbn nvarchar(13) primary key,
	title ntext,
	publish_year smallint,
	price decimal(10,2),
	genre nvarchar(255)
);

create table author(
	id bigint auto_increment primary key,
	name nvarchar(255),
	dob date
);

create table book_author(
	id bigint auto_increment primary key,
	isbn nvarchar(13),
	author_id bigint
)
