mvn clean install -DskipTests
sleep 10
docker compose down
docker compose up --build --force-recreate