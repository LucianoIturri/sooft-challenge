mvn clean install
sleep 10
docker compose down
docker compose up --build --force-recreate