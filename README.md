# Nombre del Proyecto

Aplicacion4b

## Descripcion

* Vamos a lanzar una aplicaión que en primer lugar guardará nuestros datos al iniciar la app.
* Posteriormente no pedira mas los datos, ya que estan guardados.
* La aplicacion consta de 4 botones cada uno con su funcionalida.
  * Boton1 hace la llamada, que nos permitirá llamar a un numero de telefono si aceptamos los permisos.
  * Boton2 redirecciona a una página web que nosotros configuremos previamente.
  * Boton3 envía un correo electronico.
  * Boton4 implementa una funcionalidad que nosotros queramos.

## Creación de la pantalla de carga

En mi caso he creado una view de fondo y he añadido una imagen para que se vea de forma uniforme como
pantalla de carga.

Añadimos la imagen a la carpeta drawable, creamos un Activity con su layout donde añadimos la imagen
y la view.

### Código del Activity de la pantalla de carga

**Variable Tiempo**

Creamos una variable para almacenar el tiempo que va a tardar en irse el Activity de carga.

```kotlin
    private val TIEMPO_CARGA = 3000 // Tiempo de carga en milisegundos (3 segundos)
```

**Usamos Handler**

Usamos un Handler para retrasar la transición a MainActivity.

```Kotlin
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carga)

        android.os.Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, TIEMPO_CARGA.toLong())
    }
```

**Handler**: Esta clase permite ejecutar código en el hilo principal después de un tiempo especificado. 
En este caso, se utiliza para retrasar la ejecución de un bloque de código.
**postDelayed**: Este método toma dos parámetros:
  * Un bloque de código que se ejecutará después de un retraso.
  * Un tiempo de retraso en milisegundos (TIEMPO_CARGA.toLong()), que debe ser una constante definida en tu código, 
  como val TIEMPO_CARGA = 3000 para un retraso de 3 segundos.

**Intent**: Este objeto nos permite decirle al sistema que quieres iniciar otra actividad.(MainActivity)
**startActivity(intent)**: Este método inicia la MainActivity, que es la siguiente pantalla que el usuario verá.
**finish()**: Este método cierra la CargaActivity. Al hacer esto, aseguras que el usuario no pueda 
volver a la pantalla de carga presionando el botón "Atrás".

