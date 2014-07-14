--
-- PostgreSQL database dump
--

-- Started on 2009-05-03 17:38:19

SET search_path = excel, pg_catalog;

CREATE TABLE excel.tab_estado_civil (
    cod_estado_civil serial primary key,
    estado_civil character varying(50)
);

CREATE TABLE excel.tab_inscricao (
    cod_inscricao serial primary key,
    data_inscricao timestamp without time zone
);

CREATE TABLE excel.tab_uf (
    uf character varying(2) NOT NULL primary key,
    estado character varying(50)
);

CREATE TABLE excel.boleto_bancario (
    cod_boleto_bancario serial primary key,
    numero_inscricao character varying(15),
    numero_documento character varying(15),
    data_emissao timestamp without time zone
);

CREATE TABLE excel.candidato (
    cod_candidato serial primary key,
    nome character varying(250),
    cpf character varying(11),
    numero_inscricao character varying(15),
    cod_concurso integer,
    cod_cargo integer,
    endereco character varying(250),
    end_numero character varying(15),
    complemento character varying(50),
    bairro character varying(100),
    cidade character varying(100),
    uf character varying(2),
    cep character varying(10),
    telefone_ddd character varying(5),
    telefone character varying(15),
    data_nascimento timestamp without time zone,
    local_nascimento character varying(100),
    uf_nascimento character varying(2),
    cod_estado_civil integer,
    sexo character varying(1),
    possui_deficiencia character varying(1),
    deficiencia character varying(150),
    ident_tipo character varying(25),
    ident_numero character varying(15),
    ident_orgao_expedidor character varying(20),
    ident_uf character varying(2),
    email character varying(50),
    senha character varying(50),
    cod_municipio_ibge integer,
    cod_uf_ibge integer
);

CREATE TABLE excel.candidato_0012008 (
    cod_candidato serial primary key,
    nome character varying(250),
    cpf character varying(11),
    numero_inscricao character varying(15),
    cod_cargo_012009 integer,
    cod_concurso integer,
    cod_cargo_area integer,
    cod_lotacao_area integer,
    endereco character varying(250),
    end_numero character varying(15),
    bairro character varying(100),
    cidade character varying(100),
    uf character varying(2),
    cep character varying(10),
    telefone_ddd character varying(5),
    telefone character varying(15),
    nome_pai character varying(100),
    nome_mae character varying(100),
    data_nascimento timestamp without time zone,
    local_nascimento character varying(100),
    uf_nascimento character varying(2),
    cod_estado_civil integer,
    possui_deficiencia character varying(1),
    ident_tipo character varying(25),
    ident_numero character varying(15),
    ident_expedicao timestamp without time zone,
    email character varying(50),
    senha character varying(50),
    complemento character varying(50),
    deficiencia character varying(150),
    identidade_orgao_expedidor character varying(20),
    sexo character varying(1),
    cod_municipio_ibge integer,
    nova_inscricao character varying(1)
);

CREATE TABLE excel.cargo (
    cod_cargo serial primary key,
    cargo character varying(150),
    vagas_cargo integer,
    inscritos_cargo integer,
    inscricao_homologada integer,
    inscricao_homologada_necess_especial integer,
    inscricao_nao_homologada integer,
    prova_escrita_presentes integer,
    prova_escrita_ausentes integer,
    aceita_fora_municipio character varying(1),
    cod_municipio_ibge integer
);

CREATE TABLE excel.concurso (
    cod_concurso serial primary key,
    concurso character varying(100)
);

CREATE TABLE excel.municipio (
    cod_municipio_ibge integer NOT NULL primary key,
    municipio character varying(50)
);

