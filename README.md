# API Collections — Proyecto del curso

Este proyecto es la **base** del curso de colecciones de Java. La idea es siempre la misma:
generamos datos de prueba, los metemos en distintas estructuras (`ArrayList`, `LinkedList`,
`HashSet`, `TreeSet`, `HashMap`…) y **medimos cuánto tarda cada una**. Así aprendemos a
elegir la estructura correcta según el problema, en lugar de usar `ArrayList` para todo.

> Este README está pensado para que cualquiera pueda arrancar, incluso si es nuevo en Java.
> Si algo no compila, salta directo a la sección **Solución de problemas** al final.

---

## Requisitos

- **Java 25** (JDK, no solo el JRE).
- **Maven 3.9+**.
- Conexión a internet la primera vez (Maven descarga las librerías).
- Un IDE como IntelliJ IDEA (recomendado) o VS Code.

Para verificar que tu Maven usa el Java correcto:

```bash
mvn -version
```

En la salida, la línea `Java version:` debe decir **25**. Si dice otra, ajusta tu
`JAVA_HOME` al JDK 25.

---

## Cómo está organizado el proyecto

Maven divide el código en dos mundos, y **esto es importante entenderlo**:

```
src/
├── main/java/...   → el código "de producción": tus objetos (DTOs)
└── test/java/...   → el código de pruebas: tests, factory, utilidades
```

| Carpeta | Qué va aquí | Ejemplos |
|---|---|---|
| `src/main/java` | Los objetos del dominio | `Payment`, `MutablePayment`, `Shipment` y sus enums |
| `src/test/java` | Todo lo relacionado con pruebas | `DataFactory`, `AbstractCollectionTest`, `StopWatch`, y las clases de test |

### Regla de oro: Datafaker solo existe en `src/test`

En el `pom.xml`, la librería **Datafaker** (la que genera datos falsos) está marcada con
`<scope>test</scope>`. Eso significa: **solo se puede usar dentro de `src/test/java`**.

Por eso el `DataFactory` (que usa Datafaker) **debe** estar en `src/test`. Si lo pones por
error en `src/main`, verás el error `Package not found: net.datafaker` aunque el `import`
esté bien escrito. No es un bug: es la regla del *scope*.

---

## Cómo correr los tests

### Opción 1 — Terminal (recomendada, muestra los tiempos en árbol)

Desde la carpeta raíz del proyecto (donde está el `pom.xml`):

```bash
mvn test
```

Esto corre **todos** los tests. Verás una salida en forma de árbol, con una palomita ✔ y el
**tiempo de cada test** al lado:

```
ArrayList vs LinkedList: insert in the middle
└─ insert 2000 elements in the middle of a 2000-element list ✔ 42 ms
```

Para correr **una sola clase** de test:

```bash
mvn test -Dtest=MiddleInsertionTest
```

### Opción 2 — IntelliJ (rápida para el día a día)

Abre la clase de test y haz clic en la **flecha verde ▶** que aparece junto a la clase o al
método. Los resultados salen en el panel de abajo.

> El árbol bonito con el formato UNICODE **solo aparece con `mvn test` en la terminal**. El
> runner de IntelliJ muestra los resultados en su propia ventana. Ambos corren los mismos
> tests; solo cambia cómo se ve.

---

## Dónde ver el tiempo de ejecución

Hay **dos tiempos distintos** y conviene no confundirlos:

1. **El tiempo del test completo.** Lo da Maven automáticamente (el `42 ms` del árbol, o la
   línea `Time elapsed: 0.042 s`). Incluye **todo**: generar los datos con Datafaker **más**
   la operación que estás midiendo.

2. **El tiempo de una operación específica.** Cuando queremos comparar dos estructuras en el
   mismo test (por ejemplo `ArrayList` vs `LinkedList`), el tiempo del test completo no
   sirve, porque mezcla las dos operaciones y la generación de datos. Para eso usamos nuestro
   **`StopWatch`**.

---

## El `StopWatch`: cómo medimos una operación

`StopWatch` es una pequeña utilidad nuestra. Por dentro usa `System.nanoTime()`, que es un
cronómetro de alta precisión de Java:

```java
public static long measure(Runnable operation) {
    long start = System.nanoTime();   // marca el inicio
    operation.run();                  // ejecuta la operación
    return (System.nanoTime() - start) / 1_000_000;  // fin - inicio, en milisegundos
}
```

Usamos `nanoTime()` y **no** `currentTimeMillis()` porque el segundo es un "reloj de pared"
que puede retroceder (cuando el sistema ajusta la hora) y tiene menos precisión.
`nanoTime()` solo avanza y es ideal para medir cuánto dura algo.

En tus tests lo usas a través de los helpers de `AbstractCollectionTest`:

```java
time("ArrayList middle insert", () -> {
    // ... la operación que quieres medir ...
});
```

Eso mide **solo ese bloque** y lo imprime con su etiqueta.

---

## ¿Por qué `Runnable`? (para quienes son nuevos)

Esta es la parte que más confunde al principio, así que vamos con calma.

Fíjate que a `measure` le pasamos **código como si fuera un dato**:

```java
time("ArrayList middle insert", () -> {
    arrayList.add(arrayList.size() / 2, sample);
});
```

Ese `() -> { ... }` es una **lambda**, y representa un `Runnable`.

**¿Qué es un `Runnable`?** Es simplemente **"un bloque de código que no recibe nada y no
devuelve nada, y que se puede ejecutar más tarde"**. Piénsalo como una tarea guardada en una
cajita: no se ejecuta cuando la escribes, sino cuando alguien llama a su método `.run()`.

