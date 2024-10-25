```markdown
# Bold Test

El objetivo de este proyecto es proporcionar una prueba técnica que demuestre mis conocimientos técnicos en Android Studio con Kotlin.

## Mejoras

- Implementación de Compose para el proyecto.
- Pruebas unitarias para ViewModel.
- Organización de Loadings.
- Organización de errores.
- Ajustes visuales: que los días seleccionados por el usuario tengan una transición para ofrecer más detalle.

## Badges

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

## Arquitectura

- **Arquitectura Limpia**: Este proyecto sigue el principio de Arquitectura Limpia, donde se definen claramente las responsabilidades de cada componente. Esto permite una mejor organización del código, facilitando su mantenimiento y escalabilidad. La arquitectura se compone de las siguientes capas:

  - **Capa de API**: Esta capa se encarga de la comunicación con servicios externos y fuentes de datos. Utiliza bibliotecas como Ktor para realizar solicitudes HTTP y manejar la serialización de datos.

  - **Capa de Repository**: Aquí se implementa la lógica para acceder y gestionar los datos. Esta capa actúa como un intermediario entre la capa de API y la capa de presentación, facilitando la obtención y almacenamiento de datos, y permitiendo la implementación de patrones como el Repository Pattern.

  - **Capa de Presentación**: Esta capa maneja la interfaz de usuario y la lógica de presentación. Utiliza ViewModels y LiveData para interactuar con la UI, garantizando que los datos se presenten de manera reactiva y eficiente.

- **Proyecto Modular**: La aplicación está estructurada en módulos, lo que permite un desarrollo más eficiente y una mejor gestión de dependencias.

## Manejo de Respuestas de API

Para gestionar las respuestas de los endpoints, se ha implementado una clase sellada llamada `ApiResponse`, que permite representar los diferentes estados de una operación de manera segura y estructurada. Esta estrategia incluye:

- **Success**: Representa una respuesta exitosa, conteniendo los datos devueltos por la API.
- **Error**: Maneja las excepciones que puedan ocurrir durante la llamada a la API.
- **Loading**: Indica que la operación está en curso.

Ejemplo de implementación:

```kotlin
sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val exception: Exception) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}
```

Para los errores, se utilizó esta estrategia de clase sellada para capturar tanto los errores de red como los inesperados. Sin embargo, falta la conversión de errores de red a errores de negocio. En un proyecto Android, es importante dar trazabilidad, especialmente en lo que respecta a los errores, para retroalimentar tanto al usuario como al negocio. 

No obstante, no se definieron en la prueba qué tipos de errores de negocio podrían existir, y tampoco se implementaron lógicas de negocio, ya que el enfoque se centró exclusivamente en consumos de red.

<img src="https://github.com/CarlosQuezadaP/bold_test/blob/main/images/Screenshot%202024-10-25%20095501.png" alt="Pantalla Completa" width="300"/>
<img src="https://github.com/CarlosQuezadaP/bold_test/blob/main/images/Screenshot%202024-10-25%20095430.png" alt="Pantalla Completa" width="300"/>

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

<img src="https://github.com/CarlosQuezadaP/bold_test/blob/main/images/fullview.png" alt="Pantalla Completa" width="300"/>
<img src="https://github.com/CarlosQuezadaP/bold_test/blob/main/images/main.png" alt="Pantalla Principal" width="300"/>
<img src="https://github.com/CarlosQuezadaP/bold_test/blob/main/images/scrollview.png" alt="Scroll View" width="300"/>
<img src="https://github.com/CarlosQuezadaP/bold_test/blob/main/images/search.png" alt="Buscar" width="300"/>
<img src="https://github.com/CarlosQuezadaP/bold_test/blob/main/images/splash.png" alt="Splash Screen" width="300"/>


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
```

## Uso

Explicación breve sobre cómo utilizar la aplicación.

## Contribuciones

Indicar cómo las personas pueden contribuir al proyecto.

## Licencia

Detalles sobre la licencia del proyecto.
```

Copia este texto y pégalo en un archivo nuevo, guardándolo como `README.md`. Si necesitas algo más, ¡avísame!
