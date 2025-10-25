change application.properties according to your needs

spring.application.name=student-management
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/smsdb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JPA
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
