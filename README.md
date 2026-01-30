# 📱 App "Actividad Física y Sensores"

Aplicación Android desarrollada en Kotlin para el registro de sesiones de actividad física con uso de sensores del dispositivo.

## 📋 Descripción del Proyecto

Esta aplicación permite a los usuarios:
- ✅ Registrar sesiones de actividad física con nombre y duración
- 📊 Visualizar un historial de actividades en formato lista
- 🏃 Detectar movimiento en tiempo real usando el acelerómetro
- 🎨 Interfaz moderna con Material Design

## 🛠️ Características Técnicas

### 1. Pantalla Principal: "Registro de Actividad"

**Componentes implementados:**
- Campo de texto para nombre de actividad
- Campo numérico para duración en minutos
- Validación de datos (texto y números)
- RecyclerView con adapter personalizado
- Modelo de datos `ActivitySession`
- ViewHolder pattern
- DiffUtil para optimización

**Funcionalidades:**
- Validación de campos vacíos
- Validación de tipo de dato numérico
- Guardado de actividades con fecha/hora actual
- Clasificación automática del tipo de actividad (Cardio, Fuerza, Flexibilidad, Otro)
- Lista actualizada dinámicamente

### 2. Segunda Pantalla: "Sesión en Tiempo Real"

**Componentes implementados:**
- SensorManager para acelerómetro
- SensorEventListener
- Detección de tres niveles de movimiento:
  - 🔵 Sin movimiento
  - 🟢 Movimiento suave
  - 🔴 Movimiento intenso
- Cambio de color de fondo según actividad
- Botón para finalizar sesión

**Algoritmo de detección:**
- Cálculo de diferencias entre lecturas del acelerómetro
- Magnitud del vector de movimiento
- Umbrales configurables
- Actualización optimizada (100ms)

## 📁 Estructura del Proyecto

```
com.example.actividadfisicaapp/
│
├── model/
│   └── ActivitySession.kt          # Modelo de datos
│
├── adapter/
│   ├── ActivityAdapter.kt          # Adapter del RecyclerView
│   └── ActivityViewHolder.kt       # ViewHolder
│
├── MainActivity.kt                  # Actividad principal
└── SesionTiempoRealActivity.kt     # Actividad de sensor
```

## 🎯 Requisitos Cumplidos

### ✅ RecyclerView + Adapter + ViewHolder
- Implementación completa con patrón ViewHolder
- Uso de ListAdapter con DiffUtil
- Método `submitList()` para actualizaciones eficientes

### ✅ Modelos de Datos Personalizados
- Clase `ActivitySession` con:
  - Propiedades: nombre, duración, fechaHora
  - Métodos auxiliares: `getFechaFormateada()`, `getTipoActividad()`
  - Data class de Kotlin para inmutabilidad

### ✅ Intents con Extras
- Navegación entre MainActivity y SesionTiempoRealActivity
- Intent explícito configurado

### ✅ Navegación entre Activities
- Navegación bidireccional
- Configuración de actividad padre en Manifest
- Action Bar con botón "Atrás"

### ✅ Lectura de Sensores (Acelerómetro)
- Registro/desregistro correcto en onResume/onPause
- Procesamiento de datos del sensor
- Cálculo de magnitud de movimiento
- Clasificación en 3 niveles

### ✅ Toasts y Actualizaciones de UI
- Toasts para validaciones
- Toast de confirmación al guardar
- Actualizaciones en tiempo real del sensor
- Cambios de color dinámicos

### ✅ Código Limpio y Comentado
- Documentación KDoc en todas las clases y métodos
- Comentarios explicativos
- Nombres descriptivos
- Organización en paquetes
- Separación de responsabilidades

## 🚀 Cómo Ejecutar el Proyecto

### Requisitos previos:
- Android Studio Arctic Fox o superior
- SDK mínimo: Android 7.0 (API 24)
- SDK objetivo: Android 14 (API 34)
- Kotlin 1.8+

### Pasos de instalación:

