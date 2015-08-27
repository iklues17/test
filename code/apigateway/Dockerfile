FROM java:openjdk-8u45-jdk
CMD java -jar trip-management.jar --spring.profiles.active=enableEureka --eureka.client.serviceUrl.defaultZone=http://${EUREKA_HOST_AND_PORT?}/eureka/
EXPOSE 8080
ADD build/libs/trip-management.jar .
