FROM openjdk:8
EXPOSE 8080
ADD target/spring-boot-blog-rest-api-image.jar spring-boot-blog-rest-api-image.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-blog-rest-api-image.jar"]