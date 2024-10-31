package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	private ProductoMenu productoBase;
	private ProductoAjustado productoAjustado;
	private  Ingrediente ingredienteEliminado;
	private Ingrediente ingredienteExtra;
	
	@BeforeEach
	void setUp() {
		productoBase= new ProductoMenu("corral",14000);
		productoAjustado=new ProductoAjustado(productoBase);
		ingredienteEliminado= new Ingrediente("cebolla",1000);
		ingredienteExtra= new Ingrediente("tocineta picada",6000);
		
	}
	@Test
    void testGetNombre() {
        assertEquals("corral", productoAjustado.getNombre(), "El nombre no coincide");
    }
	
	@Test
	void testGetPrecioSinAdicion() {
		assertEquals(14000, productoAjustado.getPrecio(),"El precio no coincide" );
	}
	
	

	@Test
	void testGenerarTextoFacturaConModificaciones() {
		

		String facturaEsperada = "corral\n" +
								 "    +tocineta picada                6000\n" +
								 "    -cebolla\n" +
								 "            20000\n";
		assertEquals(facturaEsperada, productoAjustado.generarTextoFactura(), "La factura generada con modificaciones no es correcta");
	}
	
	
}
