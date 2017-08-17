
--==========================  USERS  =====================--

-- DROP USER characterBuilder CASCADE;

-- CREATE USER characterBuilder
-- IDENTIFIED BY povertyrules
-- DEFAULT TABLESPACE users
-- TEMPORARY TABLESPACE temp;
--
-- GRANT connect to characterBuilder;
-- GRANT resource to characterBuilder;
-- GRANT CREATE SESSION TO characterBuilder;
-- GRANT CREATE TABLE TO characterBuilder;
-- GRANT CREATE VIEW TO characterBuilder;
-- GRANT CREATE MATERIALIZED VIEW TO characterBuilder;
-- GRANT create session to characterBuilder;
-- ALTER USER characterBuilder QUOTA 10m ON users;
--
-- conn characterBuilder/povertyrules


DROP USER characterBuilderTest CASCADE;

CREATE USER characterBuilderTest
IDENTIFIED BY povertyrules
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp;

GRANT connect to characterBuilderTest;
GRANT resource to characterBuilderTest;
GRANT CREATE SESSION TO characterBuilderTest;
GRANT CREATE TABLE TO characterBuilderTest;
GRANT CREATE VIEW TO characterBuilderTest;
GRANT CREATE MATERIALIZED VIEW TO characterBuilderTest;
GRANT create session to characterBuilderTest;
ALTER USER characterBuilderTest QUOTA 10m ON users;

conn characterBuilderTest/povertyrules
