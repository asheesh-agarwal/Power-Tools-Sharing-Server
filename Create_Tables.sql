-- Table: pts_user

-- DROP TABLE pts_user;

CREATE TABLE pts_user
(
  id character varying(36) NOT NULL,
  userid character varying(36) NOT NULL,
  emailid character varying(100) NOT NULL,
  password bytea NOT NULL,
  firstname character varying(50) NOT NULL,
  lastname character varying(50) NOT NULL,
  mobilenumber character varying(12) NOT NULL,
  status character varying(20) NOT NULL,
  creationdate timestamp without time zone NOT NULL,
  updatedate timestamp without time zone,
  CONSTRAINT primary_key_user PRIMARY KEY (id),
  CONSTRAINT unique_userid UNIQUE (userid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pts_user
  OWNER TO postgres;

-- Table: pts_powertool

-- DROP TABLE pts_powertool;

CREATE TABLE pts_powertool
(
  id character varying(36) NOT NULL,
  toolname character varying(200) NOT NULL,
  userid character varying(36) NOT NULL,
  toolimage character varying,
  toolimagename character varying(100) NOT NULL,
  status character varying(50) NOT NULL,
  description character varying(500),
  creationdate timestamp without time zone NOT NULL,
  updatedate timestamp without time zone,
  CONSTRAINT primary_key_powertool PRIMARY KEY (id),
  CONSTRAINT foreign_key_powertool FOREIGN KEY (userid)
      REFERENCES pts_user (userid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT unique_powertool UNIQUE (userid, toolname)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pts_powertool
  OWNER TO postgres;
