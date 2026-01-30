package com.example.actividadfisicaapp

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.sqrt

/**
 * Actividad para sesión en tiempo real
 * Utiliza el acelerómetro para detectar el nivel de movimiento
 */
class SesionTiempoRealActivity : AppCompatActivity(), SensorEventListener {

    // Vistas
    private lateinit var tvEstadoMovimiento: TextView
    private lateinit var btnFinalizarSesion: Button
    private lateinit var layoutPrincipal: ConstraintLayout

    // Sensor Manager y Acelerómetro
    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null

    // Variables para calcular el movimiento
    private var ultimaActualizacion: Long = 0
    private var ultimoX: Float = 0f
    private var ultimoY: Float = 0f
    private var ultimoZ: Float = 0f

    // Umbrales para detectar movimiento
    companion object {
        private const val UMBRAL_SIN_MOVIMIENTO = 2f
        private const val UMBRAL_MOVIMIENTO_INTENSO = 15f
        private const val INTERVALO_ACTUALIZACION = 100 // milisegundos
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion_tiempo_real)

        // Inicializar vistas
        inicializarVistas()

        // Configurar sensor
        configurarSensor()

        // Configurar listeners
        configurarListeners()
    }

    /**
     * Inicializa las referencias a las vistas
     */
    private fun inicializarVistas() {
        tvEstadoMovimiento = findViewById(R.id.tvEstadoMovimiento)
        btnFinalizarSesion = findViewById(R.id.btnFinalizarSesion)
        layoutPrincipal = findViewById(R.id.layoutPrincipal)
    }

    /**
     * Configura el sensor del acelerómetro
     */
    private fun configurarSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (acelerometro == null) {
            tvEstadoMovimiento.text = "Acelerómetro no disponible en este dispositivo"
            tvEstadoMovimiento.setTextColor(Color.RED)
        }
    }

    /**
     * Configura los listeners de los botones
     */
    private fun configurarListeners() {
        btnFinalizarSesion.setOnClickListener {
            finish() // Cierra la actividad
        }
    }

    /**
     * Se ejecuta cuando la actividad vuelve a estar en primer plano
     * Registra el listener del sensor
     */
    override fun onResume() {
        super.onResume()
        acelerometro?.also { sensor ->
            sensorManager.registerListener(
                this,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    /**
     * Se ejecuta cuando la actividad pasa a segundo plano
     * Desregistra el listener del sensor para ahorrar batería
     */
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    /**
     * Callback cuando cambian los valores del sensor
     */
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            procesarDatosAcelerometro(event)
        }
    }

    /**
     * Procesa los datos del acelerómetro y actualiza la UI
     */
    private fun procesarDatosAcelerometro(event: SensorEvent) {
        val tiempoActual = System.currentTimeMillis()

        // Solo actualizar cada cierto intervalo para evitar demasiadas actualizaciones
        if ((tiempoActual - ultimaActualizacion) > INTERVALO_ACTUALIZACION) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            // Calcular la diferencia con los valores anteriores
            val deltaX = x - ultimoX
            val deltaY = y - ultimoY
            val deltaZ = z - ultimoZ

            // Calcular la magnitud del movimiento
            val movimiento = sqrt((deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ).toDouble())

            // Actualizar valores anteriores
            ultimoX = x
            ultimoY = y
            ultimoZ = z
            ultimaActualizacion = tiempoActual

            // Determinar el nivel de movimiento y actualizar UI
            actualizarEstadoMovimiento(movimiento.toFloat())
        }
    }

    /**
     * Actualiza la interfaz según el nivel de movimiento detectado
     * @param movimiento Magnitud del movimiento detectado
     */
    private fun actualizarEstadoMovimiento(movimiento: Float) {
        when {
            movimiento < UMBRAL_SIN_MOVIMIENTO -> {
                // Sin movimiento
                tvEstadoMovimiento.text = "Sin movimiento"
                tvEstadoMovimiento.setTextColor(Color.parseColor("#2196F3")) // Azul
                layoutPrincipal.setBackgroundColor(Color.parseColor("#E3F2FD")) // Azul claro
            }
            movimiento < UMBRAL_MOVIMIENTO_INTENSO -> {
                // Movimiento suave
                tvEstadoMovimiento.text = "Movimiento suave"
                tvEstadoMovimiento.setTextColor(Color.parseColor("#4CAF50")) // Verde
                layoutPrincipal.setBackgroundColor(Color.parseColor("#E8F5E9")) // Verde claro
            }
            else -> {
                // Movimiento intenso
                tvEstadoMovimiento.text = "Movimiento intenso"
                tvEstadoMovimiento.setTextColor(Color.parseColor("#F44336")) // Rojo
                layoutPrincipal.setBackgroundColor(Color.parseColor("#FFEBEE")) // Rojo claro
            }
        }
    }

    /**
     * Callback cuando cambia la precisión del sensor
     * No es necesario implementarlo para esta aplicación
     */
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No necesitamos hacer nada aquí
    }
}
