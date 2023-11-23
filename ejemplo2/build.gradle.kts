plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ejemplo01"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}