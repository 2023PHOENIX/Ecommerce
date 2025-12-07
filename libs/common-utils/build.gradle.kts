// libs/common-utils/build.gradle

plugins {
    id("java-library")
    // This plugin is essential for importing the BOM below
    id("io.spring.dependency-management")
}

group = "com.ecomm"
version = "1.0.0"

repositories {
    mavenCentral()
}

// 💡 NEW: Import the Spring Boot BOM to manage versions
dependencyManagement {
    imports {
        // Replace 3.3.1 with your main application's Spring Boot version
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.3.1")
    }
}

dependencies {
    // Lombok (Version managed by Spring Boot BOM)
    // Removed explicit version (1.18.38)
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Jakarta validation for DTO checks (Version managed by Spring Boot BOM)
    // Removed explicit version (3.1.1)
    implementation("jakarta.validation:jakarta.validation-api")

    // Spring dependencies (Versions managed by Spring Boot BOM)
    implementation("org.springframework:spring-context")
    implementation("org.springframework.kafka:spring-kafka")

    // Jackson (Versions managed by Spring Boot BOM)
    // Removed explicit versions
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
}