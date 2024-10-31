package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;


import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;



public class PedidoTest {
	
	private Pedido pedido;
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	
	
	@BeforeEach
	void setUp(){
		pedido= new Pedido("Samuel", "Calle 122");
		producto1= new ProductoMenu("corral", 14000 );
		producto2= new ProductoMenu("gaseosa", 5000);
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);	
	}
	@Test
	
	void testGetIdPedido() {
		assertEquals(1,pedido.getIdPedido(),"No coincide");
	}
	@Test
	void testGetNombreCliente() {
		assertEquals("Samuel", pedido.getNombreCliente(), "Nombre no coincide");
		
	}
	
	@Test
	
    void testGetPrecioTotalPedido() {
        int precioNeto = 14000 + 5000; 
        int precioIVA = (int) (precioNeto * 0.19); 
        int precioTotalEsperado = precioNeto + precioIVA; // 
        assertEquals(precioTotalEsperado, pedido.getPrecioTotalPedido(), "El precio total no coincide");
    }
	@Test
	void testGenerarTextoFactura() {
		String facturaEsperada="Cliente: Samuel\n" +
                "Dirección: Calle 122\n" +
                "----------------\n" +
                "corral\n" +
                "            14000\n" +
                "gaseosa\n" +
                "            5000\n" +
                "----------------\n" +
                "Precio Neto:  19000\n" +
                "IVA:          3610\n" +
                "Precio Total: 22610\n";
		assertEquals(facturaEsperada,pedido.generarTextoFactura(),"No coincide");
	}
	void testGuardarFactura() {
        File archivoFactura = new File("factura_test.txt");
        try {
            pedido.guardarFactura(archivoFactura);
            assertTrue(archivoFactura.exists(), "El archivo de la factura no se creó correctamente.");
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException no esperada al guardar la factura.");
        } finally {
            archivoFactura.delete(); // Limpiar el archivo de prueba
        }
    }

    @Test
    void testGuardarFacturaFileNotFoundException() {
        File archivoInvalido = new File("/ruta/no/existe/factura.txt");
        assertThrows(FileNotFoundException.class, () -> {
            pedido.guardarFactura(archivoInvalido);
        }, "Se esperaba una FileNotFoundException al intentar guardar en una ruta inválida.");
    }
	

}
