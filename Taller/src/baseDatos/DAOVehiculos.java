/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Ejemplar;
import aplicacion.Categoria;
import aplicacion.Cliente;
import aplicacion.JefeTaller;
import aplicacion.Vehiculo;
import aplicacion.Prestamo;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author basesdatos
 */
public class DAOVehiculos extends AbstractDAO {

    public DAOVehiculos (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public Cliente obtenerCliente(String dni){
        Cliente resultado=null;
        Connection con;
        PreparedStatement stmCliente=null;
        ResultSet rsCliente;

        con=this.getConexion();
        try {
        stmCliente=con.prepareStatement("select DNI, nombre, telefonoContacto "+
                                        "from Cliente c "+
                                        "where DNI = ? ");
        stmCliente.setString(1, dni);
        rsCliente=stmCliente.executeQuery();
        if (rsCliente.next())
        {
            resultado = new Cliente(rsCliente.getString("DNI"), rsCliente.getString("nombre"), rsCliente.getString("telefonoContacto"));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCliente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public JefeTaller obtenerJefeTaller(String id){
        JefeTaller resultado=null;
        Connection con;
        PreparedStatement stmSupervisor=null;
        ResultSet rsSupervisor;

        con=this.getConexion();
        try {
        stmSupervisor=con.prepareStatement("select j.idMecanico, nombre, clave, telefonoContacto, fechaIngreso "+
                                        "from JefeTaller j join Mecanico m on j.idMecanico = m.idMecanico "+
                                        "where j.idMecanico = ? ");
        stmSupervisor.setString(1, id);
        rsSupervisor=stmSupervisor.executeQuery();
        if (rsSupervisor.next())
        {
            resultado = new JefeTaller(rsSupervisor.getString("idMecanico"), rsSupervisor.getString("clave"), rsSupervisor.getString("nombre"), rsSupervisor.getString("telefonoContacto"), rsSupervisor.getDate("fechaingreso"));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSupervisor.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public java.util.List<Vehiculo> consultarCatalogo(String matricula, String cliente, String marca, String modelo, String supervisor, String combustible){
        java.util.List<Vehiculo> resultado = new java.util.ArrayList<Vehiculo>();
        int i = 3;
        Vehiculo vehiculoActual;
        Connection con;
        PreparedStatement stmVehiculos=null;
        ResultSet rsVehiculos;

        con=this.getConexion();
        
        String consulta = "select matricula, marca, modelo, combustible, kilometraje, clienteDNI, supervisor " +
                                         "from vehiculo as v "+
                                         "where modelo like ?"+
                                         "  and marca like ?";
        if (!matricula.isEmpty())
            consulta = consulta + " and matricula = ? ";
        
        if (!cliente.isEmpty())
            consulta = consulta + " and clienteDNI = ? ";
        
        if (!supervisor.isEmpty())
            consulta = consulta + " and supervisor = ? ";

        try  {
         stmVehiculos=con.prepareStatement(consulta);
         stmVehiculos.setString(1, "%"+modelo+"%");
         stmVehiculos.setString(2, "%"+marca+"%");
         if (!matricula.isEmpty()){ stmVehiculos.setString(i, matricula); i++;}
         if (!cliente.isEmpty()){ stmVehiculos.setString(i, cliente); i++;}
         if (!supervisor.isEmpty()){ stmVehiculos.setString(i, supervisor); i++;}
         rsVehiculos=stmVehiculos.executeQuery();
        while (rsVehiculos.next())
        {
            vehiculoActual = new Vehiculo(rsVehiculos.getString("matricula"), rsVehiculos.getString("marca"),
                                      rsVehiculos.getString("modelo"), rsVehiculos.getString("combustible"),
                                      rsVehiculos.getInt("kilometraje"));
            vehiculoActual.setPropietario(obtenerCliente(rsVehiculos.getString("clienteDNI")));
            vehiculoActual.setSupervisor(obtenerJefeTaller(rsVehiculos.getString("supervisor")));
            
            resultado.add(vehiculoActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public Vehiculo consultarLibro(Integer idLibro){
        Vehiculo resultado=null;
        Connection con;
        PreparedStatement stmLibro=null;
        ResultSet rsLibro;
        PreparedStatement stmAutores=null;
        ResultSet rsAutores;
        PreparedStatement stmCategorias=null;
        ResultSet rsCategorias;
        PreparedStatement stmEjemplares=null;
        ResultSet rsEjemplares;

        con=super.getConexion();
/*
        try {
        stmLibro=con.prepareStatement("select id_libro, titulo, isbn, editorial, paginas, ano " +
                                         "from libro "+
                                         "where id_libro = ?");
        stmLibro.setInt(1, idLibro);
        rsLibro=stmLibro.executeQuery();
        if (rsLibro.next())
        {
            resultado = new Vehiculo(rsLibro.getInt("id_libro"), rsLibro.getString("titulo"),
                                      rsLibro.getString("isbn"), rsLibro.getString("editorial"),
                                      rsLibro.getInt("paginas"), rsLibro.getString("ano"));

            try{
            stmAutores = con.prepareStatement("select nombre as autor "+
                                              "from autor "+
                                              "where libro = ? "+
                                              "order by orden");
            stmAutores.setInt(1, resultado.getIdLibro());
            rsAutores=stmAutores.executeQuery();
            while (rsAutores.next())
            {
                 resultado.addAutor(rsAutores.getString("autor"));
            }
            } catch (SQLException e){
                  System.out.println(e.getMessage());
                  this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
              stmAutores.close();
             }
            try{
            stmCategorias =con.prepareStatement("select categoria "+
                                                    "from cat_tiene_libro "+
                                                    "where libro = ?");
            stmCategorias.setInt(1,resultado.getIdLibro());
            rsCategorias=stmCategorias.executeQuery();
            while (rsCategorias.next())
            {
                 resultado.addCategoria(new Categoria(rsCategorias.getString("categoria"), null));
            }
            } catch (SQLException e){
                 System.out.println(e.getMessage());
                 this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
             }finally{
                stmCategorias.close();
              }
            try {
            stmEjemplares =con.prepareStatement("select e.num_ejemplar, e.ano_compra, e.localizador, p.fecha_prestamo, p.fecha_vencimiento, p.usuario, p.fecha_devolucion "+
                                                "from ejemplar as e left join prestamos as p on (e.num_ejemplar = p.ejemplar and e.libro = p.libro and p.fecha_devolucion is null)"+
                                                "where e.libro = ?");
            stmEjemplares.setInt(1,resultado.getIdLibro());
            rsEjemplares=stmEjemplares.executeQuery();
            while (rsEjemplares.next())
            {
                if(rsEjemplares.getString("usuario") == null || rsEjemplares.getDate("fecha_devolucion") != null){
                    resultado.addEjemplar(new Ejemplar(resultado, rsEjemplares.getInt("num_ejemplar"),
                                                  rsEjemplares.getString("localizador"),
                                                  rsEjemplares.getString("ano_compra"), null));
                } else{
                    resultado.addEjemplar(new Ejemplar(resultado, rsEjemplares.getInt("num_ejemplar"),
                                                  rsEjemplares.getString("localizador"),
                                                  rsEjemplares.getString("ano_compra"), new Prestamo(rsEjemplares.getString("usuario"), null, null, rsEjemplares.getDate("fecha_prestamo"), null, rsEjemplares.getDate("fecha_vencimiento"))));
                }
            }
            } catch (SQLException e){
                 System.out.println(e.getMessage());
                 this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
               stmEjemplares.close();
            }
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {
               stmLibro.close();
          } catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }*/
        return resultado;
    }

    public java.util.List<Ejemplar> consultarEjemplaresLibro(Integer idLibro){
        java.util.List<Ejemplar> resultado=new java.util.ArrayList<Ejemplar>();
        Connection con;
        PreparedStatement stmEjemplares=null;
        ResultSet rsEjemplares;
        Vehiculo l = consultarLibro(idLibro);

        con=super.getConexion();

        try {
        stmEjemplares =con.prepareStatement("select e.num_ejemplar, e.ano_compra, e.localizador, p.fecha_prestamo, p.fecha_vencimiento, p.usuario, p.fecha_devolucion "+
                                                "from ejemplar as e left join prestamos as p on (e.num_ejemplar = p.ejemplar and e.libro = p.libro and p.fecha_devolucion is null)"+
                                                "where e.libro = ?");
        stmEjemplares.setInt(1,idLibro);
        rsEjemplares=stmEjemplares.executeQuery();
        while (rsEjemplares.next())
        {
            if(rsEjemplares.getString("usuario") == null || rsEjemplares.getDate("fecha_devolucion") != null){
                resultado.add(new Ejemplar(l, rsEjemplares.getInt("num_ejemplar"),
                                                  rsEjemplares.getString("localizador"),
                                                  rsEjemplares.getString("ano_compra"), null));
            } else{
            resultado.add(new Ejemplar(l, rsEjemplares.getInt("num_ejemplar"),
                                                  rsEjemplares.getString("localizador"),
                                                  rsEjemplares.getString("ano_compra"), new Prestamo(rsEjemplares.getString("usuario"), null, null, rsEjemplares.getDate("fecha_prestamo"), null, rsEjemplares.getDate("fecha_vencimiento"))));
            }
        }


        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmEjemplares.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }



    public java.util.List<String> obtenerRestoCategorias(Integer idLibro){
        java.util.List<String> resultado = new java.util.ArrayList<String>();
        String categoriaActual;
        Connection con;
        PreparedStatement stmCategorias=null;
        ResultSet rsCategorias;


        con=super.getConexion();

        try  {
         stmCategorias=con.prepareStatement("select nombre "+
                                            "from categoria c "+
                                            "where not exists (select *  "+
                                            "		  from cat_tiene_libro cl "+
                                            "		  where cl.libro=? and cl.categoria=c.nombre)");
        stmCategorias.setInt(1, idLibro);
        rsCategorias=stmCategorias.executeQuery();
        while (rsCategorias.next())
        {
            categoriaActual = rsCategorias.getString("nombre");
            resultado.add(categoriaActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategorias.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return resultado;
    }
    
    public Integer insertarLibro(Vehiculo libro){
        Connection con;
        PreparedStatement stmLibro=null;
        PreparedStatement stmAutores=null;
        PreparedStatement stmIdLibro=null;
        ResultSet rsIdLibro;
        Integer numAutor;
        Integer idLibro=null;

        con=super.getConexion();

        try {
        stmLibro=con.prepareStatement("insert into libro(titulo, isbn, editorial, paginas, ano) "+
                                      "values (?,?,?,?,?)");
    //    stmLibro.setString(1, libro.getTitulo());
    //    stmLibro.setString(2, libro.getIsbn());
    //    stmLibro.setString(3, libro.getEditorial());
    //    stmLibro.setInt(4, libro.getPaginas());
    //    stmLibro.setString(5,libro.getAno());
        stmLibro.executeUpdate();

        try{
        stmIdLibro=con.prepareStatement("select currval('seq_libro_id_libro') as idLibro");
        rsIdLibro=stmIdLibro.executeQuery();
        rsIdLibro.next();
        idLibro=rsIdLibro.getInt("idLibro");
        } catch (SQLException e){
          System.out.println(e.getMessage());
        }finally{stmIdLibro.close();}

        try{
        stmAutores=con.prepareStatement("insert into autor(libro, nombre, orden) "+
                                      "values (?,?,?)");
        numAutor=1;/*
        for (String s:libro.getAutores()){
            stmAutores.setInt(1, idLibro);
            stmAutores.setString(2, s);
            stmAutores.setInt(3, numAutor);
            stmAutores.executeUpdate();
            numAutor=numAutor+1;
        }*/
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{stmAutores.close();}
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmLibro.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return idLibro;
    }

    public void borrarLibro(Integer idLibro){
        Connection con;
        PreparedStatement stmLibro=null;

        con=super.getConexion();

        try {
        stmLibro=con.prepareStatement("delete from libro where id_libro = ?");
        stmLibro.setInt(1, idLibro);
        stmLibro.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmLibro.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void modificarLibro(Vehiculo libro){
        Connection con;
        PreparedStatement stmLibro=null;
        PreparedStatement stmAutores=null;
        PreparedStatement stmBorrado=null;
        Integer numAutor;

        con=super.getConexion();
/*
        try {
        stmLibro=con.prepareStatement("update libro "+
                                    "set titulo=?, "+
                                    "    isbn=?, "+
                                    "    editorial=?, "+
                                    "    paginas=?, "+
                                    "    ano=? "+
                                    "where id_libro=?");
        stmLibro.setString(1, libro.getTitulo());
        stmLibro.setString(2, libro.getIsbn());
        stmLibro.setString(3, libro.getEditorial());
        stmLibro.setInt(4, libro.getPaginas());
        stmLibro.setString(5,libro.getAno());
        stmLibro.setInt(6, libro.getIdLibro());
        stmLibro.executeUpdate();


        try{
        stmBorrado=con.prepareStatement("delete from autor where libro=?");
        stmBorrado.setInt(1, libro.getIdLibro());
        stmBorrado.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{stmBorrado.close();}



        try{
        stmAutores=con.prepareStatement("insert into autor (libro, nombre, orden) "+
                                      "values (?,?,?)");
        numAutor=1;
        for (String s:libro.getAutores()){
            stmAutores.setInt(1, libro.getIdLibro());
            stmAutores.setString(2, s);
            stmAutores.setInt(3, numAutor);
            stmAutores.executeUpdate();
            numAutor=numAutor+1;
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{stmAutores.close();}


        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmLibro.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }*/
    }

  public void modificarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
        Connection con;
        PreparedStatement stmBorrado=null;
        PreparedStatement stmInsercion=null;

        con=super.getConexion();

        try {
        stmBorrado=con.prepareStatement("delete from cat_tiene_libro where libro = ?");
        stmBorrado.setInt(1, idLibro);
        stmBorrado.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmBorrado.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        try{
        stmInsercion=con.prepareStatement("insert into cat_tiene_libro(libro, categoria) values (?,?)");
        for (String c : categorias){
            stmInsercion.setInt(1, idLibro);
            stmInsercion.setString(2, c);
            stmInsercion.executeUpdate();
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {
               stmBorrado.close();
               stmInsercion.close();
          } catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
  }

  public void insertarEjemplarLibro(Integer idLibro, Ejemplar ejemplar){
        Connection con;
        PreparedStatement stmEjemplar=null;

        con=super.getConexion();

        try {
        stmEjemplar=con.prepareStatement("insert into ejemplar(libro,ano_compra,localizador) "+
                                           "values (?,?,?)");
        stmEjemplar.setInt(1, idLibro);
        stmEjemplar.setString(2, ejemplar.getAnoCompra());
        stmEjemplar.setString(3, ejemplar.getLocalizador());
        stmEjemplar.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmEjemplar.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
  }
  public void borrarEjemplaresLibro(Integer idLibro, java.util.List<Integer> numsEjemplar){
        Connection con;
        PreparedStatement stmEjemplar=null;

        con=super.getConexion();

        try {
        stmEjemplar=con.prepareStatement("delete from ejemplar where libro=? and num_ejemplar=?");
        for (Integer i: numsEjemplar){
            stmEjemplar.setInt(1, idLibro);
            stmEjemplar.setInt(2, i);
            stmEjemplar.executeUpdate();
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmEjemplar.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
  }
  public void modificarEjemplarLibro(Integer idLibro, Ejemplar ejemplar){
        Connection con;
        PreparedStatement stmEjemplar=null;

        con=super.getConexion();

        try {
        stmEjemplar=con.prepareStatement("update ejemplar "+
                                            "set ano_compra=?, "+
                                            "   localizador=? "+
                                            "where libro=? and num_ejemplar=?");
        stmEjemplar.setString(1, ejemplar.getAnoCompra());
        stmEjemplar.setString(2, ejemplar.getLocalizador());
        stmEjemplar.setInt(3, idLibro);
        stmEjemplar.setInt(4, ejemplar.getNumEjemplar());
        stmEjemplar.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmEjemplar.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
  }
}
