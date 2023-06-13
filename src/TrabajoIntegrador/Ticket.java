package TrabajoIntegrador;

import java.time.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ticket {
	
    Scanner dato = new Scanner(System.in);
	Connection conexion = null;
    Statement stm = null;
    ResultSet r = null;
    
    private String Fecha;
    private String Hora;
   
    public Ticket(String Fecha, String Hora) {
        this.Fecha = Fecha;
        this.Hora = Hora;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

	/*CONECTAR A LA BASE DE DATOS*/
	  public void conectar() { 
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conexion = DriverManager.getConnection("jdbc:mysql://localhost/Negocio","root","Fede1234");
	            
	        } catch (Exception e) {
	            System.out.println("*****************************************");
	            System.out.println(" * OCURRIO UN ERROR * ");
	            System.out.println("******************************************");
	        }
	    }

	  /*DESCONECTAR LA BASE DE DATOS*/
	    private void desconectar() {
	        try {
	            conexion.close();
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
	    }
	    
	    public void CrearTicket(int ClienteDni, String Fecha, String Hora) {
	    	String sql;
	        sql = "INSERT INTO Ticket (TicketFecha,Tickethora,TicketClienteDni) VALUES ('"+Fecha+"','"+Hora+"','"+ClienteDni+"')";
	        EjecutarConsultaDB(sql);
        	}
	        
		public void ImprimirTicket(int TicketID, String ClienteNombre, String ClienteApellido) {	    	
    	try {
    		String TicketFecha = "";
    		String TicketHora = "";
    		float ProdPrecioDescuento;
    		float TotalFinal = 0;
        	String sql = "SELECT * FROM detalleticket dt INNER JOIN producto p ON dt.DetTickProdId = p.ProdId INNER JOIN ticket t ON dt.DetTickTicketID = t.TicketID Where DetTickTicketID='"+TicketID+"'";
        	ResultSet resultado = stm.executeQuery(sql);
        	System.out.println("\n ********** RESUMEN DE TU COMPRA ********** \n");
        	System.out.println("\n     Cliente: " + ClienteNombre + ", " + ClienteApellido + "\n");
        	System.out.println("---------------------------------------------------------------------------------------------");
        	System.out.println("   Cantidad   |   Nombre   |   Precio U.  |   Precio U. c/ desc   |   Total");
            while (resultado.next()) {
            	TicketFecha = resultado.getString("TicketFecha");
            	TicketHora = resultado.getString("TicketHora");
            	float ProdCantidad = resultado.getFloat("DetTickCantidad");
            	String ProdNombre = resultado.getString("ProdNombre");
            	int ProdDescuento = resultado.getInt("ProdDescuento");
            	float ProdPrecio = resultado.getFloat("ProdPrecio");
            	if (ProdDescuento == 0) {
            		ProdPrecioDescuento = ProdPrecio;
            	} else {
            		ProdPrecioDescuento = ProdPrecio - (ProdPrecio * ProdDescuento) / 100;
            	}
            	float Total = ProdPrecioDescuento * ProdCantidad;
            	TotalFinal = TotalFinal += Total;
            	System.out.println("      " + ProdCantidad + "     |    " + ProdNombre + "   |     " + ProdPrecio + "    |         " + ProdPrecioDescuento + "         |    " + Total);
            }
        	System.out.println("---------------------------------------------------------------------------------------------");
        	System.out.println("  								Total:   " + TotalFinal + "");
        	System.out.println("---------------------------------------------------------------------------------------------");
        	System.out.println("\n   Fecha: " + TicketFecha + "				Hora: " + TicketHora + "\n");
    	 } catch (SQLException ex) {
             Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
         }
	    }
	    
		public int GetTicketID(int Dni, String Fecha, String Hora) {	    	
	    	try {
	        	String sql = "SELECT * FROM Ticket Where TicketClienteDni='"+Dni+"' and TicketFecha='"+Fecha+"' and TicketHora='"+Hora+"'";
	        	ResultSet resultado = EjecutarConsultaDB2(sql);
	            while (resultado.next()) {
	            	int ID = resultado.getInt("TicketId");
	            	return ID;}
	    	 } catch (SQLException ex) {
	             Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
	         }
	    	return 0;
		    }
		
		public void InsertarProductosTicketDetalle(int ProdID, int TicketID, float Cantidad) {
        	String sql = "INSERT INTO detalleTicket (DetTickProdID,DetTickTicketID,DetTickCantidad) VALUES ('"+ProdID+"','"+TicketID+"','"+Cantidad+"')";
        	EjecutarConsultaDB(sql);
        }
	    
    private int EjecutarConsultaDB(String sql) {
        int resultado = 0;
        try {
        	conectar();
            stm = conexion.createStatement();
            stm.execute(sql);
            resultado = 1;
            return resultado;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//            	desconectar();
    } return resultado;
}

    private ResultSet EjecutarConsultaDB2(String sql) {
        try {
        	conectar();
            stm = conexion.createStatement();
            return stm.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//            	desconectar();
    } return null;
}

	private ResultSet SelectOne(String sql) {
		conectar();
		try {
		stm = conexion.createStatement();
    	return stm.executeQuery(sql);
	} catch (SQLException ex) {
        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
	}
	 finally {
//    	desconectar();
	} return null;
	}
}