1. **Crear nuevo proyecto en Android Studio**
   - File → New → New Project
   - Seleccionar "Empty Activity"
   - Nombre: ActividadFisicaApp
   - Package: com.example.actividadfisicaapp
   - Language: Kotlin
   - Minimum SDK: API 24

2. **Copiar los archivos del proyecto**

   **Archivos Kotlin (en src/main/java/com/example/actividadfisicaapp/):**
   - `model/ActivitySession.kt`
   - `adapter/ActivityAdapter.kt`
   - `adapter/ActivityViewHolder.kt`
   - `MainActivity.kt`
   - `SesionTiempoRealActivity.kt`

   **Layouts (en src/main/res/layout/):**
   - `activity_main.xml`
   - `activity_sesion_tiempo_real.xml`
   - `item_activity_session.xml`

   **Recursos (en src/main/res/values/):**
   - `strings.xml`

   **Configuración:**
   - `AndroidManifest.xml` (en src/main/)
   - `build.gradle` (Module: app)

3. **Sincronizar Gradle**
   - Click en "Sync Now" cuando aparezca la notificación
   - Esperar a que se descarguen las dependencias

4. **Ejecutar la aplicación**
   - Conectar dispositivo Android o iniciar emulador
   - Click en el botón "Run" (▶️)
   - Seleccionar dispositivo

## 📱 Uso de la Aplicación

### Registrar una actividad:
1. Escribe el nombre de la actividad (ej: "Correr", "Yoga")
2. Introduce la duración en minutos
3. Pulsa "Guardar"
4. La actividad aparecerá en el historial

### Sesión en tiempo real:
1. Pulsa el botón "Sesión Real"
2. Mueve el dispositivo para ver cambios en la detección
3. Observa los cambios de color y texto
4. Pulsa "Finalizar Sesión" para volver

## 🎨 Características de Diseño

- **Material Design 3**: Componentes modernos y estéticos
- **Cards elevadas**: Para mejor visualización
- **Colores semánticos**: Verde (guardado), Azul (información), Rojo (intenso)
- **Iconos**: Visualización intuitiva
- **Responsive**: Adaptable a diferentes tamaños de pantalla

## 📊 Clasificación de Actividades

El sistema clasifica automáticamente las actividades en:

| Tipo | Actividades |
|------|-------------|
| **Cardio** | Correr, Caminar, Nadar, Ciclismo |
| **Flexibilidad** | Yoga, Pilates, Estiramientos |
| **Fuerza** | Gimnasio, Pesas, Musculación |
| **Otro** | Cualquier otra actividad |

## 🔧 Configuración del Sensor

**Umbrales de movimiento:**
- Sin movimiento: < 2.0 unidades
- Movimiento suave: 2.0 - 15.0 unidades
- Movimiento intenso: > 15.0 unidades

**Optimizaciones:**
- Intervalo de actualización: 100ms
- Registro/desregistro automático para ahorro de batería
- Manejo de dispositivos sin acelerómetro

## 🐛 Solución de Problemas

**Problema:** El sensor no detecta movimiento
- **Solución:** Verifica que el dispositivo tenga acelerómetro
- Prueba en un dispositivo físico (no todos los emuladores simulan sensores)

**Problema:** Error al compilar
- **Solución:** Verifica las versiones en build.gradle
- Sync Gradle y Clean Project

**Problema:** Lista no se actualiza
- **Solución:** Verifica que estés usando `submitList()` con una nueva lista
- Asegúrate de llamar a `toList()` para crear una copia

## 📝 Notas del Desarrollador

Este proyecto fue desarrollado como práctica de 2º DAM, cumpliendo con todos los requisitos del enunciado:

✅ RecyclerView funcional con adapter personalizado
✅ Validación de datos
✅ Navegación entre activities
✅ Uso de sensores
✅ Código limpio y documentado
✅ Material Design moderno

## 👨‍🎓 Autor

Proyecto realizado por Marcin Ladkiewicz
Módulo: Programación Multimedia y Dispositivos Móviles (PMDM)

---

**Versión:** 1.0  
**Fecha:** Enero 2026  
**Licencia:** Educativa
