# Impresiones al escribir la App

Durante el desarrollo de la aplicación, en algunos momentos tuve la sensación de que ciertas partes del código podrían haberse escrito de manera más apropiada si se hubiera aplicado algún patrón de diseño que no consideré en un principio. Esto me llevó a reflexionar sobre la importancia de explorar diferentes enfoques y patrones para escribir un código más eficiente y mantenible.

## Uso de iteradores

En algunas partes del código, como al asignar tareas aleatorias a los jugadores, fue necesario emplear iteradores para recorrer y manipular colecciones de objetos. Esto se debió a la necesidad de iterar sobre la lista de tareas disponibles y verificar si un jugador podía realizar una tarea en particular.

## Sobrescritura de hashCode/equals

No fue necesario sobrescribir los métodos `hashCode()` y `equals()` en esta aplicación, ya que no se realizaron comparaciones de objetos basadas en su contenido. La identificación de los jugadores se realizó mediante el alias único asignado a cada uno, por lo que no fue necesario implementar una comparación basada en igualdad de contenido.

## Uso de Comparable/Comparator

No se utilizó la interfaz `Comparable` ni `Comparator` en esta aplicación, ya que no fue necesario comparar o ordenar objetos en ningún punto del código. La lógica del juego no requería la comparación de jugadores o tareas según algún criterio específico.

## Mejoras aportadas

- Implementación del menú de configuración y opciones de juego para proporcionar una experiencia más interactiva al usuario.
- Refactorización del código para mejorar la legibilidad y la estructura del programa.
- Incorporación de comentarios detallados para facilitar la comprensión del código y su mantenimiento futuro.

## Otras observaciones

Durante el proceso de desarrollo, fue fundamental realizar pruebas exhaustivas para garantizar el correcto funcionamiento de la aplicación y la detección temprana de posibles errores. Además, la colaboración y el intercambio de ideas con otros desarrolladores resultaron muy útiles para identificar áreas de mejora y aplicar soluciones efectivas.
