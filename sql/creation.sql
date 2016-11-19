CREATE TABLE especie (
	id SERIAL PRIMARY KEY NOT NULL,
	descricao TEXT NOT NULL
);


CREATE TABLE cliente (
	id SERIAL PRIMARY KEY NOT NULL,
	nome TEXT NOT NULL,
	endereco TEXT NOT NULL,
	bairro TEXT NOT NULL,
	cep TEXT NOT NULL,
	cidade TEXT NOT NULL,
	estado TEXT NOT NULL,
	telefone TEXT NOT NULL
);

CREATE TABLE animal (
	id SERIAL PRIMARY KEY NOT NULL,
	nome TEXT NOT NULL,
	id_especie INTEGER REFERENCES especie (id),
	id_cliente INTEGER REFERENCES cliente (id)
);
