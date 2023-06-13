package TrabajoIntegrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
	
    Scanner dato = new Scanner(System.in);
	Connection conexion = null;
    Statement stm = null;
    ResultSet r = null;
    
    private int ClienteDni;
    private String ClienteNombre;
    private String ClienteApellido;
    private String ClienteDomicilio;
    private int ClienteTelefono;
    private String ClienteMail;
   
    public Cliente (int ClienteDni, String ClienteNombre, String ClienteApellido, String ClienteDomicilio, int ClienteTelefono, String ClienteMail) {
        this.ClienteDni = ClienteDni;
        this.ClienteNombre = ClienteNombre;
        this.ClienteApellido = ClienteApellido;
        this.ClienteDomicilio = ClienteDomicilio;
        this.ClienteTelefono = ClienteTelefono;
        this.ClienteMail = ClienteMail;
    }
    
    public int getClienteDni() {
        return ClienteDni;
    }

    public void setClienteDni(int ClienteDni) {
        this.ClienteDni = ClienteDni;
    }
    
    public String getClienteNombre() {
        return ClienteNombre;
    }

    public void setClienteNombre(String ClienteNombre) {
        this.ClienteNombre = ClienteNombre;
    }
    
    public String getClienteApellido() {
        return ClienteApellido;
    }

    public void setClienteApellido(String ClienteApellido) {
        this.ClienteApellido = ClienteApellido;
    }
    
    public String getClienteDomicilio() {
        return ClienteDomicilio;
    }

    public void setClienteDomicilio(String ClienteDomicilio) {
        this.ClienteDomicilio = ClienteDomicilio;
    }
    
    public int getClienteTelefono() {
        return ClienteTelefono;
    }

    public void setClienteTelefono(int ClienteTelefono) {
        this.ClienteTelefono = ClienteTelefono;
    }

    public String getCClienteMail() {
        return ClienteMail;
    }

    public void setClienteMail(String ClienteMail) {
        this.ClienteMail = ClienteMail;
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
	    
	    public void ModificarCliente() {
	    	String sql;
	    	boolean continuar = true;
	    	int Opcion = 0;
	    	int Dni = 0;
	    	String Nombre = "";
	    	String Apellido = "";
	    	String Domicilio = "";
	    	int Telefono = 0;
	    	String Mail = "";
	    	
	    	while (continuar) {
	    		try {
		    		System.out.println("Elija una opcion\n");
		    		System.out.println("1) Modificar Cliente");
		    		System.out.println("2) Salir");
			    	Opcion = dato.nextInt();
				    }
				catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();
					continue;
				}

	    		if (Opcion == 1) {
	    			try {
	    				System.out.println("Ingresar Dni del cliente a modificar: \n");
	    		    	Dni = dato.nextInt();
					    }
					catch (Exception ex) {
						System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
						dato.nextLine();
						continue;
						}
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
		        	ResultSet consulta = SelectOne(Dni);
	        		if (consulta.next()) {
		               	Nombre = consulta.getString("ClienteNombre");
		            	Apellido = consulta.getString("ClienteApellido");
		            	Domicilio = consulta.getString("ClienteDomicilio");
		            	Telefono = consulta.getInt("ClienteTelefono");
		            	Mail = consulta.getString("ClienteMail");
	        		} else {
	        			System.out.println("El dni ingresado no existe\n");
	        			ModificarCliente();
	        			}	        			
	        	} catch (SQLException e) {
			                System.out.println(e.getMessage());
			                ModificarCliente();
	        	}
	        	
		        boolean checkString;
		        boolean checkInt;
		        
		        continuar = true;
		    	while (continuar) {
		    		checkInt = true;
	    			while (checkInt) {
	    				System.out.println("\n Que datos desea modificar? ELIJA UNA OPCION \n");
			    		System.out.println("1) Nombre");
			    		System.out.println("2) Apellido");
			    		System.out.println("3) Domicilio");
			    		System.out.println("4) Telefono");
			    		System.out.println("5) Mail");
			    		System.out.println("-------------------------------- ");
			    		System.out.println("6) Finalizar");
	    				try {
	    		    		Opcion = dato.nextInt();
	    		    		checkInt = false;
	    		    		break;
	    				} catch (Exception ex) {
	    					System.out.println("Parece que has ingresado caracteres no alfabeticos");
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
				        		System.out.println("\n 2) Ingrese Nombre:");
				        		Nombre = dato.nextLine();
		    				    if (Nombre.matches("[a-zA-Z' ']+")) {
		    				    	checkString = false;
		    				    	break;}
		    				    else {
		    				    	throw new Exception();}
		    				} catch (Exception ex) {
		    					System.out.println("Parece que has ingresado caracteres no alfabeticos");
		    					continue;
		    				}
		    			}
		    			break;
		    		case 2:
		        		dato.nextLine();
		    			checkString = true;
		    			while (checkString) {
		    				try {
				        		System.out.println("\n 3) Ingrese Apellido:");
				        		Apellido = dato.nextLine();
		    				    if (Apellido.matches("[a-zA-Z' ']+")) {
		    				    	checkString = false;
		    				    	break;}
		    				    else {
		    				    	throw new Exception();}
		    				} catch (Exception ex) {
		    					System.out.println("Parece que has ingresado caracteres no alfabeticos");
		    					continue;
		    				}
		    			}
		    			break;
		    		case 3:
		        		dato.nextLine();
		    			checkString = true;
		    			while (checkString) {
		    				try {
				        		System.out.println("\n 4) Ingrese Domicilio:");
				        		Domicilio = dato.nextLine();
				        		checkString = false;
				        		break;
		    				} catch (Exception ex) {
		    					System.out.println("Ocurrio un error, intenta nuevamente");
		    					continue;
		    				}
		    			}
		    			break;
		    		case 4:
		        		dato.nextLine();
		    			checkInt = true;
		    			while (checkInt) {
		    				try {
				        		System.out.println("\n 5) Ingrese Telefono:");
				        		Telefono = dato.nextInt();
				        		checkInt = false;
				    			break;
				    		} catch (Exception ex) {
		    					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
		    					dato.nextLine();
		    					continue;
		    				}
		    			}
		    			break;
		    		case 5:
		        		dato.nextLine();
		    			checkString = true;
		    			while (checkString) {
		    				try {
				        		System.out.println("\n 6) Ingrese Mail:");
				        		Mail = dato.nextLine();
				        		checkString = false;
				        		break;
		    				} catch (Exception ex) {
		    					System.out.println("Ocurrio un error, intenta nuevamente");
		    					continue;
		    				}
		    			}
		    			break;
		    		case 6:
		    			continuar = false;
		    			break;
		    		default:
    					System.out.println("Opcion incorrecta\n");
    					break;
		    		}
		    	}
				System.out.println(Dni+""+Nombre+""+Apellido+""+Domicilio+""+Telefono+""+Mail);
		    	int resultado = 0;
		    	sql = "UPDATE cliente SET ClienteNombre='"+Nombre+"', ClienteApellido='"+Apellido+"', ClienteDomicilio='"+Domicilio+"', ClienteTelefono='"+Telefono+"', ClienteMail='"+Mail+"' WHERE ClienteDni='"+Dni+"'";
		    	resultado = EjecutarConsultaDB(sql);
		    	if (resultado == 1) {
		    		System.out.println("Has Modificado un cliente");
		    	} else {
		    		System.out.println("Ha ocurrido un error, intenta nuevamente");
		    	}
	    	}
	    }

	    public void ConsultarClientes() {
        	int contador = 1;
	    	conectar();
    	try {
    		stm = conexion.createStatement();
        	String sql = "SELECT ClienteDni,ClienteNombre,ClienteApellido,ClienteDomicilio,ClienteTelefono,ClienteMail FROM cliente ORDER BY ClienteNombre asc";
        	ResultSet resultado = stm.executeQuery(sql);
        	System.out.println("\n CLIENTES EXISTENTES:\n");
        	System.out.println("Nº|   Dni   |   Nombre   |   Apellido   |   Domicilio   |   Telefono   |   Mail  ");
            while (resultado.next()) {
            	System.out.println(contador + ") " + resultado.getInt("ClienteDni") + " | " + resultado.getString("ClienteNombre") + " | " + resultado.getString("ClienteApellido") + " | " + resultado.getString("ClienteDomicilio") + " | " + resultado.getInt("ClienteTelefono") + " | " + resultado.getString("ClienteMail"));
            	contador += 1;
            }
    	 } catch (SQLException ex) {
             Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
         }
	    }
	    
	    public void EliminarCliente() {
	    	String sql;
			boolean checkInt = true;
        	ResultSet resultado;
        	int Dni = 0;
        	
			while (checkInt) {
				try {
			        System.out.println("Dni cliente a eliminar");
			        Dni = dato.nextInt();
				    checkInt = false;
				    break;
	        	} catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();}
			}
			resultado = SelectOne(Dni);
        	try {
        		if (resultado.next()) {
        			int ClienteDni = resultado.getInt("ClienteDni");
	        		if (ClienteDni == Dni) {
	        			sql = "DELETE FROM cliente WHERE ClienteDni="+Dni+"";
	        		 	EjecutarConsultaDB(sql);
	        			System.out.println("Has eliminado un cliente\n");
	        		}
        		} else {
        			System.out.println("El cliente ingresado no existe\n");
    			}	        			
            } catch (SQLException e) {
                System.out.println(e.getMessage());
        	}
		}
	    
        public void AgregarCliente() {
	        boolean checkString;
	        boolean checkInt;
	        int Dni = 0;
	        String Nombre = "";
	        String Apellido = "";
	        String Domicilio = "";
	        int Telefono = 0;
	        String Mail = "";
	        
			checkInt = true;
			while (checkInt) {
				try {
			        System.out.println("Dni");
			        Dni = dato.nextInt();
	        		checkInt = false;
	    			break;
	    		} catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();}
				}
			checkString = true;
			dato.nextLine();
        	while (checkString) {
    		    try {
    		        System.out.println("Nombre");
    		        Nombre = dato.nextLine();
    			    if (Nombre.matches("[a-zA-Z' ']+")) {
    			    	checkString = false;}
    			    else {
    			    	throw new Exception();}
    			} catch (Exception ex) {
    			System.out.println("Parece que has ingresado caracteres no alfabeticos");
    			}
    			}
			checkString = true;
        	while (checkString) {
    		    try {
    		        System.out.println("Apellido");
    		        Apellido = dato.nextLine();
    			    if (Apellido.matches("[a-zA-Z' ']+")) {
    			    	checkString = false;}
    			    else {
    			    	throw new Exception();}
    			} catch (Exception ex) {
    			System.out.println("Parece que has ingresado caracteres no alfabeticos");}
    			}
			checkString = true;
        	while (checkString) {
    		    try {
    		        System.out.println("Domicilio");
    		        Domicilio = dato.nextLine();
    		        checkString = false;
    			} catch (Exception ex) {
    			System.out.println("Ocurrio un error");}
    			}
			checkInt = true;
			while (checkInt) {
				try {
			        System.out.println("Telefono");
			        Telefono = dato.nextInt();
	        		checkInt = false;
	    			break;
	    		} catch (Exception ex) {
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero ");
					dato.next();
	    			}
				}
			checkString = true;
	        dato.nextLine();
        	while (checkString) {
    		    try {
    		        System.out.println("Mail");
    		        Mail = dato.nextLine();
    		        checkString = false;
    			} catch (Exception ex) {
    			System.out.println("Ocurrio un error");}
    			}

        	Cliente cliente = new Cliente(Dni,Nombre,Apellido,Domicilio,Telefono,Mail);
        	int resultado = 0;
        	String sql = "INSERT INTO cliente (ClienteDni,ClienteNombre,ClienteApellido,ClienteDomicilio,ClienteTelefono,ClienteMail) VALUES ('"+Dni+"','"+Nombre+"','"+Apellido+"','"+Domicilio+"','"+Telefono+"','"+Mail+"')";
        	resultado = EjecutarConsultaDB(sql);
        	if (resultado == 1) {
        		System.out.println("Has agregado un cliente nuevo");
        	} else {
        		System.out.println("Ha ocurrido un error, intenta nuevamente");
        	}
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

    public ResultSet SelectOne(int Dni) {
    	String sql = "SELECT * FROM cliente where ClienteDni='"+Dni+"'";
		conectar();
		try {
		stm = conexion.createStatement();
    	return stm.executeQuery(sql);
		} catch (SQLException ex) {
	        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
	//    	desconectar();
		} return null;
		}
	}
