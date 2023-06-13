package TrabajoIntegrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producto {
	
    Scanner dato = new Scanner(System.in);
	Connection conexion = null;
    Statement stm = null;
    ResultSet r = null;
    
    private String ProdNombre;
    private int ProdDescuento;
    private String ProdVencimiento;
    private float ProdPrecio;
    private float ProdStock;
   
    public Producto (String ProdNombre, int ProdDescuento, String ProdVencimiento, float ProdPrecio, float ProdStock) {
        this.ProdNombre = ProdNombre;
        this.ProdDescuento = ProdDescuento;
        this.ProdVencimiento = ProdVencimiento;
        this.ProdPrecio = ProdPrecio;
        this.ProdStock = ProdStock;
    }

    public String getProdNombre() {
        return ProdNombre;
    }

    public void setProdNombre(String ProdNombre) {
        this.ProdNombre = ProdNombre;
    }
    
    public int getProdDescuento() {
        return ProdDescuento;
    }

    public void setProdDescuento(int ProdDescuento) {
        this.ProdDescuento = ProdDescuento;
    }
    
    public String ProdVencimiento() {
        return ProdVencimiento;
    }

    public void setProdVencimiento(String ProdVencimiento) {
        this.ProdVencimiento = ProdVencimiento;
    }
    
    public float getProdPrecio() {
        return ProdPrecio;
    }

    public void setProdPrecio(float ProdPrecio) {
        this.ProdPrecio = ProdPrecio;
    }
    
    public float getProdStock() {
        return ProdStock;
    }

    public void setProdStock(float ProdStock) {
        this.ProdStock = ProdStock;
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
	    
	    public void ModificarProducto() {
	    	String sql;
	    	boolean continuar = true;
	    	int Opcion = 0;
	    	int ProdID = 0;
	    	String ProdNombre = "";
	    	int ProdDescuento = 0;
	    	String ProdVencimiento = "";
	    	float ProdPrecio = 0;
	    	float ProdStock = 0;
	    	
	    	while (continuar) {
				try {
					System.out.println("Elija una opcion\n");
		    		System.out.println("1) Modificar Producto");
		    		System.out.println("2) Salir");
			    	Opcion = dato.nextInt();
				    }
				catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.nextLine();
					continue;
				}
				dato.nextLine();
		    	if (Opcion == 1) {
    				System.out.println("Ingresar nombre producto a modificar: \n");
    				ProdNombre = dato.nextLine();
		    	}
		    	else if (Opcion == 2) {
		    		continuar = false;
		    		break;
		    	}
		    	else {
		    		System.out.println("Opcion incorrecta");
		    		continue;
		    	}
	
	        	try {
		        	sql = "SELECT * FROM producto where ProdNombre='"+ProdNombre+"'";
		        	ResultSet consulta = SelectOne(sql);
	        		if (consulta.next()) {
	        	    	ProdID = consulta.getInt("ProdID");
	        	    	ProdDescuento = consulta.getInt("ProdDescuento");
	        	    	ProdVencimiento = consulta.getString("ProdVencimiento");
	        	    	ProdPrecio = consulta.getFloat("ProdPrecio");
	        	    	ProdStock = consulta.getFloat("ProdStock");
	        		} else {
	        			System.out.println("El producto ingresado no existe\n");
	        			ModificarProducto();
	        			}	        			
	        	} catch (SQLException e) {
			         System.out.println(e.getMessage());
	        		 ModificarProducto();
	        	}
	        
	        boolean continuar2 = true;
	        boolean checkString;
	        boolean checkInt;
	        boolean checkFloat;
	        continuar2:
	    	while (continuar2) {
	    		checkInt = true;
    			while (checkInt) {
    				System.out.println("\n Que datos desea modificar? ELIJA UNA OPCION \n");
    	    		System.out.println("1) Nombre producto");
    	    		System.out.println("2) % Descuento");
    	    		System.out.println("3) Vencimiento");
    	    		System.out.println("4) Precio ");
    	    		System.out.println("5) Stock ");
    	    		System.out.println("--------------------------------");
    	    		System.out.println("6) Finalizar \n");
    				try {
    		    		Opcion = dato.nextInt();
		        		checkInt = false;
				    	break;}
    				catch (Exception ex) {
    					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
    					dato.nextLine();
    					continue;
    					}
    			}
	    		switch (Opcion) {
		    		case 1:
		    			dato.nextLine();
		    			checkString = true;
		    			while (checkString) {
		    				try {
		    					System.out.println("1) Nombre producto: \n");
		    					ProdNombre = dato.nextLine();
		    				    if (ProdNombre.matches("[a-zA-Z' ']+")) {
		    				    	checkString = false;
		    		    			break;}
		    				    else {
		    				    	throw new Exception();}
		    				}
		    			catch (Exception ex) {
		    				System.out.println("Parece que has ingresado caracteres no alfabeticos");
		    				continue;
		    					}
			    			}
			    		break;
		    		case 2:
		    			checkInt = true;
		    			while (checkInt) {
		    				try {
		    					System.out.println("2) Ingrese descuento: \n");
				        		ProdDescuento = dato.nextInt();
				        		checkInt = false;
						    	break;}
		    				catch (Exception ex) {
		    					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
		    					dato.nextLine();
		    					continue;}
		    				}
		    			break;
		    		case 3:
		    			dato.nextLine();
		    			checkString = true;
		    			while (checkString) {
		    				try {
				        		System.out.println("3) Ingrese Vencimiento: \n");
				        		ProdVencimiento = dato.nextLine();
				        		checkString = false;
				    			break;}
		    				catch (Exception ex) {
		    					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
		    					continue;}
		    				}	
		    			break;
		    		case 4:
		    			checkFloat = true;
		    			while (checkFloat) {
		    				try {
				        		System.out.println("4) Ingrese precio: \n");
				        		ProdPrecio = dato.nextFloat();
				        		checkFloat = false;
				    			break;}
		    				catch (Exception ex) {
		    					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
		    					dato.nextLine();
		    					continue;}
		    				}	
		    			break;
		    		case 5:
		    			checkFloat = true;
		    			while (checkFloat) {
		    				try {
				        		System.out.println("5) Ingrese stock: \n");
				        		ProdStock = dato.nextFloat();
				        		checkFloat = false;
				    			break;}
		    				catch (Exception ex) {
		    					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
		    					dato.nextLine();
		    					continue;}
		    				}	
		    			break;
		    		case 6:
		    			continuar2 = false;
		    			break continuar2;
		    		default:
		        		System.out.println("Opcion incorrecta \n");
		        		break;
		    			}
		    		}
	    	
	    	int resultado = 0;
	    	sql = "UPDATE producto SET ProdNombre='"+ProdNombre+"', ProdDescuento='"+ProdDescuento+"', ProdVencimiento='"+ProdVencimiento+"', ProdPrecio='"+ProdPrecio+"', ProdStock='"+ProdStock+"' WHERE ProdId='"+ProdID+"'";
	    	resultado = EjecutarConsultaDB(sql);
	    	if (resultado == 1) {
	    		System.out.println("Has Modificado un producto");
	    	} else {
	    		System.out.println("Ha ocurrido un error, intenta nuevamente");
	    	}
	    }
	}
	    	

		public void ConsultarProductos() {
	    	conectar();
    	try {
    		stm = conexion.createStatement();
        	String sql = "SELECT ProdID,ProdNombre,ProdDescuento,ProdVencimiento,ProdPrecio,ProdStock FROM producto";
        	ResultSet resultado = stm.executeQuery(sql);
        	System.out.println("\n PRODUCTOS EXISTENTES:\n");
        	System.out.println("Nº|   Nombre   |   Descuento   |   Vencimiento   |   Precio   |   Stock");
            while (resultado.next()) {
            	System.out.println(resultado.getInt("ProdId") + " | " + resultado.getString("ProdNombre") + " | " + resultado.getInt("ProdDescuento") + " | " + resultado.getString("ProdVencimiento") + " | " + resultado.getFloat("ProdPrecio") + " | " + resultado.getFloat("ProdStock"));
            }
    	 } catch (SQLException ex) {
             Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
         }
	    }
	    
		public void EliminarProducto() {
			boolean checkInt = true;
			int ID = 0;
        	ResultSet resultado;
        	
			while (checkInt) {
				try {
					System.out.println("Ingrese ID producto a eliminar:\n");
				    ID = dato.nextInt();
				    checkInt = false;
				    break;
	        	} catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();}
			}
			
			String sql = "SELECT ProdId FROM producto WHERE ProdID="+ID+"";
			resultado = SelectOne(sql);
        	try {
        		if (resultado.next()) {
        			int ProdID = resultado.getInt("ProdID");
	        		if (ProdID == ID) {
	        			sql = "DELETE FROM producto WHERE ProdID="+ID+"";
	        		 	int resultado2 = EjecutarConsultaDB(sql);
	        			System.out.println("Has eliminado un producto\n");
	        		}
        		} else {
        			System.out.println("El producto ingresado no existe\n");
    			}	        			
            } catch (SQLException e) {
                System.out.println(e.getMessage());
        	}
		}
    	
		public void AgregarProducto() {
			String ProdNombre = "";
			int ProdDescuento = 0;
			String ProdVencimiento = "";
			float ProdPrecio = 0;
			float ProdStock = 0;
			
			boolean checkString = true;
			while (checkString) {
		    try {
			    System.out.println("Nombre Producto");
			    ProdNombre = dato.nextLine();
			    if (ProdNombre.matches("[a-zA-Z' ']+")) {
			    	checkString = false;}
			    else {
			    	throw new Exception();}
			} catch (Exception ex) {
			System.out.println("Parece que has ingresado caracteres no alfabeticos");}
			}
			
			boolean checkInt = true;
			while (checkInt) {
				try {
				    System.out.println("Descuento");
				    ProdDescuento = dato.nextInt();
					checkInt = false;}
				catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();}
			}

			checkString = true;
			while (checkString) {
				try {
				    System.out.println("Vencimineto");
				    ProdVencimiento = dato.next();
				    checkString = false;}
				catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();}
				}

			boolean checkFloat = true;
			while (checkFloat) {
				try {
				    System.out.println("Precio");
				    ProdPrecio = dato.nextFloat();
				    checkFloat = false;}
				catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();}
			}

			checkFloat = true;
			while (checkFloat) {
				try {
					System.out.println("Stock");
				    ProdStock = dato.nextFloat();
				    checkFloat = false;}
				catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();}
			}
				    
		    Producto producto = new Producto(ProdNombre,ProdDescuento,ProdVencimiento,ProdPrecio,ProdStock);
        	int resultado = 0;
        	String sql = "INSERT INTO producto (ProdNombre,ProdDescuento,ProdVencimiento,ProdPrecio,ProdStock) VALUES ('"+ProdNombre+"','"+ProdDescuento+"','"+ProdVencimiento+"','"+ProdPrecio+"','"+ProdStock+"')";
        	resultado = EjecutarConsultaDB(sql);
        	if (resultado == 1) {
        		System.out.println("Has agregado un producto");
        	} else {
        		System.out.println("Ha ocurrido un error, intenta nuevamente");
        	}
        }
		
		public boolean CheckStock(float Cantidad, int ProdID) {
	    	String sql;
	        sql = "SELECT ProdStock FROM producto WHERE ProdId='"+ProdID+"'";
	        ResultSet resultado = EjecutarConsultaDB2(sql);
	        try {
		        if (resultado.next()){
		        	float Stock = resultado.getInt("ProdStock");
		        	if (Stock >= Cantidad) {
		        		return true;
		        	}
		        }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return false;
	 }

		public boolean CheckExiste(int ProdID) {
	    	String sql;
	        sql = "SELECT * FROM producto WHERE ProdId='"+ProdID+"'";
	        ResultSet resultado = EjecutarConsultaDB2(sql);
	        try {
		        if (resultado.next()){
		        	int ID= resultado.getInt("ProdID");
		        	if (ID >= ProdID) {
		        		return true;
		        	}
		        }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return false;
	 }	
		
	    private ResultSet EjecutarConsultaDB2(String sql) {
	        try {
	        	conectar();
	            stm = conexion.createStatement();
	            return stm.executeQuery(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
//	            	desconectar();
	    } return null;
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

