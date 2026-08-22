# API Collections

Este proyecto es la **base** del curso de colecciones de Java. La idea es siempre la misma:
generamos datos de prueba, los metemos en distintas estructuras (`ArrayList`, `LinkedList`,
`HashSet`, `TreeSet`, `HashMap`…) y **medimos cuánto tarda cada una**. Así aprendemos a
elegir la estructura correcta según el problema, en lugar de usar `ArrayList` para todo.

> Este README está pensado para que cualquiera pueda arrancar, incluso si es nuevo en Java
> o en Maven. Si algo no compila, salta directo a la sección **Solución de problemas** al final.

---

## Las dos ramas del proyecto — lee esto primero

Este repositorio tiene **dos ramas**, y cada una cumple un propósito distinto:

| Rama | Para qué sirve | ¿Se corre? |
|---|---|---|
| **`main`** | Es la rama de **trabajo**. Aquí viven los esqueletos de los ejercicios (cada método lanza `UnsupportedOperationException`, esperando a que tú lo implementes en clase). | ✅ Sí — es la única rama sobre la que trabajas y corres comandos |
| **`solutions`** | Es la rama de **respuestas**. Contiene la implementación ya resuelta de cada ejercicio, por si te atoras o quieres comparar tu solución con la oficial. | ❌ No — solo se consulta para leer el código, no se usa para trabajar ni para correr tests |

> ⚠️ **Regla del curso: todo el trabajo, todos los comandos de Maven y todos los ejercicios se
> hacen sobre la rama `main`.** La rama `solutions` es solo de consulta — nunca la vas a
> compilar, correr, ni hacer merge de ella hacia `main`. Mezclar ambas ramas por accidente es
> el error más común (y más frustrante) del curso: si alguna vez ves que tus ejercicios ya
> aparecen resueltos sin que tú los hayas escrito, probablemente pasó un merge accidental de
> `solutions` — avísale a tu instructor.

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

Los conceptos de colecciones aplican igual sin importar la versión de Java que uses, pero
**este proyecto en particular tiene un piso real**: los DTOs (`Payment`, `Shipment`) están
escritos como **records** de Java, y los records se convirtieron en una característica
estándar (sin banderas de "preview") a partir de **Java 16** — por eso la versión mínima
práctica para este proyecto es **Java 17** (la primera LTS que ya los soporta de forma
estable), no Java 11. Si de verdad necesitas usar Java 11, tendrías que reescribir los DTOs
como clases normales — fuera del alcance de este curso.

