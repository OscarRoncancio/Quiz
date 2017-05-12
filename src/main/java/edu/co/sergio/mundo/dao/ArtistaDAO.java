package edu.co.sergio.mundo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.co.sergio.mundo.vo.Artista;
import edu.co.sergio.mundo.vo.*;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ArtistaDAO implements IBaseDatos<Usuario> {


    public List<Usuario> findAll() {
        List<Usuario> departamentos = null;
        String query = "SELECT * FROM Depto";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null;

            while (rs.next()) {
                if (departamentos == null) {
                    departamentos = new ArrayList<Usuario>();
                }

                Usuario registro = new Usuario();
                id = rs.getInt("id_depto");
                registro.setId_departamento(id);

                nombre = rs.getString("nom_depto");
                registro.setNom_departamento(nombre);

                departamentos.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return departamentos;
    }

    public boolean insert(Usuario t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into Depto (id_depto,nom_depto)" + " values (?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_departamento());
            preparedStmt.setString(2, t.getNom_departamento());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean update(Usuario t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "update Depto set nom_depto = ? where id_depto = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNom_departamento());
            preparedStmt.setInt(2, t.getId_departamento());
            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean delete(Usuario t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "delete from Depto where id_depto = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_departamento());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Artista> recursos() {
        List<Artista> proyectos = null;

        String query = "select nom_proy,Count(id_rec) as total from Proyecto left join Recurso using (id_proyecto) group by nom_proy;";
        Connection connection = null;
        Artista d = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            int total = 0;
            String nombre_pro = null;
            d = new Artista();
            while (rs.next()) {
                if (proyectos == null) {
                    proyectos = new ArrayList<Artista>();
                }

                nombre_pro = rs.getString("nom_proy");
                d.setName_proy(nombre_pro);

                total = rs.getInt("total");
                d.setTotal(total);

                proyectos.add(d);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de proyectos");
            e.printStackTrace();
        }
        return proyectos;
    }

}
