INSERT INTO clinica
    (nome_fantasia,razao_social,cnpj,cnae,cep,logradouro,numero,bairro,cidade,estado,responsavel_tecnico,email)
VALUES
    ("cLI","Teste", "65.622.164/0001-04","46546","03808-130","Rua Miguel Rachid", "546","Vila Paranaguá","São Paulo","AM","resp. tecnico","teste@email.COM");

INSERT INTO funcionario
    (user_name, password, email, cep, clinica_id, cpf, logradouro, nome, numero, bairro, celular)
VALUES
    ('Usuario Mario', '$2a$10$IyMS1RLQcAJg.lMKb6WKBuzQV0DTN63rhZ9i/MKxWscC.Avsh0ka.', 'mario@quem.viu',
        '00000-00', 1, '536.457.270-60', 'ruafake', 'nome', 's/n', 'BAIRRO', '789456213');

# INSERT INTO funcionario
# (bairro, celular, cep, cidade, cpf, email, estado, logradouro, nome, numero, password, rg, telefone, user_name, clinica_id)

INSERT INTO animal
    (especie, idade, nome, outros, pelagem, raca, sexo)
VALUES
    ('canina', '3', 'totó', 'outros', 'curta', 'SRD', 'MASCULINO');

INSERT INTO tutor
(cpf, email, endereco, nome, rg, telefone, estado)
VALUES
    ('123.456.789-01', 'tutor@do.toto', 'endereco', 'It\'s me Mario', 'Rgdsadsa', '(23) 4567890132', 'estado');


# INSERT INTO perfil(perfil_id, funcionario_id) VALUES (1,3);

# INSERT INTO veterinario
#     (email, nome, registrocrmv, email)
# VALUES
#     ('vet@novo.com', 'Doolittle', 'SP-12345', 'email@email');

INSERT INTO  veterinario (registrocrmv, id) value ('SP-12345', 1);