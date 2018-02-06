package Data;

import java.sql.*;
import java.util.ArrayList;

import Business.Product;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDB {

    public static int insert(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Product (ProductCode, ProductDescription, ProductPrice) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE Product SET "
                + "ProductCode = ?, "
                + "ProductDescription = ?, "
                + "ProductPrice = ? "
                + "WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setLong(4, product.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int delete(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM Product "
                + "WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, product.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean idExists(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT ProductID FROM Product "
                + "WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static Product selectProduct(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product "
                + "WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Product product = null;
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getLong("ProductID"));
                product.setCode(rs.getString("ProductCode"));
                product.setDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("ProductPrice"));
            }
            return product;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Product> selectProducts() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> products;

        String query = "SELECT * FROM Product";
        try {
            Connection connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            products = new ArrayList<Product>();
            Product product;
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getLong("ProductID"));
                product.setCode(rs.getString("ProductCode"));
                product.setDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("ProductPrice"));
                products.add(product);
            }
            connection.close();
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String dbName = "music";
        String userName = "root";
        String password = "87654321";
        String hostname = "murach.ctssmpbzpkdc.us-east-1.rds.amazonaws.com";
        String port = "3306";
        String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
        return DriverManager.getConnection(jdbcUrl);
    }
}
