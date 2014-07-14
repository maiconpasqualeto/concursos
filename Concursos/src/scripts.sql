-- SQL Manager 2007 Lite for PostgreSQL 4.4.0.1
-- ---------------------------------------
-- Host      : 192.168.20.4
-- Database  : concursos
-- Version   : PostgreSQL 8.1.11 on i486-pc-linux-gnu, compiled by GCC cc (GCC) 4.1.2 20061115 (prerelease) (Debian 4.1.1-21)

 CREATE SCHEMA "concursos";

--
-- Definition for sequence candidato_cod_candidato_seq (OID = 49699) : 
--
CREATE SEQUENCE concursos.candidato_cod_candidato_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
CREATE TABLE concursos.candidato (
    cod_candidato integer DEFAULT nextval('concursos.candidato_cod_candidato_seq'::regclass) NOT NULL,
    nome character varying(250) NOT NULL,
    cpf character varying(11) NOT NULL,
    numero_inscricao character varying(20),
    cod_concurso integer,
    cod_cargo integer,
    cod_lotacao integer,
    endereco character varying(250),
    end_numero character varying(15),
    bairro character varying(50),
    cidade character varying(100),
    uf character varying(2),
    cep character varying(8),
    telefone_ddd character varying(5),
    telefone character varying(15),
    nome_pai character varying(100),
    nome_mae character varying(100),
    data_nascimento date,
    local_nascimento character varying(100),
    uf_nascimento character varying(2),
    cod_estado_civil integer,
    possui_deficiencia boolean,
    ident_tipo character varying(10),
    ident_numero character varying(15),
    ident_expedicao date
) WITHOUT OIDS;

--
-- Definition for sequence cargo_cod_cargo_seq (OID = 49703) : 
--
CREATE SEQUENCE concursos.cargo_cod_cargo_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
--
-- Structure for table cargo (OID = 49701) : 
--
CREATE TABLE concursos.cargo (
    cod_cargo integer DEFAULT nextval('concursos.cargo_cod_cargo_seq'::regclass) NOT NULL,
    cargo character varying(50) NOT NULL,
    cod_categoria_cargo integer NOT NULL
) WITHOUT OIDS;

--
-- Definition for sequence categoria_cargo_cod_categoria_cargo_seq (OID = 49707) : 
--
CREATE SEQUENCE concursos.categoria_cargo_cod_categoria_cargo_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
--
-- Structure for table categoria_cargo (OID = 49705) : 
--
CREATE TABLE concursos.categoria_cargo (
    cod_categoria_cargo integer DEFAULT nextval('concursos.categoria_cargo_cod_categoria_cargo_seq'::regclass) NOT NULL,
    categoria_cargo character varying(30) NOT NULL,
    cod_grupo_cargo integer NOT NULL
) WITHOUT OIDS;

--
-- Definition for sequence concurso_cod_concurso_seq (OID = 49711) : 
--
CREATE SEQUENCE concursos.concurso_cod_concurso_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
--
-- Structure for table concurso (OID = 49709) : 
--
CREATE TABLE concursos.concurso (
    cod_concurso integer DEFAULT nextval('concursos.concurso_cod_concurso_seq'::regclass) NOT NULL,
    concurso character varying(100) NOT NULL
) WITHOUT OIDS;

--
-- Definition for sequence grupo_cargo_cod_grupo_cargo_seq (OID = 49715) : 
--
CREATE SEQUENCE concursos.grupo_cargo_cod_grupo_cargo_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
--
-- Structure for table grupo_cargo (OID = 49713) : 
--
CREATE TABLE concursos.grupo_cargo (
    cod_grupo_cargo integer DEFAULT nextval('concursos.grupo_cargo_cod_grupo_cargo_seq'::regclass) NOT NULL,
    grupo_cargo character varying(30) NOT NULL
) WITHOUT OIDS;

--
-- Definition for sequence lotacao_cod_lotacao_seq (OID = 49719) : 
--
CREATE SEQUENCE concursos.lotacao_cod_lotacao_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
--
-- Structure for table lotacao (OID = 49717) : 
--
CREATE TABLE concursos.lotacao (
    cod_lotacao integer DEFAULT nextval('concursos.lotacao_cod_lotacao_seq'::regclass) NOT NULL,
    lotacao character varying(50) NOT NULL
) WITHOUT OIDS;

--
-- Definition for sequence tab_estado_civil_cod_estado_civil_seq (OID = 49723) : 
--
CREATE SEQUENCE concursos.tab_estado_civil_cod_estado_civil_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
--
-- Structure for table tab_estado_civil (OID = 49721) : 
--
CREATE TABLE concursos.tab_estado_civil (
    cod_estado_civil integer DEFAULT nextval('concursos.tab_estado_civil_cod_estado_civil_seq'::regclass) NOT NULL,
    estado_civil character varying(50) NOT NULL
) WITHOUT OIDS;

