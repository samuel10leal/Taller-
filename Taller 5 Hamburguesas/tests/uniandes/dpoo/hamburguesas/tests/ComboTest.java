package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private Combo combo;
	private ArrayList<ProductoMenu> itemsCombo; 

    @BeforeEach
    void setUp() {
        itemsCombo = new ArrayList<>();
        itemsCombo.add(new ProductoMenu("corral",14000));
        itemsCombo.add(new ProductoMenu("papas medianas",5500));
        itemsCombo.add(new ProductoMenu("gaseosa",5000));
        combo=new Combo("combo corral", 0.9, itemsCombo);
    }
    
    @Test
    void TestGetNombre() {
    	assertEquals("combo corral", combo.getNombre(), "Nombre no coincide");
    }
    
    @Test
    void TestGetPrecio() {
    	assertEquals(22050,combo.getPrecio(),"No coincide");
    }
    @Test
    void TestGenerarTextoFactura() {
    	String facturaEsperada = "Combo combo corral\n" +
                " Descuento: 0.9\n" +
                "            22050\n";
    	assertEquals(facturaEsperada,combo.generarTextoFactura(),"No coincide");
    }
    	
    	
    
		
	
	
	

}
