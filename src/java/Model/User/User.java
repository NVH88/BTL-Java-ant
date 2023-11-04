package Model.User;
import java.time.LocalDate;
public class User {
    private int user_id ;
    private String user_name ;
    private int pass_word ;
    private String full_name ;
    private LocalDate date_of_birth ;
    private boolean gender ;
    private String country ;
    private String city ;
    private String district;
    private String detail_position;
    private String avatar_image_path;
    private String link_app;
    private String link_social;
    private String Favor_fc ;
    private String description_text;
    private String uid ;
    private int user_role ;
    private int namechange_cooldown ;
    private boolean search_permission ;
    private int likes ;
    private int dislikes ;
    private int score_to_award ;
    private int pass_word_latest ;
    private LocalDate pass_word_latest_time;
    private int login_fail ;
    private int login_cooldown ;

    public User(int user_id, String user_name, int pass_word, String full_name, LocalDate date_of_birth, boolean gender, String country, String city, String district, String detail_position, String avatar_image_path, String link_app, String link_social, String Favor_fc, String description_text, String uid, int user_role, int namechange_cooldown, boolean search_permission, int likes, int dislikes, int score_to_award, int pass_word_latest, LocalDate pass_word_latest_time, int login_fail, int login_cooldown) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.pass_word = pass_word;
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.district = district;
        this.detail_position = detail_position;
        this.avatar_image_path = avatar_image_path;
        this.link_app = link_app;
        this.link_social = link_social;
        this.Favor_fc = Favor_fc;
        this.description_text = description_text;
        this.uid = uid;
        this.user_role = user_role;
        this.namechange_cooldown = namechange_cooldown;
        this.search_permission = search_permission;
        this.likes = likes;
        this.dislikes = dislikes;
        this.score_to_award = score_to_award;
        this.pass_word_latest = pass_word_latest;
        this.pass_word_latest_time = pass_word_latest_time;
        this.login_fail = login_fail;
        this.login_cooldown = login_cooldown;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public int getPass_word() {
        return pass_word;
    }

    public String getFull_name() {
        return full_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public boolean getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getDetail_position() {
        return detail_position;
    }

    public String getAvatar_image_path() {
        return avatar_image_path;
    }

    public String getLink_app() {
        return link_app;
    }

    public String getLink_social() {
        return link_social;
    }

    public String getFavor_fc() {
        return Favor_fc;
    }

    public String getDescription_text() {
        return description_text;
    }

    public String getUid() {
        return uid;
    }

    public int getUser_role() {
        return user_role;
    }

    public int getNamechange_cooldown() {
        return namechange_cooldown;
    }

    public boolean isSearch_permission() {
        return search_permission;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getScore_to_award() {
        return score_to_award;
    }

    public int getPass_word_latest() {
        return pass_word_latest;
    }

    public LocalDate getPass_word_latest_time() {
        return pass_word_latest_time;
    }

    public int getLogin_fail() {
        return login_fail;
    }

    public int getLogin_cooldown() {
        return login_cooldown;
    }
    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", user_name=" + user_name + ", pass_word=" + pass_word + ", full_name=" + full_name + ", date_of_birth=" + date_of_birth + ", gender=" + gender + ", country=" + country + ", city=" + city + ", district=" + district + ", detail_position=" + detail_position + ", avatar_image_path=" + avatar_image_path + ", link_app=" + link_app + ", link_social=" + link_social + ", Favor_fc=" + Favor_fc + ", description_text=" + description_text + ", uid=" + uid + ", user_role=" + user_role + ", namechange_cooldown=" + namechange_cooldown + ", search_permission=" + search_permission + ", likes=" + likes + ", dislikes=" + dislikes + ", score_to_award=" + score_to_award + ", pass_word_latest=" + pass_word_latest + ", pass_word_latest_time=" + pass_word_latest_time + ", login_fail=" + login_fail + ", login_cooldown=" + login_cooldown + '}';
    }
}