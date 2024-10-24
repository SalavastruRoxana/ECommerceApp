plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.ECommerceApp"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jboss.logging:jboss-logging")
	implementation("org.slf4j:slf4j-api")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api")
	implementation("io.jsonwebtoken:jjwt:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-impl")





	compileOnly("org.projectlombok:lombok")
	runtimeOnly ("com.mysql:mysql-connector-j")
	//runtimeOnly ("com.microsoft.sqlserver:mssql-jdbc")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-test")

	//new
	testImplementation("com.h2database:h2:2.3.230")

	//junit 4
	testImplementation("org.junit.vintage:junit-vintage-engine:5.10.1")
	testImplementation("org.hamcrest:hamcrest-core:2.1")



	//old
	testImplementation ("org.testcontainers:testcontainers:1.19.7")
	testImplementation ("org.testcontainers:mysql")
	testImplementation ("org.testcontainers:junit-jupiter")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

