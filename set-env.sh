#! /bin/bash 


export DOCKER_IP=$(boot2docker ip)
export DOCKER_HOST_IP=${DOCKER_IP}

export CASSANDRA_HOST=${DOCKER_IP?}
export SPRING_DATA_MONGODB_URI=mongodb://${DOCKER_IP?}/testdb
export SPRING_REDIS_HOST=${DOCKER_IP?}
export SPRING_REDIS_PORT=6379
export SPRING_DATASOURCE_URL=jdbc:mysql://${DOCKER_IP?}/test
export SPRING_RABBITMQ_HOST=${DOCKER_IP?}
