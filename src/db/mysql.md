# bigdata_portal

```bash
create database bigdata_portal;

create user 'portal'@'%' identified by 'pwdportal';
grant all privileges on bigdata_portal.* to portal@'%' identified by 'pwdportal';
flush privileges;
show grants for 'portal'@'%';
```