--
-- Definition for sequence tab_inscricao_cod_inscricao_seq (OID = 49727) : 
--
CREATE SEQUENCE concursos.tab_inscricao_cod_inscricao_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
--
-- Structure for table tab_inscricao (OID = 49725) : 
--
CREATE TABLE concursos.tab_inscricao (
    cod_inscricao integer DEFAULT nextval('concursos.tab_inscricao_cod_inscricao_seq'::regclass) NOT NULL,
    data_inscricao timestamp without time zone NOT NULL
) WITHOUT OIDS;

--
-- Structure for table tab_uf (OID = 49729) : 
--
CREATE TABLE concursos.tab_uf (
    uf character varying(2) NOT NULL,
    estado character varying(50) NOT NULL
) WITHOUT OIDS;

--
-- Definition for index candidato_pkey (OID = 49739) : 
--
ALTER TABLE ONLY concursos.candidato
    ADD CONSTRAINT candidato_pkey PRIMARY KEY (cod_candidato);
--
-- Definition for index cargo_pkey (OID = 49741) : 
--
ALTER TABLE ONLY concursos.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (cod_cargo);
--
-- Definition for index categoria_cargo_pkey (OID = 49743) : 
--
ALTER TABLE ONLY concursos.categoria_cargo
    ADD CONSTRAINT categoria_cargo_pkey PRIMARY KEY (cod_categoria_cargo);
--
-- Definition for index concurso_pkey (OID = 49745) : 
--
ALTER TABLE ONLY concursos.concurso
    ADD CONSTRAINT concurso_pkey PRIMARY KEY (cod_concurso);
--
-- Definition for index grupo_cargo_pkey (OID = 49747) : 
--
ALTER TABLE ONLY concursos.grupo_cargo
    ADD CONSTRAINT grupo_cargo_pkey PRIMARY KEY (cod_grupo_cargo);
--
-- Definition for index lotacao_pkey (OID = 49749) : 
--
ALTER TABLE ONLY concursos.lotacao
    ADD CONSTRAINT lotacao_pkey PRIMARY KEY (cod_lotacao);
--
-- Definition for index tab_estado_civil_pkey (OID = 49751) : 
--
ALTER TABLE ONLY concursos.tab_estado_civil
    ADD CONSTRAINT tab_estado_civil_pkey PRIMARY KEY (cod_estado_civil);
--
-- Definition for index tab_inscricao_pkey (OID = 49753) : 
--
ALTER TABLE ONLY concursos.tab_inscricao
    ADD CONSTRAINT tab_inscricao_pkey PRIMARY KEY (cod_inscricao);
--
-- Definition for index tab_uf_pkey (OID = 49755) : 
--
ALTER TABLE ONLY concursos.tab_uf
    ADD CONSTRAINT tab_uf_pkey PRIMARY KEY (uf);
--
-- Definition for index candidato_cargo_fkey (OID = 49757) : 
--
ALTER TABLE ONLY concursos.candidato
    ADD CONSTRAINT candidato_cargo_fkey FOREIGN KEY (cod_cargo) REFERENCES concursos.cargo(cod_cargo);
--
-- Definition for index candidato_concurso_fkey (OID = 49762) : 
--
ALTER TABLE ONLY concursos.candidato
    ADD CONSTRAINT candidato_concurso_fkey FOREIGN KEY (cod_concurso) REFERENCES concursos.concurso(cod_concurso);
--
-- Definition for index candidato_estado_civil_fkey (OID = 49767) : 
--
ALTER TABLE ONLY concursos.candidato
    ADD CONSTRAINT candidato_estado_civil_fkey FOREIGN KEY (cod_estado_civil) REFERENCES concursos.tab_estado_civil(cod_estado_civil);
--
-- Definition for index candidato_lotacao_fkey (OID = 49772) : 
--
ALTER TABLE ONLY concursos.candidato
    ADD CONSTRAINT candidato_lotacao_fkey FOREIGN KEY (cod_lotacao) REFERENCES concursos.lotacao(cod_lotacao);
--
-- Definition for index cargo_categoria_cargo_fkey (OID = 49777) : 
--
ALTER TABLE ONLY concursos.cargo
    ADD CONSTRAINT cargo_categoria_cargo_fkey FOREIGN KEY (cod_categoria_cargo) REFERENCES concursos.categoria_cargo(cod_categoria_cargo);
