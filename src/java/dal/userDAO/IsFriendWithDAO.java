package dal.userDAO;
import Model.User.Email;
import Model.User.IsFriendWith;
import dal.DAO.DAO;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// các hàm khả dụng
// addObject(int id_1 , int id_2) : 2 người dùng kết bạn với nhau
// deleteObject(int id_1 , int id_2) : 2 người dùng hủy kết bạn với nhau
// getAllObjects(int Record_id) : trả về 1 list các bạn bè của người dùng , trả về null nếu không có bạn bè nào
public class IsFriendWithDAO extends DAO{
    public Object getById(int Record_id){
        return null ;
    }
    public boolean addObject(Object x){
        return false;
    }
    public  boolean addObject(int id_1 , int id_2){
        try {
            String sql = "insert into is_friend_withs values(?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id_1);
            stm.setInt(2, id_2);
            stm.executeUpdate();
            stm.setInt(1, id_2);
            stm.setInt(2, id_1);
            stm.executeUpdate();
            return true ;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public  boolean updateObject(Object object){
       return false;
    }
    public boolean deleteObject(int objectId){
        return false;
    }
    public boolean deleteObject(int id_1 , int id_2){
        String sql = "delete from is_friend_withs where user_id_1  = ? and user_id_2 = ?" ;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,id_1);
            stm.setInt(2,id_2);
            stm.executeUpdate();
            stm.setInt(2,id_1);
            stm.setInt(1,id_2);
            stm.executeUpdate();
            return true ;
        } catch (Exception e) {
            return false;
        }
    }
    public List<Object> getAllObjects(){
        return null;
    }
    public List<Object> getAllObjects(int Record_id){
        String sql = "select * from is_friend_withs where user_id_1  = ? or user_id_2 = ?" ;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, Record_id);
            stm.setInt(2, Record_id);
            ResultSet res = stm.executeQuery();
            Set <Integer> se = new HashSet<>() ;
            while(res.next()){
                if(res.getInt(1) != Record_id)se.add(res.getInt(1)) ;
                if(res.getInt(2) != Record_id)se.add(res.getInt(2));
            }
            List<Object> list = new ArrayList<>();
            for(Integer x : se){
                list.add((new UserDAO()).getById((int)x));
            }
            if(list.size() > 0)return list ;
            return null ;
        } catch (Exception e) {
            return null;
        }
    }
}
