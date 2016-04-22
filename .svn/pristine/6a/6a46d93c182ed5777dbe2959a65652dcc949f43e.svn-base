package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Counsel;



public class CounselDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper counselMapper = new RowMapper() {
		public Counsel mapRow(ResultSet rs, int rowNum) throws SQLException {
			Counsel counsel = new Counsel();
			counsel.setCounselorSeq(rs.getInt("counselor_seq"));
			counsel.setUserId(rs.getString("user_id"));
			counsel.setCounselDate(rs.getString("counsel_date"));
			counsel.setCounselTime(rs.getString("counsel_time"));
			counsel.setRegDate(rs.getString("reg_date"));
			counsel.setUserName(rs.getString("user_name"));
			counsel.setFileName(rs.getString("file_name"));
			counsel.setGender(rs.getInt("gender"));
			counsel.setBirthday(rs.getString("birthday"));
			counsel.setBlood(rs.getInt("blood"));
			counsel.setPress(rs.getInt("press"));
			counsel.setCol(rs.getInt("col"));
			counsel.setHeiwieght(rs.getInt("heiwieght"));
			counsel.setIsNow(rs.getInt("is_now"));
			counsel.setStatus(rs.getInt("status"));
			counsel.setChatRoomSeq(rs.getInt("chat_room_seq"));
			return counsel;
		}
	};
	//상담예약
	public void addcounsel(Counsel counsel) {
		String query = "" +
				"INSERT INTO T_NF_COUNSEL " +
				"	(user_id, counsel_date, counsel_time, reg_date, is_now) " +
				"VALUES " +
				"	(?, ?, ?, SYSDATE(), 0) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			counsel.getUserId(),
			counsel.getCounselDate(),
			counsel.getCounselTime()
		
		});
	}
	//상담요청(즉시)
	public void addcounselNow(Counsel counsel) {
		String query = "" +
				"INSERT INTO T_NF_COUNSEL " +
				"	(user_id, reg_date, is_now, status) " +
				"VALUES " +
				"	(?, SYSDATE(), 1, 0) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			counsel.getUserId()
		
		});
	}
	
	
	
	//정보가져오기
	public int getcounsel(String user_id,String starttime,String endtime) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_COUNSEL " +
				"WHERE user_id = ? and reg_date between  ?  and  ?  ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { user_id,starttime,endtime });
	}

	public void deletecounsel(int counselor_seq) {
		String query = "DELETE FROM T_NF_COUNSEL WHERE counselor_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { counselor_seq });
	}
	public void deletecounsel(String userId) {
		String query = "DELETE FROM T_NF_COUNSEL WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}

	public void updatecounsel(Counsel counsel) { 
		String query = "" + 
				"UPDATE T_NF_COUNSEL SET " +
				"	counsel_date = ?, " +
				"	counsel_time = ? " +
				"WHERE counselor_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			counsel.getCounselDate(),
			counsel.getCounselTime(),
			counsel.getCounselorSeq()
		});
	}

	public Counsel getcounsel(int counselor_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_COUNSEL " +
				"WHERE counselor_seq = ? ";
		return (Counsel)this.jdbcTemplate.queryForObject(query, new Object[] { counselor_seq }, this.counselMapper);
	}
	
	public Counsel getcounsel(String user_id) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_COUNSEL " +
				"WHERE user_id = ? ";
		try {
			return (Counsel)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.counselMapper);
		} catch (Exception e) {
			return new Counsel();
		}
	}
	public Counsel getcounselByChatRoomSeq(int chatRoomSeq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_COUNSEL " +
				"WHERE chat_room_seq = ? ";
		try {
			return (Counsel)this.jdbcTemplate.queryForObject(query, new Object[] { chatRoomSeq }, this.counselMapper);
		} catch (Exception e) {
			return new Counsel();
		}
	}
	
	public int getcounselrev(String user_id) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_COUNSEL " +
				"WHERE user_id = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { user_id });
	}


	public List<Counsel> getcounselList(int page, int itemCountPerPage) {
		String query = ""
	            + "    SELECT * FROM ( "
	            + "         SELECT "
	            + "            * "
	            + "         FROM V_NF_COUNSEL "
	            + "			ORDER BY counselor_seq DESC "
	            + "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<Counsel>)this.jdbcTemplate.query(query, this.counselMapper);
	}
	
	public List<Counsel> getcounselList() {
		String query = ""
	            + " SELECT * "
	            + " FROM V_NF_COUNSEL ";
		return (List<Counsel>)this.jdbcTemplate.query(query, this.counselMapper);
	}
	
	

}
