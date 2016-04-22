package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatCounsel;

public class ChatCounselDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chatcounselMapper = new RowMapper() {
		public ChatCounsel mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatCounsel chatcounsel = new ChatCounsel();
			chatcounsel.setCounselSeq(rs.getInt("counsel_seq"));
			chatcounsel.setChatRoomSeq(rs.getInt("chat_room_seq"));
			chatcounsel.setUserId(rs.getString("user_id"));
			chatcounsel.setAgentId(rs.getString("agent_id"));
			chatcounsel.setStartDatetime(rs.getString("start_datetime"));
			chatcounsel.setEndDatetime(rs.getString("end_datetime"));
			chatcounsel.setCounselTime(rs.getInt("counsel_time"));
			chatcounsel.setStatus(rs.getInt("status"));
			chatcounsel.setDialog(rs.getString("dialog"));
			chatcounsel.setRegDate(rs.getString("reg_date"));
			return chatcounsel;
		}
	};
	private RowMapper chatcounselMapper2 = new RowMapper() {
		public ChatCounsel mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatCounsel chatcounsel = new ChatCounsel();
			chatcounsel.setCounselSeq(rs.getInt("counsel_seq"));
			chatcounsel.setChatRoomSeq(rs.getInt("chat_room_seq"));
			chatcounsel.setUserId(rs.getString("user_id"));
			chatcounsel.setAgentId(rs.getString("agent_id"));
			chatcounsel.setStartDatetime(rs.getString("start_datetime"));
			chatcounsel.setEndDatetime(rs.getString("end_datetime"));
			chatcounsel.setCounselTime(rs.getInt("counsel_time"));
			chatcounsel.setStatus(rs.getInt("status"));
			chatcounsel.setDialog(rs.getString("dialog"));
			chatcounsel.setRegDate(rs.getString("reg_date"));
			chatcounsel.setUserName(rs.getString("user_name"));
			chatcounsel.setAgentName(rs.getString("agent_name"));
			return chatcounsel;
		}
	};

	public void addChatCounsel(final ChatCounsel chatcounsel) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_COUNSEL " +
				"	(chat_room_seq, user_id, agent_id, start_datetime, end_datetime, counsel_time, status, dialog, reg_date) " +
				"VALUES " +
				"	(?, ?, ?, SYSDATE(), null, 0, ?, ?, SYSDATE()) ";
		this.jdbcTemplate.update(query, new Object[] {
			chatcounsel.getChatRoomSeq(),
			chatcounsel.getUserId(),
			chatcounsel.getAgentId(),
			chatcounsel.getStatus(),
			chatcounsel.getDialog()
		});
	}

	public void deleteChatCounsel(String counsel_seq) {
		String query = "DELETE FROM T_NF_CHAT_COUNSEL WHERE counsel_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { counsel_seq });
	}

	public void updateChatCounsel(ChatCounsel chatcounsel) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_COUNSEL SET " +
				"	chat_room_seq = ?, " +
				"	user_id = ?, " +
				"	agent_id = ?, " +
				"	start_datetime = ?, " +
				"	end_datetime = ?, " +
				"	counsel_time = ?, " +
				"	status = ?, " +
				"	dialog = ? " +
				"WHERE counsel_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chatcounsel.getChatRoomSeq(),
			chatcounsel.getUserId(),
			chatcounsel.getAgentId(),
			chatcounsel.getStartDatetime(),
			chatcounsel.getEndDatetime(),
			chatcounsel.getCounselTime(),
			chatcounsel.getStatus(),
			chatcounsel.getDialog(),
			chatcounsel.getCounselSeq()
		});
	}
	

	public void finishChatCounsel(ChatCounsel chatcounsel) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_COUNSEL SET " +
				"	end_datetime = SYSDATE(), " +
				"	counsel_time = TIMESTAMPDIFF(MINUTE, start_datetime, end_datetime), " +
				"	status = ?, " +
				"	dialog = ? " +
				"WHERE counsel_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chatcounsel.getStatus(),
			chatcounsel.getDialog(),
			chatcounsel.getCounselSeq()
		});
	}

	public ChatCounsel getChatCounsel(String counsel_seq) {
		String query = "" + 
				"SELECT counsel_seq, chat_room_seq, user_id, agent_id, start_datetime, end_datetime, counsel_time, status, dialog, reg_date " +
				"FROM T_NF_CHAT_COUNSEL " +
				"WHERE counsel_seq = ? ";
		return (ChatCounsel)this.jdbcTemplate.queryForObject(query, new Object[] { counsel_seq }, this.chatcounselMapper);
	}

	public ChatCounsel getRecentChatCounsel(int chatRoomSeq) {
		String query = ""
				+ "	SELECT "
				+ "		c.*, u.user_name, a.user_name as agent_name "
				+ "	FROM "
				+ "		T_NF_CHAT_COUNSEL as c LEFT OUTER JOIN "
				+ "		T_NF_USER as u ON u.user_id = c.user_id LEFT OUTER JOIN "
				+ "		T_NF_USER as a ON a.user_id = c.agent_id "
				+ "	WHERE chat_room_seq = ? "
				+ "	ORDER BY counsel_seq DESC "
				+ "	LIMIT 1 ";
		try {
			return (ChatCounsel)this.jdbcTemplate.queryForObject(query, new Object[] { chatRoomSeq }, this.chatcounselMapper2);
		} catch (Exception e) {
			return new ChatCounsel();
		}
	}

	
	public List<ChatCounsel> getChatCounselList(int page, int itemCountPerPage) {
		String query = ""
				+ "    SELECT * FROM ( "
				+ "        SELECT "
				+ "            c.*, u.user_name, a.user_name as agent_name "
				+ "			FROM "
				+ "				T_NF_CHAT_COUNSEL as c LEFT OUTER JOIN "
				+ "				T_NF_USER as u ON u.user_id = c.user_id LEFT OUTER JOIN "
				+ "				T_NF_USER as a ON a.user_id = c.agent_id "
				+ "			ORDER BY counsel_seq DESC "
				+ "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<ChatCounsel>)this.jdbcTemplate.query(query, this.chatcounselMapper2);
	}
	public int getCount() {
		String query = " SELECT COUNT(*) FROM T_NF_CHAT_COUNSEL ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public List<ChatCounsel> getChatCounselList(int page, int itemCountPerPage, String keyword) {
		String query = ""
				+ "    SELECT * FROM ( "
				+ "        SELECT "
				+ "            c.*, u.user_name, a.user_name as agent_name "
				+ "			FROM "
				+ "				T_NF_CHAT_COUNSEL as c LEFT OUTER JOIN "
				+ "				T_NF_USER as u ON u.user_id = c.user_id LEFT OUTER JOIN "
				+ "				T_NF_USER as a ON a.user_id = c.agent_id "
				+ "     	WHERE (c.user_id like ? OR c.agent_id like ? OR u.user_name like ? OR a.user_name like ? ) "
				+ "			ORDER BY counsel_seq DESC "
				+ "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<ChatCounsel>)this.jdbcTemplate.query(query, new Object[] {"%"+ keyword+"%","%"+ keyword+"%","%"+ keyword+"%","%" + keyword + "%"}, this.chatcounselMapper2);
	}
	public int getCount(String keyword) {
		String query = ""
				+ "	SELECT COUNT(*) "
				+ "	FROM "
				+ "		T_NF_CHAT_COUNSEL as c LEFT OUTER JOIN "
				+ "		T_NF_USER as u ON u.user_id = c.user_id LEFT OUTER JOIN "
				+ "		T_NF_USER as a ON a.user_id = c.agent_id "
				+ "	WHERE (c.user_id like ? OR c.agent_id like ? OR u.user_name like ? OR a.user_name like ? ) ";
		return this.jdbcTemplate.queryForInt(query, new Object[] {"%"+ keyword+"%","%"+ keyword+"%","%"+ keyword+"%","%" + keyword + "%"});
	}

}
