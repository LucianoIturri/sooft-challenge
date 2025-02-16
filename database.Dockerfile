FROM postgres:alpine
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_HOST=jdbc:postgresql://localhost:5432/transfers
VOLUME ["./var/pgdata:/var/lib/postgresql/data"]
EXPOSE 5432
COPY init.sql /docker-entrypoint-initdb.d/