package org.unrn.app;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao {

    private Connection obtenerConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost:3306/personas", "root", "");
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return conexion;
    }

    public Persona personaPorId(int id) {
        String sql = "select p.nombre "
                + "from personas p "
                + "where p.id = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement statement =
                     conn.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            ProxySet<Telefono> telefonos = new ProxySet<Telefono>(this, id);
            String nombrePersona = null;
            while (result.next()) {
                nombrePersona = result.getString(1);
            }
            // Se cierra conexión
            conn.close();
            return new Persona(id, nombrePersona, telefonos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Telefono> obtenerTelefonos(int id) {
        String sql = "select t.numero "
                + "from telefonos t "
                + "where t.idpersona = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement statement =
                     conn.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            Set<Telefono> telefonos = new HashSet<Telefono>();
            while (result.next()) {
                telefonos.add(new Telefono(result.getString(1)));
            }
            return telefonos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
