package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoMenuTest {
	
	private ProductoMenu productoMenu;
	
	@BeforeEach
    void setUp( ) {
		productoMenu= new ProductoMenu("corral",14000);
	}
	
	@Test
	void testGetNombre() {
		assertEquals("corral", productoMenu.getNombre(), "No se encontro el nombre");
		
	}
	
	@Test
	void tesGetPrecio() {
		assertEquals(14000, productoMenu.getPrecio(), "No es el precio correcto");
		
	}
	@Test
	void testGenerarFactura() {
		String  facturaEsperada="corral\n            14000\n";
		assertEquals(facturaEsperada,productoMenu.generarTextoFactura(),"El texto de la factura no es correcto");
	}
	
	
	
   
	

}
