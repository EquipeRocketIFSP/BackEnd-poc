INSERT INTO clinica (nome_fantasia,razao_social,cnpj,cnae,cep,logradouro,numero,bairro,cidade,estado,responsavel_tecnico,email, dono_cpf)
VALUES ("cLI","Teste", "65.622.164/0001-04","46546","03808-130","Rua Miguel Rachid", "546","Vila Paranaguá","São Paulo","AM","resp. tecnico","teste", "759.755.587-35");


INSERT INTO funcionario(user_name, password, email, cep, clinica, cpf, logradouro, nome, numero)
VALUES ('Usuario Mario', '$2a$10$IyMS1RLQcAJg.lMKb6WKBuzQV0DTN63rhZ9i/MKxWscC.Avsh0ka.','mario@quem.viu','09089777',1,'48957609800','rua kdjaba','meu','1');

INSERT INTO perfil(perfil_id, funcionario_id) VALUES (1,3);
