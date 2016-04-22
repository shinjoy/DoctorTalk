package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.ChatScript;
import kr.nomad.mars.dto.Counsel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ChatScriptDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chatscriptMapper = new RowMapper() {
		public ChatScript mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatScript chatscript = new ChatScript();
			chatscript.setScSeq(rs.getInt("sc_seq"));
			chatscript.setScript(rs.getString("script"));
			return chatscript;
		}
	};

	public void addChatScript(final ChatScript chatscript) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_SCRIPT " +
				"	(script) " +
				"VALUES " +
				"	(?) ";
		this.jdbcTemplate.update(query, new Object[] {
			chatscript.getScript()
		});
	}

	public void deleteChatScript(String sc_seq) {
		String query = "DELETE FROM T_NF_CHAT_SCRIPT WHERE sc_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { sc_seq });
	}

	public void updateChatScript(ChatScript chatscript) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_SCRIPT SET " +
				"	script = ? " +
				"WHERE sc_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chatscript.getScript(),
			chatscript.getScSeq()
		});
	}

	public ChatScript getChatScript(String sc_seq) {
		String query = "" + 
				"SELECT sc_seq, script " +
				"FROM T_NF_CHAT_SCRIPT " +
				"WHERE sc_seq = ? ";
		return (ChatScript)this.jdbcTemplate.queryForObject(query, new Object[] { sc_seq }, this.chatscriptMapper);
	}

	public List<ChatScript> getScriptList() {
		String query = ""
	            + " SELECT * "
	            + " FROM T_NF_CHAT_SCRIPT ";
		return (List<ChatScript>)this.jdbcTemplate.query(query, this.chatscriptMapper);
	}

	public List<ChatScript> getScriptList(String keyword) {
		String query = ""
	            + " SELECT * "
	            + " FROM T_NF_CHAT_SCRIPT "
	            + "	WHERE script like ? ";
		return (List<ChatScript>)this.jdbcTemplate.query(query, new Object[] { "%"+keyword+"%" }, this.chatscriptMapper);
	}
	
}
