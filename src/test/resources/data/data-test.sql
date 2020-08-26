-- Authors ###########################################################################################################################
insert into author (id, create_date_time, description, email, name)
values (1, NOW(),
        'Fernando Boaglio, graduated from UNESP in BCC, was an official instructor at Sun Microsystems and Oracle Education. Currently, ' ||
        'he contributes to some open source projects, such as KDE, Jenkins, among others. Keep your blog at boaglio.com.',
        'fernando@boaglio.com', 'Fernando Boaglio');

insert into author (id, create_date_time, description, email, name)
values (2, NOW(), 'Alberto Souza holds a BA in Computer Science from Universidade Salvador and a developer since 2005, ' ||
                     'having participated in many web projects and experimented with different languages. Participates in open source projects such as Stella and VRaptor. ' ||
                     'He is SCJP certified and works as a developer and instructor for Caelum', 'alberto@souza.com', 'Alberto Souza');

-- Categories ###########################################################################################################################
insert into category (id, name) values (1, 'JAVA');
insert into category (id, name) values (2, 'Spring');

-- Books ###############################################################################################################################
insert into book (author_id, category_id, description, isbn, pages, price, publish_date, sumary, title)
values (1, 1, 'Accelerate the development of microservices', '978-85-94120-00-7', 154, 60.00, NOW(), 'sumary...','Java Microsevices');

insert into book (author_id, category_id, description, isbn, pages, price, publish_date, sumary, title)
values (2, 2, 'Master the main Java web framework', '978-85-5519-019-3', 260, 78.00, NOW(), 'sumary...', 'Spring MVC');

-- Countries ###########################################################################################################################
 insert into country (id, name) values (1, 'Brazil');
insert into country (id, name) values (2, 'Germany');

-- States ##############################################################################################################################
insert into state (id, name, country_id) values (1, 'Rio de Janeiro', 1);
insert into state (id, name, country_id) values (2, 'Berlin', 2);