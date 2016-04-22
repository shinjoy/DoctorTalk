package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatMember;

public class ChatMemberDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chatmemberMapper = new RowMapper() {
		public ChatMember mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatMember chatmember = new ChatMember();
			chatmember.setChatMemberSeq(rs.getInt("chat_member_seq"));
			chatmember.setChatRoomSeq(rs.getInt("chat_room_seq"));
			chatmember.setGroupId(rs.getString("group_id"));
			chatmember.setNotification(rs.getInt("notification"));
			chatmember.setRegDate(rs.getString("reg_date"));
			chatmember.setUserId(rs.getString("user_id"));
			return chatmember;
		}
	};

	public void addChatMember(final ChatMember chatmember) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_MEMBER " +
				"	(chat_room_seq, reg_date, user_id) " +
				"VALUES " +
				"	(?, SYSDATE(), ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			chatmember.getChatRoomSeq(),
			chatmember.getUserId()
		});
	}

	public void deleteChatMember(int chat_room_seq) {
		String query = "DELETE FROM T_NF_CHAT_MEMBER WHERE chat_room_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { chat_room_seq });
	}

	public void deleteChatMemberById(String userId) {
		String query = "DELETE FROM T_NF_CHAT_MEMBER WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}

	public void updateChatMember(ChatMember chatmember) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_MEMBER SET " +
				"	chat_member_seq = ?, " +
				"	chat_room_seq = ?, " +
				"	group_id = ?, " +
				"	notification = ?, " +
				"	reg_date = ?, " +
				"	user_id = ? " +
				"WHERE chat_member_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chatmember.getChatMemberSeq(),
			chatmember.getChatRoomSeq(),
			chatmember.getGroupId(),
			chatmember.getNotification(),
			chatmember.getRegDate(),
			chatmember.getUserId()
		});
	}
	
	public int getCount(int chatRoomSeq) {
		String query = " SELECT count(*) FROM T_NF_CHAT_MEMBER WHERE chat_room_seq = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { chatRoomSeq });
	}

	public ChatMember getChatMember(String chatRoomSeq) {
		String query = "" + 
				"SELECT m.*, u.user_name " +
				"FROM T_NF_CHAT_MEMBER as m LEFT OUTER JOIN T_NF_USER as u ON u.user_id = m.user_id " +
				"WHERE m.chat_room_seq = ? ";
		return (ChatMember)this.jdbcTemplate.queryForObject(query, new Object[] { chatRoomSeq }, this.chatmemberMapper);
	}

	public List<ChatMember> getChatMemberList(int chatRoomSeq) {
		String query = "" +
				"SELECT chat_member_seq, chat_room_seq, group_id, notification, reg_date, user_id " +
				"FROM T_NF_CHAT_MEMBER " +
				"WHERE chat_room_seq = ? ";
		return (List<ChatMember>)this.jdbcTemplate.query(query, new Object[] { chatRoomSeq }, this.chatmemberMapper);
	}
	
	public ChatMember getChatMe(int chatRoomSeq, String userId) {
		String query = ""
				+ "	SELECT m.*, u.user_name "
				+ "	FROM T_NF_CHAT_MEMBER as m LEFT OUTER JOIN T_NF_USER as u ON u.user_id = m.user_id "
				+ "	WHERE m.chat_room_seq = ? and u.user_id = ? ";
		try {
			return (ChatMember)this.jdbcTemplate.queryForObject(query, new Object[] { chatRoomSeq, userId }, this.chatmemberMapper);
		} catch (Exception e) {
			return new ChatMember();
		}
	}

	public ChatMember getChatCounselor(int chatRoomSeq) {
		String query = ""
				+ "	SELECT m.*, u.user_name "
				+ "	FROM T_NF_CHAT_MEMBER as m LEFT OUTER JOIN T_NF_USER as u ON u.user_id = m.user_id "
				+ "	WHERE m.chat_room_seq = ? and u.user_type = 2 "
				+ "	LIMIT 1 ";
		try {
			return (ChatMember)this.jdbcTemplate.queryForObject(query, new Object[] { chatRoomSeq }, this.chatmemberMapper);
		} catch (Exception e) {
			return new ChatMember();
		}
	}
	public ChatMember getChatNormalCounselor(int chatRoomSeq, String defaultCounselor) {
		String query = ""
				+ "	SELECT m.*, u.user_name "
				+ "	FROM T_NF_CHAT_MEMBER as m LEFT OUTER JOIN T_NF_USER as u ON u.user_id = m.user_id "
				+ "	WHERE m.chat_room_seq = ? and u.user_type = 2 and u.user_id <> ? ";
		try {
			return (ChatMember)this.jdbcTemplate.queryForObject(query, new Object[] { chatRoomSeq, defaultCounselor }, this.chatmemberMapper);
		} catch (Exception e) {
			return null;
		}
	}

}
