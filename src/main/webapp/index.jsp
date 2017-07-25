<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
<modelVersion>4.0.0</modelVersion>
<groupId>lectorHTTPS</groupId>
<artifactId>lectorHTTPSTFG</artifactId>
<packaging>war</packaging>
<version>0.0.1-SNAPSHOT</version>
<name>lectorHTTPSTFG Maven Webapp</name>
<url>http://maven.apache.org</url>

<!--Spring library version -->
<properties>
<spring.version>4.2.1.RELEASE</spring.version>
</properties>

<dependencies>

<!-- Spring dependencies -->
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-core</artifactId>
<version>${spring.version}</version>
</dependency>

<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-webmvc</artifactId>
<version>${spring.version}</version>
</dependency>

<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-web</artifactId>
<version>${spring.version}</version>
</dependency>
<!--End Spring dependencies -->

<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>3.8.1</version>
<scope>test</scope>
</dependency>
<dependency>
<groupId>javax.servlet</groupId>
<artifactId>jstl</artifactId>
<version>1.2</version>
</dependency>
</dependencies>
<build>
<finalName>lectorHTTPSTFG</finalName>
</build>
</project>