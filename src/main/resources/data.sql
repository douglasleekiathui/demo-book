insert into book(isbn, title, publish_year, price, genre) values
('0000000000000', 'title 1', 2000, 23.3, 'HORROR'),
('0000000000001', 'title 2', 2001, 23.4, 'ROMANCE'),
('0000000000002', 'title 3', 2002, 23.5, 'MYSTERY'),
('0000000000003', 'title 4', 2003, 23.6, 'BIOGRAPHY');

insert into author(name, dob) values 
('Higg Adams', '1975-01-01'),
('Charles Davison', '1980-12-31'),
('Zhang XueYong', '1985-02-20'),
('Mukesh Swalivarisa', '1990-10-31');

insert into book_author(isbn, author_id) values
('0000000000000', 1),
('0000000000001', 2),
('0000000000001', 3),
('0000000000001', 4),
('0000000000002', 3),
('0000000000002', 4);
