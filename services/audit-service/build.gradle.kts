#!/usr/bin/env kotlin

import org.gradle.kotlin.dsl.annotationProcessor
import org.gradle.kotlin.dsl.compileOnly

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
}



dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    // For JWT generation later
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
//    "developmentOnly"("org.springframework.boot:spring-boot-devtools")

// For test classes
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

// https://mvnrepository.com/artifact/org.elasticsearch/elasticsearch

    implementation ("org.springframework.kafka:spring-kafka")

    implementation("co.elastic.clients:elasticsearch-java:8.13.0")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.elasticsearch.client:elasticsearch-rest-client:8.13.0")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")


    // adding common libs
    implementation(project(":libs:common-dtos"))
    implementation(project(":libs:common-utils"))

}

