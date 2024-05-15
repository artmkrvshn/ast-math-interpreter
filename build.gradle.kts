plugins {
    application
    kotlin("jvm") version "1.9.23"
}

application {
    mainClass.set("MainKt")
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

group = "com.interpreter"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}