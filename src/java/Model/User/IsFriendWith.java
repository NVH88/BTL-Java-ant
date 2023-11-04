/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

/**
 *
 * @author admin
 */
public class IsFriendWith {
    private int user_id_1 , user_id_2 ;

    public int getUser_id_1() {
        return user_id_1;
    }

    public int getUser_id_2() {
        return user_id_2;
    }

    @Override
    public String toString() {
        return "IsFriendWith{" + "user_id_1=" + user_id_1 + ", user_id_2=" + user_id_2 + '}';
    }

    public IsFriendWith(int user_id_1, int user_id_2) {
        this.user_id_1 = user_id_1;
        this.user_id_2 = user_id_2;
    }
}
