# 🎨 Guía de Instalación y Uso de Tailwind CSS con pnpm

> **Proyecto:** Biblioteca (Spring Boot 4.x / Java 21 / Thymeleaf / Maven)  
> **Gestor de paquetes:** `pnpm`  
> **Motor CSS:** Tailwind CSS v4 (Compilación local, 100% Offline, sin sobrecarga de rendimiento)

---

## 📋 Índice
1. [Prerrequisitos](#-prerrequisitos)
2. [Paso a Paso de Instalación](#-paso-a-paso-de-instalación)
3. [Configuración de Archivos](#-configuración-de-archivos)
4. [Flujo de Trabajo Diario](#-flujo-de-trabajo-diario)
5. [Preguntas Frecuentes y Rendimiento](#-preguntas-frecuentes-y-rendimiento)

---

## 📌 Prerrequisitos

Tener instalados en tu sistema:
- **Node.js** (v18 o superior recomendada): `node -v`
- **pnpm**: `pnpm -v` *(si no lo tienes instalado: `npm install -g pnpm`)*

---

## 🚀 Paso a Paso de Instalación

Ejecuta los siguientes comandos en la **raíz del proyecto** (`Biblioteca/`):

### 1. Inicializar el proyecto con pnpm
```powershell
pnpm init
```
*Esto generará el archivo `package.json` en la raíz.*

---

### 2. Instalar Tailwind CSS, su CLI y daisyUI
```powershell
pnpm add -D tailwindcss @tailwindcss/cli daisyui@latest
```

---

### 3. Crear el archivo CSS de entrada (`input.css`)

1. Asegúrate de tener creada la carpeta `css` dentro de los recursos estáticos:
   ```powershell
   New-Item -ItemType Directory -Force -Path "src/main/resources/static/css"
   ```

2. Crea el archivo `src/main/resources/static/css/input.css` con el siguiente contenido:

```css
@import "tailwindcss";
@plugin "daisyui";

/* Escanear todas las plantillas HTML de Thymeleaf */
@source "../../templates/**/*.html";
```

---

### 4. Configurar los scripts en `package.json`

Abre o edita tu archivo `package.json` en la raíz para que contenga los scripts de compilación:

```json
{
  "name": "biblioteca",
  "version": "1.0.0",
  "description": "Gestion de Biblioteca con Spring Boot y Tailwind CSS",
  "scripts": {
    "dev": "tailwindcss -i ./src/main/resources/static/css/input.css -o ./src/main/resources/static/css/tailwind.css --watch",
    "build": "tailwindcss -i ./src/main/resources/static/css/input.css -o ./src/main/resources/static/css/tailwind.css --minify"
  },
  "devDependencies": {
    "@tailwindcss/cli": "^4.0.0",
    "tailwindcss": "^4.0.0"
  }
}
```

---

### 5. Actualizar `.gitignore`

Añade las carpetas generadas por Node y pnpm a tu archivo `.gitignore`:

```gitignore
# Node / pnpm
node_modules/
```

---

### 6. Enlazar el CSS generado en tus vistas Thymeleaf

En tus archivos `.html` (dentro de `src/main/resources/templates/`), enlaza el archivo `tailwind.css` generado:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca</title>
    <!-- Enlace al archivo compilado de Tailwind -->
    <link rel="stylesheet" th:href="@{/css/tailwind.css}">
</head>
<body class="bg-slate-900 text-slate-100 min-h-screen flex items-center justify-center">
    <div class="p-8 bg-slate-800 rounded-xl shadow-lg border border-slate-700 text-center">
        <h1 class="text-3xl font-bold text-indigo-400">📚 Gestión de Biblioteca</h1>
        <p class="mt-2 text-slate-400">Tailwind CSS funcionando correctamente con Thymeleaf.</p>
    </div>
</body>
</html>
```

---

## 🛠️ Flujo de Trabajo Diario

Durante el desarrollo, utiliza **dos terminales**:

### Terminal 1: Compilador de estilos (Tailwind)
```powershell
pnpm run dev
```
*Se queda escuchando en segundo plano. Cada vez que guardes cambios en tus archivos `.html`, regenerará `tailwind.css` en milisegundos.*

### Terminal 2: Servidor Spring Boot
```powershell
.\mvnw.cmd spring-boot:run
```

---

### Para desplegar o compilar para Producción
Antes de empaquetar el JAR, genera el CSS minificado al máximo:
```powershell
pnpm run build
.\mvnw.cmd package -DskipTests
```

---

## ❓ Preguntas Frecuentes y Rendimiento

### ¿Genera cuellos de botella o archivos pesados?
* **No.** Tailwind realiza *Tree-shaking* automático. Solo compila las clases que escribes en tus vistas HTML.
* El archivo final suele pesar entre **10 KB y 25 KB** (a diferencia de frameworks tradicionales de más de 250 KB).
* Spring Boot sirve el archivo como un recurso estático puro, con **0% de impacto** en el rendimiento de Java.

### ¿Requiere conexión a internet para funcionar?
* **No.** Funciona **100% Offline**.
* Solo necesitas internet la primera vez que ejecutas `pnpm add` para descargar los paquetes a tu computadora.
* Tanto la compilación (`pnpm run dev`) como el renderizado en el navegador se ejecutan completamente en tu máquina local.

### Buena práctica con Thymeleaf:
Escribe siempre los nombres de clase completos en tus condicionales para que el escáner de Tailwind los detecte:
* ✅ **Correcto:** `th:class="${activo} ? 'text-blue-600' : 'text-gray-400'"`
* ❌ **Evitar:** `th:class="'text-' + ${color} + '-600'"`
