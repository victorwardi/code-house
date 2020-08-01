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

