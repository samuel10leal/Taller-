package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import uniandes.dpoo.hamburguesas.excepciones.*;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    void testCargarInformacionRestaurante() throws IOException, HamburguesaException {
        
        crearArchivo("ingredientes_test.txt", "lechuga;1000\ntomate;1000\ncebolla;1000\nqueso mozzarella;2500\nhuevo;2500\nqueso americano;2500\ntocineta express;2500\npapa callejera;2000\npepinillos;2500\ncebolla grille;2500\nsuero costeño;3000\nfrijol refrito;4500\nqueso fundido;4500\ntocineta picada;6000\npiña;2500");
        crearArchivo("menu_test.txt", "corral;14000\ncorral queso;16000\ncorral pollo;15000\ncorralita;13000\ntodoterreno;25000\n1/2 libra;25000\nespecial;24000\ncasera;23000\nmexicana;22000\ncriolla;22000\ncosteña;20000\nhawaiana;20000\nwrap de pollo;15000\nwrap de lomo;22000\nensalada mexicana;20900\npapas medianas;5500\npapas grandes;6900\npapas en casco medianas;5500\npapas en casco grandes;6900\nagua cristal sin gas;5000\nagua cristal con gas;5000\ngaseosa;5000");
        crearArchivo("combos_test.txt", "Combo1;10%;corral;papas medianas");

        restaurante.cargarInformacionRestaurante(
            new File("ingredientes_test.txt"),
            new File("menu_test.txt"),
            new File("combos_test.txt")
        );

        assertFalse(restaurante.getIngredientes().isEmpty(), "Los ingredientes deberían haberse cargado");
        assertFalse(restaurante.getMenuBase().isEmpty(), "El menú de productos base debería haberse cargado");
        assertFalse(restaurante.getMenuCombos().isEmpty(), "El menú de combos debería haberse cargado");
    }

    @Test
    void testCargarInformacionRestauranteIngredienteRepetidoException() {
        crearArchivo("ingredientes_repetidos.txt", "lechuga;1000\ntomate;1000\nlechuga;1000");
        crearArchivo("menu_test.txt", "corral;14000");
        crearArchivo("combos_test.txt", "Combo1;10%;corral;papas medianas");

        assertThrows(IngredienteRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(
                new File("ingredientes_repetidos.txt"),
                new File("menu_test.txt"),
                new File("combos_test.txt")
            );
        }, "Se esperaba IngredienteRepetidoException para ingredientes duplicados");
    }

    @Test
    void testCargarInformacionRestauranteProductoRepetidoException() {
        crearArchivo("ingredientes_test.txt", "lechuga;1000\ntomate;1000");
        crearArchivo("menu_repetidos.txt", "corral;14000\ncorral;14000");
        crearArchivo("combos_test.txt", "Combo1;10%;corral;papas medianas");

        assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(
                new File("ingredientes_test.txt"),
                new File("menu_repetidos.txt"),
                new File("combos_test.txt")
            );
        }, "Se esperaba ProductoRepetidoException para productos duplicados");
    }

    @Test
    void testCargarInformacionRestauranteProductoFaltanteException() {
        crearArchivo("ingredientes_test.txt", "lechuga;1000\ntomate;1000");
        crearArchivo("menu_test.txt", "corral;14000");
        crearArchivo("combos_faltantes.txt", "Combo1;10%;corral;papas medianas");

        assertThrows(ProductoFaltanteException.class, () -> {
            restaurante.cargarInformacionRestaurante(
                new File("ingredientes_test.txt"),
                new File("menu_test.txt"),
                new File("combos_faltantes.txt")
            );
        }, "Se esperaba ProductoFaltanteException para combos con productos faltantes");
    }

    @Test
    void testCargarInformacionRestauranteDatosInvalidos() {
        crearArchivo("ingredientes_invalidos.txt", "lechuga;abc\ntomate;1000");
        crearArchivo("menu_test.txt", "corral;14000");
        crearArchivo("combos_test.txt", "Combo1;10%;corral;papas medianas");

        assertThrows(NumberFormatException.class, () -> {
            restaurante.cargarInformacionRestaurante(
                new File("ingredientes_invalidos.txt"),
                new File("menu_test.txt"),
                new File("combos_test.txt")
            );
        }, "Se esperaba NumberFormatException para datos no válidos en ingredientes");
    }

    @Test
    void testCargarInformacionRestauranteConCombosSinIngredientes() {
        crearArchivo("ingredientes_test.txt", "lechuga;1000\ntomate;1000");
        crearArchivo("menu_test.txt", "corral;14000");
        crearArchivo("combos_sin_ingredientes.txt", "Combo1;10%;hamburguesa;papas medianas");

        assertThrows(ProductoFaltanteException.class, () -> {
            restaurante.cargarInformacionRestaurante(
                new File("ingredientes_test.txt"),
                new File("menu_test.txt"),
                new File("combos_sin_ingredientes.txt")
            );
        }, "Se esperaba ProductoFaltanteException para combos que no contienen ingredientes válidos");
    }

    private void crearArchivo(String nombreArchivo, String contenido) {
        try {
            Files.write(Paths.get(nombreArchivo), contenido.getBytes());
        } catch (IOException e) {
            fail("No se pudo crear el archivo de prueba: " + nombreArchivo);
        }
    }

    @Test
    void testGetIngredientes() {
        assertNotNull(restaurante.getIngredientes(), "La lista de ingredientes debería inicializarse vacía");
    }

    @Test
    void testGetMenuBase() {
        assertNotNull(restaurante.getMenuBase(), "La lista de productos base debería inicializarse vacía");
    }

    @Test
    void testGetMenuCombos() {
        assertNotNull(restaurante.getMenuCombos(), "La lista de combos debería inicializarse vacía");
    }
}