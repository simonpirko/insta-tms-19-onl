/* Если ваш суперюзер имеет имя "postgres" и при этом имеет пароль отличный от "postgres" */
alter user postgres with password 'postgres';

/* Если ваш суперюзер имеет имя отличное от "postgres" */
create user postgres with password 'postgres';
grant all privileges on database "postgres" to postgres;
grant all privileges on all tables in schema public to "postgres";