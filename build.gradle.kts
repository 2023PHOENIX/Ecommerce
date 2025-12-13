plugins {
    id("org.springframework.boot") version "4.0.0" apply false
    id("io.spring.dependency-management") version "1.1.5" apply false
    id("com.google.protobuf") version "0.9.5" apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.google.protobuf")
//    if (name.endsWith("-service")) { // Adjust condition as needed
//        apply(plugin = "org.springframework.boot")
//    }

    group = "com.ecommerce"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}
