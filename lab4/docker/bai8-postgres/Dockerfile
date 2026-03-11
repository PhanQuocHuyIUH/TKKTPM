FROM postgres:15

ENV POSTGRES_DB=labdb
ENV POSTGRES_USER=labuser
ENV POSTGRES_PASSWORD=labpassword

COPY init.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 5432