**¿Por qué lo necesitamos aquí?** Porque el `StopWatch` tiene que hacer tres cosas **en este
orden**:

1. Marcar el tiempo de inicio.
2. Ejecutar *tu* operación.
3. Marcar el tiempo de fin.

Para que el `StopWatch` pueda "meter tu operación en medio" de su cronómetro, necesita
**recibir esa operación sin ejecutarla todavía**. Si le pasáramos el resultado ya ejecutado,
llegaría tarde: la operación ya habría ocurrido fuera del cronómetro.

El `Runnable` resuelve justo eso: le entregas la operación **empaquetada**, y el `StopWatch`
decide *cuándo* ejecutarla (con `operation.run()`), rodeándola con las dos marcas de tiempo.

Una analogía: es como darle a alguien una **receta** (el `Runnable`) en lugar del **plato ya
cocinado**. Con la receta, esa persona puede poner un cronómetro, cocinar, y medir cuánto
tardó. Si le das el plato hecho, ya no puede medir nada.

Resumen:
- Lambda `() -> { ... }` = una forma corta de crear un `Runnable`.
- `Runnable` = un bloque de código ejecutable, guardado para después.
- Se ejecuta cuando alguien llama a `.run()` — en nuestro caso, dentro del cronómetro.

---

## Ejemplo completo: `ArrayList` vs `LinkedList` insertando en medio

```java
@Test
@DisplayName("insert 2000 elements in the middle of a 2000-element list")
void insertInMiddle() {
    List<Payment> data = payments(SIZE);       // datos generados por el factory
    Payment sample = data.get(0);

    List<Payment> arrayList = new ArrayList<>(data);
    List<Payment> linkedList = new LinkedList<>(data);

    time("ArrayList middle insert", () -> {
        for (int i = 0; i < INSERTIONS; i++) {
            arrayList.add(arrayList.size() / 2, sample);
        }
    });

    time("LinkedList middle insert", () -> {
        for (int i = 0; i < INSERTIONS; i++) {
            linkedList.add(linkedList.size() / 2, sample);
        }
    });

    assertEquals(SIZE + INSERTIONS, arrayList.size());
    assertEquals(SIZE + INSERTIONS, linkedList.size());
}
```

Al correrlo verás algo como:

```
ArrayList middle insert            18 ms
LinkedList middle insert           23 ms
```

**La lección:** mucha gente cree que `LinkedList` gana al insertar en medio porque "insertar
en un nodo es O(1)". Pero para insertar en la posición del medio, `LinkedList` primero tiene
que **recorrer** hasta ahí nodo por nodo (O(n)), y ese recorrido se come la ventaja. Por eso
`ArrayList` suele quedar igual o mejor. Ese resultado "contra la intuición" es justo lo que
queremos que entiendas.

---

## Notas honestas sobre las mediciones

- El `StopWatch` mide **una sola pasada, sin calentamiento**. Los números **bailan** entre
  corridas porque la JVM optimiza el código sobre la marcha (el famoso JIT). Para el curso
  está perfecto: la **diferencia** entre estructuras se ve clara y es real.
- Esto es una **demostración didáctica**, no un *benchmark* riguroso. Para mediciones serias
  existe una herramienta llamada JMH; la mencionaremos más adelante como tema avanzado.
- Corre cada test **un par de veces** y fíjate en la tendencia, no en el número exacto.

---

## Solución de problemas

### `Package not found: net.datafaker`
El archivo que usa Datafaker está en `src/main` en lugar de `src/test`. Datafaker tiene
`<scope>test</scope>`, así que solo existe en `src/test`. **Mueve el archivo a
`src/test/java/...`** (el `package` se queda igual; solo cambia la carpeta).

### `Cannot resolve symbol 'Faker'`
Dos posibles causas, en orden de probabilidad:
1. El archivo está en `src/main` (ver el punto anterior).
2. Falta el import: `import net.datafaker.Faker;` (con **`net`**, no `com.github.javafaker`).
3. La librería no se ha descargado. Corre `mvn -U dependency:resolve` y recarga Maven en el
   IDE.

### Maven no descarga las dependencias
Desde la raíz del proyecto:
```bash
mvn -U dependency:resolve
```
Si dice `BUILD SUCCESS`, ya bajaron; recarga el proyecto Maven en el IDE (icono de las
flechas en círculo). Si falla, revisa tu conexión a internet o si estás detrás de un proxy.

### El IDE marca rojo pero `mvn test` funciona
Recarga el proyecto Maven en el IDE: panel de Maven → **Reload All Maven Projects** (las
flechitas en círculo). El IDE a veces se desincroniza; Maven es la fuente de verdad.

### `invalid target release: 25` (o similar al compilar)
Tu Maven está compilando con un Java más viejo. Verifica con `mvn -version` que la
`Java version` sea 25 y ajusta `JAVA_HOME` si no lo es.

### No veo el árbol bonito de tiempos
El formato en árbol solo sale con **`mvn test` en la terminal**, no con el runner del IDE.
Si aun así no aparece, revisa que en el `pom.xml` el `maven-surefire-plugin` tenga
configurado el *tree reporter*.

---

## Resumen de comandos

```bash
mvn test                          # corre todos los tests
mvn test -Dtest=NombreDelTest     # corre una sola clase de test
mvn -U dependency:resolve         # fuerza la descarga de librerías
mvn -version                      # verifica la versión de Java que usa Maven
```README.md