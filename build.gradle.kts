plugins {
    java
    application
    id("org.javamodularity.moduleplugin") version "1.8.12"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.25.0"
    id ("org.flywaydb.flyway") version "11.9.1"
}

group = "dev.swell"
version = "1.0-SNAPSHOT"

val flywayVersion = "11.9.1"
val junitVersion = "5.10.2"
val lombokVersion = "1.18.38"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("dev.swell.desafiodio")
    mainClass.set("dev.swell.desafiodio.TaskApplication")
}

javafx {
    version = "17.0.15"
    modules = listOf("javafx.controls", "javafx.fxml")
}


flyway {
    url = "jdbc:mariadb://localhost:3306/bookdb"
    user = "root"
    password = "root"
    schemas = arrayOf("bookdb")
    locations = arrayOf("filesystem:src/main/resources/db/migration")
    configurations = arrayOf("compileClasspath")
}


dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")


    implementation("org.hibernate:hibernate-core:7.0.0.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.3")


    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")

    testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
    testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")

    implementation("org.slf4j:slf4j-simple:2.0.13")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    // https://mvnrepository.com/artifact/org.jetbrains/annotations
    implementation("org.jetbrains:annotations:26.0.2")

    implementation("net.datafaker:datafaker:2.4.3")

    implementation("org.flywaydb:flyway-core:${flywayVersion}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jlink {
    imageZip.set(layout.buildDirectory.file("/distributions/app-${javafx.platform.classifier}.zip"))
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    launcher {
        name = "app"
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-mysql:11.9.1")
    }
}

