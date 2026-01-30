package com.example.actividadfisicaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividadfisicaapp.adapter.ActivityAdapter
import com.example.actividadfisicaapp.model.ActivitySession

/**
 * Actividad principal: Registro de actividades físicas
 * Permite al usuario registrar nuevas sesiones y ver el historial
 */
class MainActivity : AppCompatActivity() {

    // Vistas de la interfaz
    private lateinit var etNombreActividad: EditText
    private lateinit var etDuracion: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnIniciarSesion: Button
    private lateinit var recyclerView: RecyclerView
    
    // Adapter y lista de sesiones
    private lateinit var adapter: ActivityAdapter
    private val listaSesiones = mutableListOf<ActivitySession>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        inicializarVistas()
        
        // Configurar RecyclerView
        configurarRecyclerView()
        
        // Configurar listeners
        configurarListeners()
    }

    /**
     * Inicializa las referencias a las vistas del layout
     */
    private fun inicializarVistas() {
        etNombreActividad = findViewById(R.id.etNombreActividad)
        etDuracion = findViewById(R.id.etDuracion)
        btnGuardar = findViewById(R.id.btnGuardarActividad)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        recyclerView = findViewById(R.id.recyclerViewActividades)
    }

    /**
     * Configura el RecyclerView con su adapter y layout manager
     */
    private fun configurarRecyclerView() {
        adapter = ActivityAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    /**
     * Configura los listeners de los botones
     */
    private fun configurarListeners() {
        // Botón para guardar actividad
        btnGuardar.setOnClickListener {
            guardarActividad()
        }

        // Botón para iniciar sesión en tiempo real
        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, SesionTiempoRealActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Valida y guarda una nueva actividad en la lista
     */
    private fun guardarActividad() {
        // Obtener valores de los campos
        val nombre = etNombreActividad.text.toString().trim()
        val duracionTexto = etDuracion.text.toString().trim()

        // Validar que el nombre no esté vacío
        if (nombre.isEmpty()) {
            Toast.makeText(
                this,
                "Por favor, introduce el nombre de la actividad",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Validar que la duración sea un número válido
        val duracion = duracionTexto.toIntOrNull()
        if (duracion == null || duracion <= 0) {
            Toast.makeText(
                this,
                "Por favor, introduce una duración válida en minutos",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Crear nueva sesión
        val nuevaSesion = ActivitySession(
            nombre = nombre,
            duracion = duracion
        )

        // Añadir a la lista
        listaSesiones.add(0, nuevaSesion) // Añadir al principio para que aparezca arriba
        
        // Actualizar el RecyclerView
        adapter.submitList(listaSesiones.toList())

        // Limpiar los campos
        etNombreActividad.text.clear()
        etDuracion.text.clear()

        // Mostrar mensaje de confirmación
        Toast.makeText(
            this,
            "Actividad '${nuevaSesion.nombre}' guardada correctamente",
            Toast.LENGTH_SHORT
        ).show()
    }
}
