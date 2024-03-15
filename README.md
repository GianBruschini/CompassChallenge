**Conexa Challenge**

El challenge es un proyecto que emplea una arquitectura moderna basada en MVVM con Flow y Coroutines. Se ha implementado utilizando Clean Architecture, aprovechando diferentes patrones como el de repositorio, DataSources y Use Cases. Este proyecto proporciona un ejemplo práctico de cómo desarrollar una aplicación Android siguiendo las mejores prácticas y utilizando las últimas tecnologías disponibles.


**Bibliotecas Utilizadas**

- Retrofit & Gson: Para realizar llamadas a la API y convertir los datos JSON a objetos Kotlin.
- Glide: Para cargar y mostrar imágenes de manera eficiente.
- Coroutines: Para el manejo asíncrono y concurrente de tareas.
- Fragment & Activity: Proporciona extensiones Kotlin para simplificar la interacción con Fragmentos y Actividades.
- Lifecycle: Proporciona componentes del ciclo de vida de las aplicaciones.
- Navigation Components: Para gestionar la navegación entre fragmentos.
- Google Maps: Para integrar mapas de Google en la aplicación.
- Dagger Hilt: Para la inyección de dependencias.
- WorkManager: Para programar tareas asíncronas y en segundo plano.

- 
**Estructura del Proyecto**
El proyecto sigue una arquitectura MVVM (Modelo-Vista-ViewModel) con Clean Architecture, lo que significa que está organizado en capas claras y separadas para facilitar el mantenimiento y la escalabilidad.

app: Contiene la implementación de la capa de presentación de la aplicación, incluyendo Actividades, Fragmentos, Adaptadores y ViewModels.
data: Aquí se encuentra la implementación de las capas de datos, incluyendo Repositorios, Fuentes de datos remotos y locales, y modelos de datos.
domain: Contiene la lógica de negocio y los casos de uso de la aplicación.
di: Configuración de la inyección de dependencias utilizando Dagger Hilt.
utils: Utilidades y clases de ayuda varias.

**Pruebas**
Se incluyen pruebas unitarias y de integración para garantizar el buen funcionamiento de la aplicación.
Unit Tests: Pruebas unitarias para lógica de negocio, casos de uso y modelos de datos.
