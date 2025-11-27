plugins {
    id("java-library")
    id("io.spring.dependency-management")
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
}
