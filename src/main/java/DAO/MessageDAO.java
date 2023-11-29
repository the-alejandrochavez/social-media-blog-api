package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;

public class MessageDAO {
    public Message createMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println(message);
            if(rs.next()){
                int message_id = (int) rs.getLong(1);
                return new Message(message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // public Message getMessageById(int id){
    //     Connection connection = ConnectionUtil.getConnection();
    //     try {
    //         String sql = "SELECT * FROM message WHERE message_id = ?";

    //         PreparedStatement ps = connection.prepareStatement(sql);
    //         ps.setInt(1, id);

    //         ResultSet rs = ps.executeQuery();
    //         while(rs.next()){
    //             Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
    //                     rs.getString("message_text"), rs.getLong("time_posted_epoch"));
    //             return message;
    //         }
    //     }catch(SQLException e){
    //         System.out.println(e.getMessage());
    //     }
    //     return null;
    // }
}