# API Collections — Proyecto del curso

Este proyecto es la **base** del curso de colecciones de Java. La idea es siempre la misma:
generamos datos de prueba, los metemos en distintas estructuras (`ArrayList`, `LinkedList`,
`HashSet`, `TreeSet`, `HashMap`…) y **medimos cuánto tarda cada una**. Así aprendemos a
elegir la estructura correcta según el problema, en lugar de usar `ArrayList` para todo.

> Este README está pensado para que cualquiera pueda arrancar, incluso si es nuevo en Java
> o en Maven. Si algo no compila, salta directo a la sección **Solución de problemas** al final.

---

## Versión recomendada

**La versión recomendada para este curso es Java 25** (ya vamos por la versión 25 del
lenguaje) junto con **Maven 3.9 o superior**. Este proyecto ya viene configurado así en el
`pom.xml`:

```xml
<properties>
    <maven.compiler.release>25</maven.compiler.release>
</properties>
```

No es obligatorio usar exactamente Java 25 para seguir el curso — los conceptos de
colecciones aplican igual en Java 11, 17 o 21 — pero **todo el material, capturas de pantalla
y ejemplos del curso están hechos con Java 25**. Si usas otra versión, es tu responsabilidad
ajustar el `pom.xml` como se explica más abajo en
[Cambiar la versión de Java del proyecto](#cambiar-la-versión-de-java-del-proyecto).

---

## Requisitos

- **Java 25** (el JDK completo, no solo el JRE — necesitas el compilador `javac`, no solo el
  ejecutable `java`).
- **Maven 3.9+**.
- Conexión a internet la primera vez (Maven descarga las librerías la primera vez que compilas
  o corres los tests).
- Un IDE como IntelliJ IDEA (recomendado) o VS Code.

Para verificar qué versión de Java está usando tu Maven:

```bash
mvn -version
```

Vas a ver algo parecido a esto:

```
Apache Maven 3.9.9
Maven home: /usr/local/Cellar/maven/3.9.9
Java version: 25, vendor: Oracle Corporation, runtime: /Library/Java/.../jdk-25.jdk
Default locale: es_ES, platform encoding: UTF-8
OS name: "mac os x", version: "14.5", arch: "aarch64"
```

En la línea `Java version:` debe decir **25**. Si dice otra versión, tu `JAVA_HOME` está
apuntando a otro JDK — ajústalo antes de seguir (ver la sección de compatibilidad más abajo).

---

## Compatibilidad entre Maven y Java

Aquí es fácil confundirse porque hay **dos cosas distintas** que dependen de Java:

1. **Con qué versión de Java se ejecuta Maven mismo** (el programa `mvn`).
2. **Para qué versión de Java se compila tu código** (lo que define el `pom.xml` con
   `maven.compiler.release`).

No tienen que coincidir: puedes tener instalado un JDK 21 para *ejecutar* Maven y aun así
decirle a Maven que *compile tu código* apuntando a Java 11, por ejemplo. Pero nunca puedes
compilar para una versión de Java **más nueva** que el JDK que estás usando para correr Maven.

### 1. Java mínimo para ejecutar cada versión de Maven

| Versión de Maven | Java mínimo para correr `mvn` | Comentario |
|---|---|---|
| 3.0.x – 3.2.x | Java 5 | Obsoleto, no lo uses en 2026 |
| 3.3.x – 3.6.x | Java 7 (se recomienda 8) | Frecuente en proyectos legacy |
| 3.8.x | Java 8 | Rama estable anterior a la 3.9 |
| **3.9.x (recomendada para este curso)** | **Java 8**, pero compila perfectamente con JDKs modernos (17, 21, 25) | La que usamos aquí |
| 4.0.x | Java 17 | Nueva generación de Maven (2025), aún poco usada en la industria |

> Conclusión práctica: con **Maven 3.9+** no vas a tener problemas para trabajar con Java 11,
> 17, 21 o 25. El "mínimo" de la tabla es el piso, no el techo — Maven 3.9 corre perfecto
> sobre un JDK 25 como el que usamos en el curso.

### 2. Para qué versión de Java puedes compilar (ejemplo con Java 11)

Esto lo controla **el `pom.xml`**, no la versión de Maven. Digamos que quieres trabajar este
proyecto con **Java 11** en lugar de Java 25:

| Paso | Qué hacer |
|---|---|
| 1 | Instala el **JDK 11** en tu máquina (además del que ya tengas, no hace falta desinstalar nada). |
| 2 | Apunta `JAVA_HOME` al JDK 11 (o configura el SDK del proyecto en tu IDE a 11). |
| 3 | En el `pom.xml`, cambia `<maven.compiler.release>25</maven.compiler.release>` por `<maven.compiler.release>11</maven.compiler.release>`. |
| 4 | Corre `mvn clean install` para recompilar todo con la nueva configuración. |

Si te saltas el paso 3 y solo cambias el JDK de tu máquina, Maven va a seguir intentando
compilar para la versión 25 (porque así lo dice el `pom.xml`) y vas a ver un error como
`invalid target release: 25` o `release version 11 not supported`. **El `pom.xml` manda, no
lo que tengas instalado.**

---

## Cambiar la versión de Java del proyecto

Resumiendo el punto anterior en una checklist, cada vez que cambies de versión de Java:

1. ✅ Instala el JDK correspondiente.
2. ✅ Actualiza `JAVA_HOME` (o el SDK del proyecto en tu IDE).
3. ✅ Edita **una sola línea** del `pom.xml`:

   ```xml
   <maven.compiler.release>TU_VERSION_AQUI</maven.compiler.release>
   ```

4. ✅ Corre `mvn clean install` para forzar una recompilación completa (ver más abajo por qué
   `clean` es importante aquí).
5. ✅ Recarga el proyecto Maven en tu IDE (las flechitas en círculo del panel de Maven), para
   que el IDE también se entere del cambio.

> **Nota:** este proyecto usa `maven.compiler.release` (una sola propiedad) en lugar de las
> viejas `maven.compiler.source` / `maven.compiler.target` (dos propiedades separadas). Es la
> forma moderna y recomendada desde Java 9 en adelante: garantiza que no uses por accidente
> APIs de una versión de Java más nueva que la que declaras.

---

## Comandos esenciales de Maven

Todos estos comandos se corren **desde la carpeta raíz del proyecto**, es decir, donde está el
archivo `pom.xml`.

### `mvn clean install` — compilar todo desde cero

```bash
mvn clean install
```

Qué hace, en orden:

1. **`clean`**: borra la carpeta `target/` (todo lo compilado anteriormente). Así te
   aseguras de que no queden `.class` viejos mezclados con los nuevos.
2. **`install`**: compila el código de `src/main`, compila y corre los tests de `src/test`, y
   si todo pasa, empaqueta el proyecto y lo instala en tu repositorio local (`~/.m2`), para que
   otros proyectos tuyos puedan usarlo como dependencia.

Úsalo cuando:
- Acabas de clonar el proyecto por primera vez.
- Cambiaste algo en el `pom.xml` (como la versión de Java del punto anterior).
- Quieres estar 100% seguro de que todo compila y todos los tests pasan, sin restos de
  compilaciones anteriores.

Si solo quieres compilar sin instalar en tu repositorio local, `mvn clean package` hace lo
mismo pero se detiene antes del paso de instalación.

### Descargar las dependencias

La primera vez que abras el proyecto, Maven necesita descargar las librerías que usamos
(JUnit 5, Datafaker, el plugin del árbol bonito de tests, etc.). Esto pasa **automáticamente**
la primera vez que corres cualquier comando (`mvn test`, `mvn clean install`…), pero si quieres
forzarlo manualmente:

```bash
mvn -U dependency:resolve
```

- `dependency:resolve` descarga todas las dependencias declaradas en el `pom.xml`.
- `-U` fuerza a Maven a revisar si hay versiones más nuevas, en vez de confiar en lo que ya
  tiene en caché.

Si prefieres descargar **todo lo necesario para trabajar sin internet después** (incluyendo
plugins), usa:

```bash
mvn dependency:go-offline
```

Las dependencias se guardan en tu **repositorio local de Maven**, normalmente en:

```
~/.m2/repository
```

Ahí quedan cacheadas: la próxima vez que compiles, Maven no vuelve a descargarlas (a menos que
cambie la versión que pides en el `pom.xml`).

### Correr los tests

#### Opción 1 — Terminal (recomendada, muestra los tiempos en árbol)

```bash
mvn test
```

Esto corre **todos** los tests sin reinstalar nada (más rápido que `clean install` para el
día a día). Verás una salida en forma de árbol, con una palomita ✔ y el **tiempo de cada
test** al lado:

```
ArrayList vs LinkedList: insert in the middle
└─ insert 2000 elements in the middle of a 2000-element list ✔ 42 ms
```

Para correr **una sola clase** de test:

```bash
mvn test -Dtest=MiddleInsertionTest
```

Para correr un **solo método** dentro de una clase:

```bash
mvn test -Dtest=MiddleInsertionTest#insertInMiddle
```

#### Opción 2 — IntelliJ (rápida para el día a día)

Abre la clase de test y haz clic en la **flecha verde ▶** que aparece junto a la clase o al
método. Los resultados salen en el panel de abajo.

> El árbol bonito con el formato UNICODE **solo aparece con `mvn test` en la terminal**. El
> runner de IntelliJ muestra los resultados en su propia ventana. Ambos corren los mismos
> tests; solo cambia cómo se ve.

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
Tu Maven está compilando con un Java más viejo que el que pide el `pom.xml`. Verifica con
`mvn -version` que la `Java version` sea al menos la que indica
`<maven.compiler.release>` en el `pom.xml`, y ajusta `JAVA_HOME` si no lo es. Si de verdad
quieres usar una versión distinta, sigue los pasos de
[Cambiar la versión de Java del proyecto](#cambiar-la-versión-de-java-del-proyecto).

### Cambié de versión de Java y los tests fallan raro / no compilan
Casi siempre es porque quedaron `.class` viejos compilados con la versión anterior. Corre:
```bash
mvn clean install
```
El `clean` borra `target/` por completo antes de recompilar, así no quedan restos mezclados.

### No veo el árbol bonito de tiempos
El formato en árbol solo sale con **`mvn test` en la terminal**, no con el runner del IDE.
Si aun así no aparece, revisa que en el `pom.xml` el `maven-surefire-plugin` tenga
configurado el *tree reporter*.

---

## Resumen de comandos

```bash
mvn -version                       # verifica la versión de Java que usa Maven
mvn clean install                  # borra target/, compila, corre tests e instala en ~/.m2
mvn clean package                  # igual que install pero sin instalar en el repo local
mvn -U dependency:resolve          # fuerza la descarga de librerías declaradas en el pom
mvn dependency:go-offline          # descarga todo lo necesario para trabajar sin internet
mvn test                           # corre todos los tests
mvn test -Dtest=NombreDelTest      # corre una sola clase de test
mvn test -Dtest=Clase#metodo       # corre un solo método de test
```