ALTER TABLE excel.candidato ADD CONSTRAINT candidato_cargo_fkey FOREIGN KEY (cod_cargo) REFERENCES excel.cargo (cod_cargo)    ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE excel.candidato ADD CONSTRAINT candidato_estado_civil_fkey FOREIGN KEY (cod_estado_civil) REFERENCES excel.tab_estado_civil (cod_estado_civil)    ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE excel.candidato ADD CONSTRAINT candidato_concurso_fkey FOREIGN KEY (cod_concurso) REFERENCES excel.concurso (cod_concurso)    ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE excel.candidato_0012008 ADD CONSTRAINT candidato_0012008_cargo_fkey FOREIGN KEY (cod_cargo_012009) REFERENCES excel.cargo (cod_cargo)    ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE excel.candidato_0012008 ADD CONSTRAINT candidato_0012008_estado_civil_fkey FOREIGN KEY (cod_estado_civil) REFERENCES excel.tab_estado_civil (cod_estado_civil)    ON UPDATE NO ACTION ON DELETE NO ACTION;

-- 06/05/2009 - 20:00 - Produção

ALTER TABLE excel.boleto_bancario ALTER numero_inscricao TYPE character varying(15);
ALTER TABLE excel.boleto_bancario ALTER numero_documento TYPE character varying(25);
ALTER TABLE excel.boleto_bancario ADD COLUMN data_vencimento date;
ALTER TABLE excel.boleto_bancario ADD COLUMN linha_digitavel character varying(100);
ALTER TABLE excel.boleto_bancario ADD COLUMN sacado character varying(150);
ALTER TABLE excel.boleto_bancario ALTER COLUMN numero_inscricao SET STATISTICS -1;
ALTER TABLE excel.boleto_bancario ALTER COLUMN numero_documento SET STATISTICS -1;
ALTER TABLE excel.boleto_bancario ADD COLUMN cargo character varying(150);
ALTER TABLE excel.boleto_bancario ADD COLUMN cpf_sacado character varying(15);

-- 06/05/2009 - 22:15 - Produção

ALTER TABLE excel.boleto_bancario ADD COLUMN codigo_barra character varying(150);
ALTER TABLE excel.boleto_bancario ADD COLUMN nosso_numero character varying(25);
ALTER TABLE excel.boleto_bancario ADD COLUMN valor double precision;

-- 07/05/2009 - 10:29 - Produção

DROP TABLE excel.candidato;

