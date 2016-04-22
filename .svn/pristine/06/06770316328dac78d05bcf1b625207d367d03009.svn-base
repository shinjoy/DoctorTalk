package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatRoom;
import kr.nomad.mars.dto.Counsel;

public class ChatRoomDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chatroomMapper = new RowMapper() {
		public ChatRoom mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatRoom chatroom = new ChatRoom();
			chatroom.setChatRoomSeq(rs.getInt("chat_room_seq"));
			chatroom.setChatRoomType(rs.getInt("chat_room_type"));
			chatroom.setGroupSeq(rs.getInt("group_seq"));
			chatroom.setGroupType(rs.getInt("group_type"));
			chatroom.setLastMsgSeq(rs.getString("last_msg_seq"));
			chatroom.setOwnerId(rs.getString("owner_id"));
			chatroom.setOwnerName(rs.getString("owner_name"));
			chatroom.setRegDate(rs.getString("reg_date"));
			chatroom.setReplyId(rs.getString("reply_id"));
			chatroom.setReplyName(rs.getString("reply_name"));
			chatroom.setTarget(rs.getString("target"));
			chatroom.setTitle(rs.getString("title"));
			return chatroom;
		}
	};
	public void addChatRoom(ChatRoom room) {
		String query = ""
				+ "	INSERT INTO T_NF_CHAT_ROOM "
				+ "		(owner_id, reg_date) "
				+ "	VALUES "
				+ "		(?, SYSDATE()) ";
		this.jdbcTemplate.update(query, new Object[] {
			room.getOwnerId()
		});
	}
	
	public void deleteChatRoom(int chatRoomSeq) {
	    String query = "DELETE FROM T_NF_CHAT_ROOM WHERE chat_room_seq = ? ";
	    this.jdbcTemplate.update(query, new Object[] { chatRoomSeq } );
	}
	
	public int getLastId() {
	    String query = "SELECT LAST_INSERT_ID(); ";
	    return this.jdbcTemplate.queryForInt(query );
	}
	
	public ChatRoom getChatRoom(int chat_room_seq) {
		String query = ""
				+ "	SELECT r.*, o.user_name as owner_name, p.user_name as reply_name "
				+ "	FROM "
				+ "		T_NF_CHAT_ROOM as r "
				+ "		LEFT OUTER JOIN T_NF_USER as o ON o.user_id = r.owner_id "
				+ "		LEFT OUTER JOIN T_NF_USER as p ON p.user_id = r.reply_id "
				+ "	WHERE chat_room_seq = ? ";
		try {
			return (ChatRoom)this.jdbcTemplate.queryForObject(query, new Object[] { chat_room_seq }, this.chatroomMapper);
		} catch (Exception e) {
			return new ChatRoom();
		}
	}
	public ChatRoom getChatRoom(String userId) {
		String query = ""
				+ "	SELECT r.*, o.user_name as owner_name, p.user_name as reply_name "
				+ "	FROM "
				+ "		T_NF_CHAT_ROOM as r "
				+ "		LEFT OUTER JOIN T_NF_USER as o ON o.user_id = r.owner_id "
				+ "		LEFT OUTER JOIN T_NF_USER as p ON p.user_id = r.reply_id "
				+ "	WHERE r.owner_id = ? ";
		try {
			return (ChatRoom)this.jdbcTemplate.queryForObject(query, new Object[] { userId }, this.chatroomMapper);
		} catch (Exception e) {
			return new ChatRoom();
		}
	}

	public List<ChatRoom> getChatRoomList(int page, int itemCountPerPage) {
		String query = ""  
				+ "	SELECT * FROM ("
				+ "		SELECT"
				+ "			r.*, o.user_name as owner_name, p.user_name as reply_name "
				+ "		FROM "
				+ "			T_NF_CHAT_ROOM as r "
				+ "			LEFT OUTER JOIN T_NF_USER as o ON o.user_id = r.owner_id "
				+ "			LEFT OUTER JOIN T_NF_USER as p ON p.user_id = r.reply_id "
				+ "		ORDER BY r.reg_date DESC "
				+ ") AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<ChatRoom>)this.jdbcTemplate.query(query, this.chatroomMapper);
	}
	public int getChatRoomCount() {
		String query = " SELECT COUNT(*) FROM T_NF_CHAT_ROOM ";
		return this.jdbcTemplate.queryForInt(query);
	}
	public int getChatRoomCount(String userId) {
		String query = " SELECT COUNT(*) FROM T_NF_CHAT_ROOM WHERE owner_id = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { userId});
	}
}
