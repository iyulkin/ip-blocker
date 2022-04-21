 .PHONY: build

#собрать и запустить образы
build:
	./init.sh && docker-compose up;
#вырубить все сервисы
stop:
	docker-compose down;