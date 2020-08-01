-- Authors
insert into author (id, create_date_time, description, email, name)
values (null, CURRENT_TIMESTAMP(),
        'Fernando Boaglio, graduated from UNESP in BCC, was an official instructor at Sun Microsystems and Oracle Education. Currently, ' ||
        'he contributes to some open source projects, such as KDE, Jenkins, among others. Keep your blog at boaglio.com.',
        'fernando@boaglio.com', 'Fernando Boaglio');

insert into author (id, create_date_time, description, email, name)
values (null, CURRENT_TIMESTAMP(), 'Alberto Souza holds a BA in Computer Science from Universidade Salvador and a developer since 2005, ' ||
                                   'having participated in many web projects and experimented with different languages. Participates in open source projects such as Stella and VRaptor. ' ||
                                   'He is SCJP certified and works as a developer and instructor for Caelum', 'alberto@souza.com', 'Alberto Souza');

-- Categories
insert into category (id, name) values (null, 'JAVA');
insert into category (id, name) values (null, 'Spring');

-- Books
insert into book (author_id, category_id, description, isbn, pages, price, publish_date, sumary, title)
values (1, 1, 'Accelerate the development of microservices', '978-85-94120-00-7', 154, 60.00, CURRENT_TIMESTAMP(), 'sumary...','Java Microsevices');

insert into book (author_id, category_id, description, isbn, pages, price, publish_date, sumary, title)
values (2, 2, 'Master the main Java web framework', '978-85-5519-019-3', 260, 78.00, CURRENT_TIMESTAMP(), 'sumary...', 'Spring MVC');

