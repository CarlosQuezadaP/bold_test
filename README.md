��#   b o l d _ t e s t 
 
 # Nombre del Proyecto

Descripción breve del proyecto, su propósito y funcionalidades principales.

## Badges

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

## Arquitectura

- **Arquitectura Limpia**: Este proyecto sigue el principio de Arquitectura Limpia, donde se definen claramente las responsabilidades de cada componente. Esto permite una mejor organización del código, facilitando su mantenimiento y escalabilidad. La arquitectura se compone de las siguientes capas:

  - **Capa de API**: Esta capa se encarga de la comunicación con servicios externos y fuentes de datos. Utiliza bibliotecas como Ktor para realizar solicitudes HTTP y manejar la serialización de datos.

  - **Capa de Repository**: Aquí se implementa la lógica para acceder y gestionar los datos. Esta capa actúa como un intermediario entre la capa de API y la capa de presentación, facilitando la obtención y almacenamiento de datos, y permitiendo la implementación de patrones como el Repository Pattern.

  - **Capa de Presentación**: Esta capa maneja la interfaz de usuario y la lógica de presentación. Utiliza ViewModels y LiveData para interactuar con la UI, garantizando que los datos se presenten de manera reactiva y eficiente.

- **Proyecto Modular**: La aplicación está estructurada en módulos, lo que permite un desarrollo más eficiente y una mejor gestión de dependencias.

## Características

- Listar las características más importantes del proyecto.

## Librerías Usadas

### Dependencias de la Aplicación

- **Ciclo de Vida y Databinding**
  - `androidx.lifecycle.runtime.ktx`
  - `androidx.databinding.runtime`
  - `androidx.lifecycle.viewmodel.ktx`
  - `androidx.lifecycle.livedata.ktx`

- **Componentes de Android**
  - `androidx.core.ktx`
  - `androidx.appcompat`
  - `androidx.activity`
  - `androidx.constraintlayout`
  - `androidx.swiperefreshlayout`
  - `com.google.android.material:material`

- **Red**
  - **Ktor**
    - `ktor.client.core`
    - `ktor.client.cio`
    - `ktor.serialization.kotlinx.json`
    - `ktor.client.serialization`
    - `ktor.client.logging`
    - `ktor.client.content.negotiation`

### Inyección de Dependencias

- **Koin**
  - `koin.core`
  - `koin.android`

### Imágenes

- **Glide**
  - `glide`
  - `kapt.compiler`

### Testing

- **Testing de Unidad**
  - `kotlinx.coroutines.test`
  - `mockk`
  - `junit`

- **Testing en Android**
  - `androidx.junit`
  - `androidx.espresso.core`

## Pruebas Unitarias

Se realizaron pruebas unitarias para asegurar la calidad del código en las siguientes capas:

- **Capa de API**: Se implementaron pruebas para verificar que las solicitudes a los servicios externos funcionen correctamente y que se manejen adecuadamente las respuestas y errores.

- **Capa de Repository**: Se llevaron a cabo pruebas para comprobar la lógica de acceso y gestión de datos, asegurando que los datos se obtengan y almacenen correctamente desde y hacia las fuentes de datos.

Estas pruebas contribuyen a la robustez y confiabilidad del proyecto, permitiendo identificar y corregir errores de manera temprana.

## Capturas de Pantalla

![Descripción de la imagen](ruta/a/tu/imagen.png)

## Tecnologías Usadas

- Kotlin
- Android Studio
- [Ktor](https://ktor.io/)
- [Koin](https://insert-koin.io/)
- [Glide](https://bumptech.github.io/glide/)

## Instalación

Instrucciones para clonar el repositorio y compilar el proyecto.

```bash
git clone https://github.com/CarlosQuezadaP/bold_test.git
cd bold_test
