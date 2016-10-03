DROP  table Session IF EXISTS;

CREATE TABLE Session (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
  session_name VARCHAR(100) NOT NULL,
  trainer VARCHAR(100) NOT NULL,
  length INTEGER NOT NULL,
  PRIMARY KEY(id)
);