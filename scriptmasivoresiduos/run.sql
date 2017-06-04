SET DEFINE OFF;
SET ECHO off;
 spool results_spool.txt

---Scripts en DDL
Prompt /ddl/ddlresiduos.sql
     @./ddl/ddlresiduos.sql

Prompt /dml/dmlresiduos.sql
     @./dml/dmlresiduos.sql

spool off