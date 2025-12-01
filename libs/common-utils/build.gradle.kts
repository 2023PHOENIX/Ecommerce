plugins {
    id("java-library")
    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

group = "com.ecomm"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    // Jakarta validation for DTO checks
    implementation("jakarta.validation:jakarta.validation-api:3.1.1")
    implementation("org.springframework:spring-context")
    implementation ("org.springframework.kafka:spring-kafka")

    // Required by JsonDeserializer, which uses com.fasterxml.jackson.core.type.TypeReference
    // The version will be managed by the Spring Boot BOM if properly set up.
    implementation ("com.fasterxml.jackson.core:jackson-databind")
    implementation ("com.fasterxml.jackson.core:jackson-core")
    implementation ("com.fasterxml.jackson.core:jackson-annotations")
}
