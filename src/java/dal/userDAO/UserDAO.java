package dal.userDAO;
import Model.User.User;
import com.google.gson.Gson;
import dal.DAO.DAO;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.sql.Date;
import java.sql.Types;
// các hàm khả dụng
// addObject(Object object) : 1 người dùng đăng kí tài khooản
// updateObject(Object object) : người dùng sửa thông tin cá nhân của họ
// deleteObject(int objectId) : người dùng xóa tài khoản của họ đồng thời xóa hết các bản ghi tại các  bảng tham chiếu tới (làm trong servlet)
// getAllObjects(int Record_id) : trả về object user , trả về null nếu không có user tương ứng
public class UserDAO extends DAO {
    
    public  Object getById(int Record_id) {
       String sql = "select * from users where user_id  = ?" ;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, Record_id);
            ResultSet res = stm.executeQuery();
            if(!res.next())return null ;
            int user_id_ = Integer.parseInt(res.getString(1)) ;
            String user_name_ = res.getString(2)  ;
            int pass_word_ = Integer.parseInt(res.getString(3)) ;
            String full_name_ = res.getString(4) ;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date_of_birth_ = LocalDate.parse(res.getString(5), formatter);
            boolean gender_ = false ;
            if(res.getString(6).equals("1"))gender_ = true ;
            String country_ = res.getString(7) ;
            String city_ = res.getString(8) ;
            String district_ = res.getString(9);
            String detail_position_ = res.getString(10);
            String avatar_image_path_ = res.getString(11);
            String link_app_ = res.getString(12);
            String link_social_ = res.getString(13);
            String Favor_fc_ = res.getString(14) ;
            String description_text_ = res.getString(15);
            String uid_ = res.getString(16) ;
            int user_role_ = Integer.parseInt(res.getString(17)) ;
            int namechange_cooldown_ = Integer.parseInt(res.getString(18)) ;
            boolean search_permission_ = false ;
            if(res.getString(19).equals("1"))search_permission_ = true ;
            int likes_ = Integer.parseInt(res.getString(20)) ;
            int dislikes_ = Integer.parseInt(res.getString(21)) ;
            int score_to_award_ = Integer.parseInt(res.getString(22));
            int pass_word_latest_ = Integer.parseInt(res.getString(23)) ;
            LocalDate pass_word_latest_time_ = LocalDate.parse(res.getString(24), formatter);;
            int login_fail_ = Integer.parseInt(res.getString(25)) ;
            int login_cooldown_ = Integer.parseInt(res.getString(26)) ;
            return (Object)new User( user_id_,  user_name_,  pass_word_,  full_name_,  date_of_birth_,  gender_,  country_,  city_,  district_,  detail_position_,  avatar_image_path_,  link_app_,  link_social_,  Favor_fc_,  description_text_,  uid_,  user_role_,  namechange_cooldown_,  search_permission_,  likes_,  dislikes_,  score_to_award_,  pass_word_latest_,  pass_word_latest_time_,  login_fail_,  login_cooldown_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addObject(Object x) {
        try {
            User a = (User)x ;
            String sql = "insert into users values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setNull(1, Types.INTEGER);
            stm.setString(2, a.getUser_name());
            stm.setInt(3,  a.getPass_word());
            stm.setString(4, a.getFull_name());
            stm.setDate(5, Date.valueOf(a.getDate_of_birth()));
            if(a.getGender())stm.setBoolean(6,true);
            else stm.setBoolean(6,false);
            stm.setString(7, a.getCountry());
            stm.setString(8, a.getCity());
            stm.setString(9, a.getDistrict());
            stm.setString(10, a.getDetail_position());
            stm.setString(11, a.getAvatar_image_path());
            stm.setString(12, a.getLink_app());
            stm.setString(13, a.getLink_social());
            stm.setString(14, a.getFavor_fc());
            stm.setString(15, a.getDescription_text());
            stm.setString(16, a.getUid());
            stm.setInt(17,   a.getUser_role());
            stm.setInt(18,  a.getNamechange_cooldown());
            if(a.isSearch_permission()) stm.setBoolean(19, true);
            else stm.setBoolean(19,false);
            stm.setInt(20, a.getLikes());
            stm.setInt(21,   a.getDislikes());
            stm.setInt(22,   a.getScore_to_award());
            stm.setInt(23,a.getPass_word_latest());
            stm.setDate(24, Date.valueOf(a.getPass_word_latest_time()));
            stm.setString(25, "" + a.getLogin_fail());
            stm.setString(26,  "" + a.getLogin_cooldown());
            return stm.executeUpdate() > 0 ;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateObject( Object  x ) {
        try {
            User a = (User)x ;
            String sql = "update users set user_id = ?, user_name =?, pass_word =?, full_name =?,  date_of_birth =?,  gender =?,  country =?,  city =?,  district =?,  detail_position =?,  avatar_image_path =?,  link_app =?,   link_social =?,  favor_fc =?,  description_text =?,  uid =?,   user_role =?,  namechange_cooldown =?,   search_permisson =?,   likes =?,  dislikes =?,   score_to_award =?,   password_latest =?,   password_latest_time =?,  login_failed =?,   login_cooldown =?  where user_id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, a.getUser_id());
            stm.setString(2, a.getUser_name());
            stm.setInt(3, a.getPass_word());
            stm.setString(4, a.getFull_name());
            stm.setDate(5, Date.valueOf(a.getDate_of_birth()));
            if(a.getGender())stm.setBoolean(6,true);
            else stm.setBoolean(6, false);
            stm.setString(7, a.getCountry());
            stm.setString(8, a.getCity());
            stm.setString(9, a.getDistrict());
            stm.setString(10, a.getDetail_position());
            stm.setString(11, a.getAvatar_image_path());
            stm.setString(12, a.getLink_app());
            stm.setString(13, a.getLink_social());
            stm.setString(14, a.getFavor_fc());
            stm.setString(15, a.getDescription_text());
            stm.setString(16, a.getUid());
            stm.setInt(17,  a.getUser_role());
            stm.setInt(18,   a.getNamechange_cooldown());
            if(a.isSearch_permission()) stm.setBoolean(19, true);
            else stm.setBoolean(19,false);
            stm.setInt(20,  a.getLikes());
            stm.setInt(21,   a.getDislikes());
            stm.setInt(22,  a.getScore_to_award());
            stm.setInt(23, a.getPass_word_latest());
            stm.setDate(24, Date.valueOf(a.getPass_word_latest_time()));
            stm.setInt(25,  a.getLogin_fail());
            stm.setInt(26,  a.getLogin_cooldown());
            stm.setInt(27, a.getUser_id());
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteObject(int objectId) {
        String sql = "delete from users where user_id  = ?" ;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,objectId);
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<Object> getAllObjects() {
        return null;
    }
    
}
