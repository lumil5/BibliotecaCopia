# 📚 Biblioteca — Comandos útiles

> Proyecto Spring Boot 4.1.0 · Java 21 · Thymeleaf · Spring Web MVC

---

## 🚀 Ejecutar la aplicación

```powershell
# Con Maven Wrapper (sin necesidad de Maven instalado)
.\mvnw.cmd spring-boot:run

# Con Maven del sistema
mvn spring-boot:run

# Con recarga en caliente (DevTools activo)
.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```
---

## � Detener la aplicación

```powershell
# Ctrl + C
```

---

## 📦 Compilar y empaquetar

```powershell
# Compilar el proyecto
.\mvnw.cmd compile

# Empaquetar en JAR (con tests)
.\mvnw.cmd package

# Empaquetar en JAR (sin ejecutar tests)
.\mvnw.cmd package -DskipTests

# Ejecutar el JAR generado
java -jar target\Biblioteca-1.0.0.jar
```

---

## 🧪 Tests

```powershell
# Ejecutar todos los tests
.\mvnw.cmd test

# Ejecutar un test específico
.\mvnw.cmd test -Dtest=NombreDeLaClaseTest

# Ejecutar tests y generar reporte de cobertura (si tienes JaCoCo)
.\mvnw.cmd test jacoco:report

# Saltarse los tests al compilar
.\mvnw.cmd compile -DskipTests
```

---

## 🧹 Limpieza

```powershell
# Limpiar la carpeta target/
.\mvnw.cmd clean

# Limpiar y recompilar desde cero
.\mvnw.cmd clean compile

# Limpiar y empaquetar
.\mvnw.cmd clean package -DskipTests

# Limpiar y ejecutar
.\mvnw.cmd clean spring-boot:run
```

---

## 🔍 Inspección del proyecto

```powershell
# Ver árbol de dependencias
.\mvnw.cmd dependency:tree

# Ver dependencias desactualizadas
.\mvnw.cmd versions:display-dependency-updates

# Validar el pom.xml
.\mvnw.cmd validate

# Ver información efectiva del POM
.\mvnw.cmd help:effective-pom
```

---

## ⚙️ Perfiles y configuración

```powershell
# Ejecutar con un perfil específico (ej: dev, prod)
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev

# Sobrescribir propiedades desde la línea de comandos
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"

# Cambiar el puerto del servidor
java -jar target\Biblioteca-1.0.0.jar --server.port=9090
```

---

## 📊 Verificación y salud de la app

Una vez que la aplicación esté corriendo en **http://localhost:8080**:

| Endpoint | URL |
|---|---|
| Aplicación principal | http://localhost:8080 |
| Actuator (si está activo) | http://localhost:8080/actuator |
| Health check | http://localhost:8080/actuator/health |
| Info | http://localhost:8080/actuator/info |

---

## 🛠️ Comandos de desarrollo útiles

```powershell
# Ver versión de Java instalada
java -version

# Ver versión de Maven
.\mvnw.cmd --version

# Instalar dependencias sin compilar
.\mvnw.cmd dependency:resolve

# Descargar código fuente de dependencias
.\mvnw.cmd dependency:sources

# Generar el sitio del proyecto con reportes
.\mvnw.cmd site
```

---

## 📂 Estructura del proyecto

```
Biblioteca/
├── src/
│   ├── main/
│   │   ├── java/          ← Código fuente Java
│   │   └── resources/
│   │       ├── templates/ ← Plantillas Thymeleaf (.html)
│   │       ├── static/    ← CSS, JS, imágenes
│   │       └── application.properties ← Configuración
│   └── test/
│       └── java/          ← Tests unitarios e integración
├── target/                ← Archivos compilados y JAR
├── pom.xml                ← Configuración Maven
├── mvnw / mvnw.cmd        ← Maven Wrapper
└── COMANDOS.md            ← Este archivo
```

---

## 💡 Propiedades útiles en `application.properties`

```properties
# Puerto del servidor (por defecto: 8080)
server.port=8080

# Nivel de log
logging.level.org.springframework=INFO
logging.level.org.biblioteca=DEBUG

# Mostrar SQL (si usas JPA más adelante)
spring.jpa.show-sql=true

# Thymeleaf - desactivar caché en desarrollo
spring.thymeleaf.cache=false

# DevTools - reinicio automático
spring.devtools.restart.enabled=true
```

---

*Generado para el proyecto **Biblioteca** · Spring Boot 4.1.0 · Java 21*
