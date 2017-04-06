DROP TABLE Application_Log;
DROP SEQUENCE AppLogSequence;
CREATE TABLE Application_Log (
  id        NUMBER(38) PRIMARY KEY NOT NULL,
  message   VARCHAR2 (1024),
  createdBy VARCHAR2 (100),
  createdAt TIMESTAMP
);
CREATE SEQUENCE AppLogSequence;