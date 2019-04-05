INSERT INTO regiones (id, nombre) VALUES (1, "Nortemérica");
INSERT INTO regiones (id, nombre) VALUES (2,"Centroamérica");
INSERT INTO regiones (id, nombre) VALUES (3,"Sudamérica");
INSERT INTO regiones (id, nombre) VALUES (4,"Europa");
INSERT INTO regiones (id, nombre) VALUES (5,"Asía");
INSERT INTO regiones (id, nombre) VALUES (6,"Africa");
INSERT INTO regiones (id, nombre) VALUES (7,"Ocenia");
INSERT INTO regiones (id, nombre) VALUES (8,"Antártida");

INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (1,'Eduardo', 'Ocampo', 'ed.develit@gmail.com', '2019-01-10');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (3,'Victor', 'Mendoza', 'victor@gmail.com', '2019-05-22');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (5,'Antonio', 'Perez', 'antonio.perez@gmail.com', '2019-03-25');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (8,'Luis', 'Ruiz', 'quirozstorm@gmail.com', '2019-05-29');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (3,'Victor', 'Mendoza', 'victor@gmail.com', '2019-05-22');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (5,'Antonio', 'Perez', 'antonio.perez@gmail.com', '2019-03-25');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (8,'Luis', 'Ruiz', 'quirozstorm@gmail.com', '2019-05-29');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (3,'Victor', 'Mendoza', 'victor@gmail.com', '2019-05-22');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (5,'Antonio', 'Perez', 'antonio.perez@gmail.com', '2019-03-25');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (8,'Luis', 'Ruiz', 'quirozstorm@gmail.com', '2019-05-29');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (3,'Victor', 'Mendoza', 'victor@gmail.com', '2019-05-22');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (5,'Antonio', 'Perez', 'antonio.perez@gmail.com', '2019-03-25');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES (8,'Luis', 'Ruiz', 'quirozstorm@gmail.com', '2019-05-29');

/* Creamos algunos usuarios con sus roles */
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('andres','$2a$10$CQTYgcl56SJ.DWOddS2g1Ocze10E3sHksvvU7XkfYYkRKbOwMiHUW',1,'Andres','Guzman','profesor@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$9ol.nBJ1WwoCKVOslqwOfeSRxTWFl6lLxZRS9xhD.HjuttaoKEsfi',1,'Luis','Ruiz','luis@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('eddevelit','$2a$10$mEjs3VWtptWx593kZAaTUOJSwBxX56psT4x.C9QzziB7VH/rzELOG',1,'Eduardo','Ocampo','edd.develit@gmail.com');


INSERT INTO roles (nombre) values ('ROLE_USER');
INSERT INTO roles (nombre) values ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (2,1);