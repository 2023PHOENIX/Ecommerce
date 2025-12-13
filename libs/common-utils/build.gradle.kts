// libs/common-utils/build.gradle
import com.google.protobuf.gradle.ProtobufExtension
import com.google.protobuf.gradle.GenerateProtoTask
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


    // grpc related dependencies
    implementation("com.google.protobuf:protobuf-java:3.25.3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    // 2. GRPC RUNTIME (Resolves io.grpc.* errors)
    // Use the same version as the 'protoc-gen-grpc-java' plugin (1.60.1)
    implementation("io.grpc:grpc-stub:1.60.1")
    implementation("io.grpc:grpc-protobuf:1.60.1")

    // 3. ANNOTATION FIX (Resolves javax.annotation.* errors)
    // The generated code uses javax.annotation (JSR 250).
    // We need to provide the implementation, typically via the Jakarta version now:
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    // OR if that doesn't work, try the older javax version:
    // implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation ("org.springframework.boot:spring-boot-starter")
}
// libs/common-utils/build.gradle
configure<ProtobufExtension> {
    protoc {
        // Use the set() method for properties
        artifact = "com.google.protobuf:protoc:3.25.3"
    }

    plugins {
        // Use the create() method inside plugins or a simple assignment
        // If 'grpc' isn't recognized, you use the create function
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.60.1"
        }
    }

    // Correct way to configure all generated tasks in Kotlin DSL
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                // The 'java' plugin is usually implicitly configured, but we re-add it
                // We reference the plugin created above by name "grpc"
                create("grpc")
            }
        }
    }

    // If you need to configure source sets:
    // sourceSets {
    //     main {
    //         proto {
    //             srcDir("src/main/proto")
    //         }
    //     }
    // }
}