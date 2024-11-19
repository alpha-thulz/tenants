package:
	mvn clean package -DskipTests
docker-start: package
	docker compose up