--
-- Definition for index categoria_cargo_grupo_fkey (OID = 49782) : 
--
ALTER TABLE ONLY concursos.categoria_cargo
    ADD CONSTRAINT categoria_cargo_grupo_fkey FOREIGN KEY (cod_grupo_cargo) REFERENCES concursos.grupo_cargo(cod_grupo_cargo);
--
-- Data for sequence concursos.candidato_cod_candidato_seq (OID = 49699)
--
SELECT pg_catalog.setval('concursos.candidato_cod_candidato_seq', 1, false);
--
-- Data for sequence concursos.cargo_cod_cargo_seq (OID = 49703)
--
SELECT pg_catalog.setval('concursos.cargo_cod_cargo_seq', 1, false);
--
-- Data for sequence concursos.categoria_cargo_cod_categoria_cargo_seq (OID = 49707)
--
SELECT pg_catalog.setval('concursos.categoria_cargo_cod_categoria_cargo_seq', 1, false);
--
-- Data for sequence concursos.concurso_cod_concurso_seq (OID = 49711)
--
SELECT pg_catalog.setval('concursos.concurso_cod_concurso_seq', 1, false);
--
-- Data for sequence concursos.grupo_cargo_cod_grupo_cargo_seq (OID = 49715)
--
SELECT pg_catalog.setval('concursos.grupo_cargo_cod_grupo_cargo_seq', 1, false);
--
-- Data for sequence concursos.lotacao_cod_lotacao_seq (OID = 49719)
--
SELECT pg_catalog.setval('concursos.lotacao_cod_lotacao_seq', 1, false);
--
-- Data for sequence concursos.tab_estado_civil_cod_estado_civil_seq (OID = 49723)
--
SELECT pg_catalog.setval('concursos.tab_estado_civil_cod_estado_civil_seq', 1, false);
--
-- Data for sequence concursos.tab_inscricao_cod_inscricao_seq (OID = 49727)
--
SELECT pg_catalog.setval('concursos.tab_inscricao_cod_inscricao_seq', 1, false);
--
-- Comments
--
COMMENT ON SCHEMA concursos IS 'Concursos schema';

-- Executado em 26/11/2008
ALTER TABLE concursos.candidato ADD COLUMN email character varying(50);
ALTER TABLE concursos.candidato ADD COLUMN senha character varying(50);
ALTER TABLE concursos.candidato ALTER COLUMN senha SET NOT NULL;

ALTER TABLE concursos.candidato ADD COLUMN complemento character varying(50);
ALTER TABLE concursos.candidato ADD COLUMN deficiencia character varying(100);

ALTER TABLE concursos.candidato ALTER cep TYPE character varying(10);

ALTER TABLE concursos.candidato ALTER ident_tipo TYPE character varying(25);

ALTER TABLE concursos.candidato ADD COLUMN identidade_orgao_expedidor character varying(20);


CREATE TABLE concursos.cargo_lotacao
(
  cod_cargo integer NOT NULL,
  cod_lotacao integer NOT NULL,
  CONSTRAINT cargo_lotacao_pkey PRIMARY KEY (cod_cargo, cod_lotacao),
  CONSTRAINT cargo_lotacao_cargo_fkey FOREIGN KEY (cod_cargo)
      REFERENCES concursos.cargo (cod_cargo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT cargo_lotacao_lotacao_fkey FOREIGN KEY (cod_lotacao)
      REFERENCES concursos.lotacao (cod_lotacao) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
-- at� aqui

-- Executado em 16/12/2008
ALTER TABLE concursos.candidato ADD COLUMN sexo character varying(1);
-- at� aqui

-- Executado em 24/12/2008
ALTER TABLE concursos.lotacao ALTER lotacao TYPE character varying(100);

ALTER TABLE concursos.candidato ALTER COLUMN senha DROP NOT NULL;

CREATE INDEX candidato_cpf_index ON concursos.candidato (cpf);
-- at� aqui

-- 28/12/2011
ALTER TABLE concursos.concurso ADD COLUMN tem_senha character(1);
ALTER TABLE concursos.concurso ADD COLUMN senha character varying(30);

UPDATE concursos.concurso SET tem_senha = 'N'

-- 29/12/2011
ALTER TABLE concursos.concurso ADD COLUMN pagamento_nome character varying(100);
ALTER TABLE concursos.concurso ADD COLUMN pagamento_banco character varying(30);
ALTER TABLE concursos.concurso ADD COLUMN pagamento_agencia character varying(10);
ALTER TABLE concursos.concurso ADD COLUMN pagamento_conta character varying(20);

-- 27/01/2012

ALTER TABLE concursos.concurso ADD COLUMN ultimo_num_inscricao integer;


