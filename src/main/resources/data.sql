INSERT INTO funcionario(user_name, password, email) VALUES ('Usuario Mario', '$2a$10$IyMS1RLQcAJg.lMKb6WKBuzQV0DTN63rhZ9i/MKxWscC.Avsh0ka.', 'mario@quem.viu');

INSERT INTO animal(especie, idade, nome, outros, pelagem, raca, sexo) VALUES ('canina', '3', 'totó', 'outros', 'curta', 'SRD', 'M');
INSERT INTO veterinario(email, nome, registrocrmv) VALUES ('vet@novo.com', 'Doolittle', 'SP-12345');
INSERT INTO tutor(cpf, email, endereco, nome, rg, telefone) VALUES ('123.456.789-01', 'tutor@do.toto', 'endereco', 'It\'s me Mario', 'Rgdsadsa', '(23) 4567890132');

INSERT INTO perfil(perfil_id, funcionario_id) VALUES (1,3);