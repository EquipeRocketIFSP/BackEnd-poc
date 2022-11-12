INSERT INTO funcionario(user_name, password, email, cep, clinica, cpf, logradouro, nome, numero) VALUES ('Usuario Mario',
                                                            '$2a$10$IyMS1RLQcAJg.lMKb6WKBuzQV0DTN63rhZ9i/MKxWscC.Avsh0ka.',
                                                            'mario@quem.viu',
                                                            '09089777',
                                                            1,
                                                            '48957609800',
                                                            'rua kdjaba',
                                                            'meu',
                                                            '1');

# INSERT INTO agendamento(animal_id, data_consulta, tipo_consulta) VALUES (1, 1, '2022-10-29T15:31', 'Tipo da consulta');

INSERT INTO perfil(perfil_id, funcionario_id) VALUES (1,3);