Todo el material, capturas de pantalla y ejemplos del curso están hechos con **Java 25**. Si
usas otra versión (17, 21), es tu responsabilidad ajustar el `pom.xml` como se explica más
abajo en [Cambiar la versión de Java del proyecto](#cambiar-la-versión-de-java-del-proyecto).

---

## Requisitos

- **Java 25** (el JDK completo, no solo el JRE — necesitas el compilador `javac`, no solo el
  ejecutable `java`). Mínimo real del proyecto: **Java 17**.
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

En la línea `Java version:` debe decir **25** (o al menos **17**, el mínimo del proyecto). Si
dice otra versión, tu `JAVA_HOME` está apuntando a otro JDK — ajústalo antes de seguir (ver la
sección de compatibilidad más abajo).

### Cómo actualizar Maven

Si `mvn -version` muestra algo anterior a **3.9**, actualízalo antes de continuar — los pasos
exactos dependen de cómo lo instalaste originalmente (Homebrew, SDKMAN, instalación manual,
etc.), y ya se cubrieron en la clase introductoria del curso. Si necesitas repasarlos,
consulta el material de esa clase antes de seguir aquí.

---

## Compatibilidad entre Maven y Java

Aquí es fácil confundirse porque hay **dos cosas distintas** que dependen de Java:

1. **Con qué versión de Java se ejecuta Maven mismo** (el programa `mvn`).
2. **Para qué versión de Java se compila tu código** (lo que define el `pom.xml` con
   `maven.compiler.release`).

No tienen que coincidir: puedes tener instalado un JDK 21 para *ejecutar* Maven y aun así
decirle a Maven que *compile tu código* apuntando a Java 17, por ejemplo. Pero nunca puedes
compilar para una versión de Java **más nueva** que el JDK que estás usando para correr Maven.

### 1. Java mínimo para ejecutar cada versión de Maven

| Versión de Maven | Java mínimo para correr `mvn` | Comentario |
|---|---|---|
| 3.0.x – 3.2.x | Java 5 | Obsoleto, no lo uses en 2026 |
| 3.3.x – 3.6.x | Java 7 (se recomienda 8) | Frecuente en proyectos legacy |
| 3.8.x | Java 8 | Rama estable anterior a la 3.9 |
| **3.9.x (recomendada para este curso)** | **Java 8**, pero compila perfectamente con JDKs modernos (17, 21, 25) | La que usamos aquí |
| 4.0.x | Java 17 | Nueva generación de Maven (2025), aún poco usada en la industria |

> Conclusión práctica: con **Maven 3.9+** no vas a tener problemas para trabajar con Java 17,
> 21 o 25. El "mínimo" de la tabla es el piso de Maven en general — pero recuerda que **este
> proyecto en particular** ya tiene su propio piso más alto (Java 17), por los records de los
> DTOs mencionados arriba.

### 2. Para qué versión de Java puedes compilar (ejemplo con Java 17)

Esto lo controla **el `pom.xml`**, no la versión de Maven. Digamos que quieres trabajar este
proyecto con **Java 17** en lugar de Java 25:

| Paso | Qué hacer |
|---|---|
| 1 | Instala el **JDK 17** en tu máquina (además del que ya tengas, no hace falta desinstalar nada). |
| 2 | Apunta `JAVA_HOME` al JDK 17 (o configura el SDK del proyecto en tu IDE a 17). |
| 3 | En el `pom.xml`, cambia **una sola línea**: `<maven.compiler.release>25</maven.compiler.release>` por `<maven.compiler.release>17</maven.compiler.release>`. |
| 4 | Corre `mvn compile` para recompilar con la nueva configuración (ver más abajo por qué usamos `compile` y no `install` durante el curso). |

Si te saltas el paso 3 y solo cambias el JDK de tu máquina, Maven va a seguir intentando
compilar para la versión 25 (porque así lo dice el `pom.xml`) y vas a ver un error como
`invalid target release: 25` o `release version 17 not supported`. **El `pom.xml` manda, no
lo que tengas instalado.**

---

## Cambiar la versión de Java del proyecto

Resumiendo el punto anterior en una checklist, cada vez que cambies de versión de Java:

1. ✅ Instala el JDK correspondiente (mínimo Java 17, por los records de los DTOs).
2. ✅ Actualiza `JAVA_HOME` (o el SDK del proyecto en tu IDE).
3. ✅ Edita **una sola línea** del `pom.xml`:

   ```xml
   <maven.compiler.release>TU_VERSION_AQUI</maven.compiler.release>
   ```

4. ✅ Corre `mvn compile` para forzar una recompilación con la nueva configuración.
5. ✅ Recarga el proyecto Maven en tu IDE (las flechitas en círculo del panel de Maven), para
   que el IDE también se entere del cambio.

> **Nota:** este proyecto usa `maven.compiler.release` (una sola propiedad) en lugar de las
> viejas `maven.compiler.source` / `maven.compiler.target` (dos propiedades separadas). Es la
> forma moderna y recomendada desde Java 9 en adelante: garantiza que no uses por accidente
> APIs de una versión de Java más nueva que la que declaras.

---

## Comandos esenciales de Maven

Todos estos comandos se corren **desde la carpeta raíz del proyecto**, es decir, donde está el
archivo `pom.xml`, y siempre sobre la rama **`main`**.

### Paso 1 — Descargar las dependencias

La primera vez que abras el proyecto, Maven necesita descargar las librerías que usamos
(JUnit 5, Datafaker, el plugin del árbol bonito de tests, etc.). Esto pasa **automáticamente**
la primera vez que corres cualquier comando de Maven, pero si quieres forzarlo manualmente
antes de escribir código:

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

### `mvn compile` — el comando que SÍ vas a usar todo el curso

```bash
mvn compile
```

Este es el comando que debes usar mientras resuelves los ejercicios. Solo compila el código de
`src/main` — **no corre los tests**, así que no te vas a topar con el `BUILD FAILURE` que sí
verías con otros comandos (ver el aviso más abajo).

Úsalo para confirmar que:
- Tu código compila, sin errores de sintaxis.
- No rompiste ningún import ni ninguna firma de método al implementar un ejercicio.

> ⚠️ **No uses `mvn install` ni `mvn clean install` mientras trabajas los ejercicios.** Ambos
> comandos, además de compilar, **corren todos los tests** — y mientras te falten ejercicios
> por implementar, esos tests van a **fallar**, mostrándote un `BUILD FAILURE` completo con
> decenas de errores. Eso **no significa que algo esté roto** — es exactamente lo que se
> espera al principio del curso (ver la sección de tests abajo). Usa `mvn compile` para tu
> día a día, y guarda `mvn test` (siguiente sección) para cuando sí quieras ver el estado de
> los tests a propósito.

### Correr los tests — y por qué ahorita van a fallar (esto es normal)

```bash
mvn test
```

Esto corre **todos** los tests del proyecto. Ahora mismo, **al inicio del curso, es totalmente
esperado que casi todos fallen** — cada método de ejercicio está sin implementar (lanza
`UnsupportedOperationException`), así que ningún test tiene todavía una lógica real que
verificar.

```
[ERROR] Tests run: 63, Failures: 1, Errors: 62, Skipped: 0
```

Esto **no es un error tuyo ni un problema del proyecto** — es, literalmente, el mapa de qué
ejercicios te faltan. Conforme vayas implementando cada método en clase, esos tests van a
empezar a pasar uno por uno, y el número de `Errors` va a ir bajando.

Verás una salida en forma de árbol, con una palomita ✔ (o una ✘ si falla) y el **tiempo de
cada test** al lado:

```
├─ com.debuggeandoideas.api_collections.list.ListBasicOperationsTest - 0.006 s
│  ├─ ✘ getsPaymentAtGivenIndex - 0.001 s
```

Para correr **una sola clase** de test:

```bash
mvn test -Dtest=ListBasicOperationsTest
```

Para correr un **solo método** dentro de una clase:

```bash
mvn test -Dtest=ListBasicOperationsTest#getsPaymentAtGivenIndex
```

#### Opción alterna — IntelliJ

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

### Ya implementé el ejercicio, pero `mvn test` sigue mostrando muchos `Errors`
Revisa si de verdad implementaste **esa** clase específica — con 60+ tests en el proyecto, es
normal que sigan fallando los que aún no has tocado. Corre solo la clase que te interesa:
`mvn test -Dtest=NombreDelTest`, y confirma que esa en particular ya pasa en verde.

### Mis ejercicios ya aparecen resueltos, sin que yo los haya escrito
Es señal de un merge accidental de la rama `solutions` hacia `main`. Avísale a tu instructor
— normalmente se resuelve con un `git reset --hard origin/main` para volver al estado
correcto (esto borra cualquier cambio local no subido, así que ten cuidado antes de correrlo).

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

### El IDE marca rojo pero `mvn compile` funciona
Recarga el proyecto Maven en el IDE: panel de Maven → **Reload All Maven Projects** (las
flechitas en círculo). El IDE a veces se desincroniza; Maven es la fuente de verdad.

### `invalid target release: 25` (o similar al compilar)
Tu Maven está compilando con un Java más viejo que el que pide el `pom.xml`. Verifica con
`mvn -version` que la `Java version` sea al menos la que indica
`<maven.compiler.release>` en el `pom.xml`, y ajusta `JAVA_HOME` si no lo es. Si de verdad
quieres usar una versión distinta (mínimo Java 17, por los records de los DTOs), sigue los
pasos de [Cambiar la versión de Java del proyecto](#cambiar-la-versión-de-java-del-proyecto).

### Cambié de versión de Java y no compila / da errores raros
Casi siempre es porque quedaron `.class` viejos compilados con la versión anterior. Corre:
```bash
mvn clean compile
```
El `clean` borra `target/` por completo antes de recompilar, así no quedan restos mezclados
(y a diferencia de `clean install`, esto no corre los tests).

### No veo el árbol bonito de tiempos
El formato en árbol solo sale con **`mvn test` en la terminal**, no con el runner del IDE.
Si aun así no aparece, revisa que en el `pom.xml` el `maven-surefire-plugin` tenga
configurado el *tree reporter*.

---

## Resumen de comandos

```bash
mvn -version                       # verifica la versión de Java que usa Maven
mvn -U dependency:resolve          # Paso 1 — fuerza la descarga de librerías del pom
mvn compile                        # el comando de uso diario — compila sin correr tests
mvn test                           # corre todos los tests (al inicio, fallan — es normal)
mvn test -Dtest=NombreDelTest      # corre una sola clase de test
mvn test -Dtest=Clase#metodo       # corre un solo método de test
mvn dependency:go-offline          # descarga todo lo necesario para trabajar sin internet
```

> ⚠️ `mvn install` y `mvn clean install` no se usan durante el curso — ambos corren los tests,
> y mientras te falten ejercicios por implementar, vas a ver un `BUILD FAILURE` completo. Usa
> `mvn compile` para tu día a día, y `mvn test` cuando quieras ver a propósito qué tests
> siguen pendientes.