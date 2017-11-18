# Call center
## Ejecución de los tests
- Instalar maven
- Ejecutar el comando 
```bash
mvn test
```
- El programa imprimirá el estado de los empleados durante la corrida del Test principal con 10 llamadas concurrentes.

## Desiciones tomadas
- El sistema funciona para N empleados y M llamados concurrentes.
- La clase Dispatcher tiene una PriorityQueue donde se encuentran los empleados disponibles, ordenados por prioridad según su rol.
- La clase Dispatcher tiene una Queue donde se encuentran los llamados pendientes. Esto es para poner los nuevos llamados en espera si no hay ningún empleado disponible.

## Diagrama de clases (UML)
![alt UML](https://github.com/jorexe/callcenter-test/raw/master/uml.png)