CREATE TABLE excel.candidato
(
  cod_candidato integer NOT NULL DEFAULT nextval('candidato_cod_candidato_seq'::regclass),
  nome character varying(250) NOT NULL,
  cpf character varying(11),
  numero_inscricao character varying(15),
  cod_concurso integer,
  cod_cargo integer NOT NULL,
  endereco character varying(250) NOT NULL,
  end_numero character varying(15) NOT NULL,
  complemento character varying(50),
  bairro character varying(100) NOT NULL,
  cidade character varying(100),
  uf character varying(2),
  cep character varying(10) NOT NULL,
  telefone_ddd character varying(5) NOT NULL,
  telefone character varying(15) NOT NULL,
  data_nascimento date NOT NULL,
  local_nascimento character varying(100) NOT NULL,
  uf_nascimento character varying(2) NOT NULL,
  cod_estado_civil integer,
  sexo character varying(1) NOT NULL,
  deficiencia character varying(150),
  ident_tipo character varying(25) NOT NULL,
  ident_numero character varying(15) NOT NULL,
  ident_orgao_expedidor character varying(20) NOT NULL,
  ident_uf character varying(2) NOT NULL,
  email character varying(50),
  senha character varying(50),
  cod_municipio_ibge integer,
  cod_uf_ibge integer,
  CONSTRAINT candidato_pkey PRIMARY KEY (cod_candidato),
  CONSTRAINT candidato_cargo_fkey FOREIGN KEY (cod_cargo)
      REFERENCES excel.cargo (cod_cargo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT candidato_concurso_fkey FOREIGN KEY (cod_concurso)
      REFERENCES excel.concurso (cod_concurso) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT candidato_estado_civil_fkey FOREIGN KEY (cod_estado_civil)
      REFERENCES excel.tab_estado_civil (cod_estado_civil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;

ALTER TABLE excel.candidato ADD COLUMN possui_deficiencia boolean NOT NULL;

ALTER TABLE excel.candidato_0012008 DROP COLUMN possui_deficiencia;
ALTER TABLE excel.candidato_0012008 ADD COLUMN possui_deficiencia boolean;

ALTER TABLE excel.candidato ALTER cod_municipio_ibge TYPE character varying(15);
ALTER TABLE excel.candidato ALTER cod_uf_ibge TYPE character varying(15);
ALTER TABLE excel.candidato ALTER COLUMN cod_municipio_ibge SET STATISTICS -1;
ALTER TABLE excel.candidato ALTER COLUMN cod_uf_ibge SET STATISTICS -1;
ALTER TABLE excel.candidato
   ALTER COLUMN ident_uf DROP NOT NULL;
ALTER TABLE excel.candidato ALTER COLUMN ident_uf SET STATISTICS -1;
ALTER TABLE excel.candidato_0012008 ALTER cod_municipio_ibge TYPE character varying(15);
ALTER TABLE excel.candidato_0012008 ALTER COLUMN cod_municipio_ibge SET STATISTICS -1;


ALTER TABLE excel.candidato ADD COLUMN deferimento character varying(1);
ALTER TABLE excel.candidato ADD COLUMN deficiencia_comprovada character varying(1);
ALTER TABLE excel.candidato ADD COLUMN situacao_inscricao character varying(20);


ALTER TABLE excel.candidato_0012008 ADD COLUMN ident_uf character varying(2);
ALTER TABLE excel.candidato_0012008 ADD COLUMN deficiencia_comprovada character varying(1);

ALTER TABLE excel.cargo ADD COLUMN nivel character varying(2);
ALTER TABLE excel.cargo ADD COLUMN valor double;

-- 12/05/2009 - 23:37 - Produção

ALTER TABLE excel.candidato ADD CONSTRAINT candidato_cpf_uk UNIQUE (cpf);
CREATE INDEX candidato_cpf_idx    ON excel.candidato (cpf);

ALTER TABLE excel.candidato ADD COLUMN inscricao_2008 character varying(1);

-- 26/05/2009 - 22:54 - Produção

---------------------
--- INSERE DADOS ----

insert into excel.cargo (cargo) values ('Agente Comunitário de Saúde');
insert into excel.concurso(concurso) values ('Ponta Porã 001/2009');
insert into excel.tab_estado_civil (estado_civil) values ('Solteiro');

-- Insere candidato EXCEL

insert into excel.candidato 
(nome, cpf, numero_inscricao, cod_concurso, cod_cargo,
endereco, end_numero, bairro, cidade, uf, cep, telefone_ddd,
telefone, data_nascimento, local_nascimento, uf_nascimento,
cod_estado_civil, sexo, possui_deficiencia, ident_tipo,
ident_numero, ident_orgao_expedidor, ident_uf) values
('IVANILSON VELOSO SOARES', '39487016791', '123456', 1, 1,
'Rua Teste', '123', 'centro', 'Campo Grande', 'MS', '79002160',
'67', '33251108', '1990/02/15', 'Campo Grande', 'MS', 1, 'M',
false, 'Identidade Civil', '987654', 'SSP', 'MS');

-- Insere candidato_0012008 EXCEL

insert into excel.candidato_0012008 
(nome, cpf, numero_inscricao, cod_concurso, cod_cargo_012009,
endereco, end_numero, bairro, cidade, uf, cep, telefone_ddd,
telefone, data_nascimento, local_nascimento, uf_nascimento,
cod_estado_civil, sexo, possui_deficiencia, ident_tipo,
ident_numero, identidade_orgao_expedidor, nova_inscricao) values
('IVANILSON VELOSO SOARES', '39487016791', '123456', 1, 1,
'Rua Teste', '123', 'centro', 'Campo Grande', 'MS', '79002160',
'67', '33251108', '1990/02/15', 'Campo Grande', 'MS', 1, 'M',
false, 'Identidade Civil', '987654', 'SSP', 'N');


