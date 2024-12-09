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
	implementation ("org.springframework:spring-webmvc")
	implementation ("org.springframework.boot:spring-boot-starter-mail:3.4.0")
	implementation("org.springframework.integration:spring-integration-mail")


	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-impl")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly ("com.mysql:mysql-connector-j")
	//runtimeOnly ("com.microsoft.sqlserver:mssql-jdbc")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("org.springframework:spring-test")

	//new
	testImplementation("com.h2database:h2")//:2.3.230
	testImplementation("org.junit.jupiter:junit-jupiter-api")//:5.8.1


}

tasks.withType<Test> {
	useJUnitPlatform()
}


