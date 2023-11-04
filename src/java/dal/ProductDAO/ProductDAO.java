package dal.ProductDAO;

import Model.Product.Product;
import dal.DAO.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DAO {
	
	public ProductDAO() {
		super();
	}

	@Override
	public Object getById(int id) {
		String sql = "select * from products where product_id = " + id;	
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				return new Product(rs.getInt("product_id"), 
									rs.getString("product_name"), 
									rs.getBoolean("category"), 
									rs.getString("imagePath"),
									rs.getString("team"),
									rs.getInt("price"),
									rs.getInt("rating"),
									rs.getInt("sold"),
									rs.getInt("discounted"));
			}
		}
		catch (SQLException e) {

		}
		return null;
	}
	
	@Override
	public boolean addObject(Object newProd) {
		try {
			Product p = (Product) newProd;
			String sql = "insert into products(product_name, category, imagePath, team, price, rating, sold, discounted)"
						+ " value(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, p.getProduct_name());
			st.setBoolean(2, p.getCategory());
			st.setString(3, p.getImagePath());
			st.setString(4, p.getTeam());
			st.setInt(5, p.getPrice());
			st.setInt(6, p.getRating());
			st.setInt(7, p.getSold());
			st.setInt(8, p.getDiscounted());
			st.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}

	@Override 
	public boolean updateObject(Object newProd) {
		try {
			Product p = (Product) newProd;
			String sql = "update products set product_name = ?, category = ?, imagePath = ?, team = ?, price = ?, rating = ?, sold = ?, discounted = ? where product_id = " + p.getProduct_id();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, p.getProduct_name());
			st.setBoolean(2, p.getCategory());
			st.setString(3, p.getImagePath());
			st.setString(4, p.getTeam());
			st.setInt(5, p.getPrice());
			st.setInt(6, p.getRating());
			st.setInt(7, p.getSold());
			st.setInt(8, p.getDiscounted());
			st.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean deleteObject(int id) {
		try {
			String sql = "delete from products where product_id = " + id;
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}

	@Override 
	public List<Object> getAllObjects() {
		// ko cast List<Product> ve List<Object> dc @@@	
		
		return null;
	}

	public List<Product> queryObjects() {
		try {
			String sql = "select * from products";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Product> o = new ArrayList<Product> ();
			while (rs.next()) {
				o.add(new Product(rs.getInt("product_id"), 
									rs.getString("product_name"), 
									rs.getBoolean("category"), 
									rs.getString("imagePath"),
									rs.getString("team"),
									rs.getInt("price"),
									rs.getInt("rating"),
									rs.getInt("sold"),
									rs.getInt("discounted")));
			}
			return o;
		}
		catch (SQLException e) {
			return null;
		}
	}
}
