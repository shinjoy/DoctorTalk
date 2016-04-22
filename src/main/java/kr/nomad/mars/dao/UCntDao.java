package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.UserBlood;
import kr.nomad.mars.dto.UserCnt;
import kr.nomad.mars.dto.UserGoal;
import kr.nomad.util.T;


public class UCntDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper usercntMapper = new RowMapper() {
		public UserCnt mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserCnt usercnt = new UserCnt();
			usercnt.setUserId(rs.getString("user_id"));
			usercnt.setBcnt(rs.getInt("bcnt"));
			usercnt.setPcnt(rs.getInt("pcnt"));
			usercnt.setRegDate(rs.getString("reg_date"));
			usercnt.setCntSeq(rs.getInt("cnt_seq"));
			usercnt.setWcnt(rs.getInt("wcnt"));
			usercnt.setBloodStatus(rs.getInt("blood_status"));
			usercnt.setPressStatus(rs.getInt("press_status"));
			return usercnt;
		}
	};
	
	//해당년월의 해당아이디에 관리주기목표 갯수
	
	public UserCnt getUserCntList(String id,String ym) {
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_CNT "
				+ "	WHERE user_id = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') = ? "
				+ "	order by cnt_seq desc "
				+ "	LIMIT 1 ";
		
		try {
			return (UserCnt)this.jdbcTemplate.queryForObject(query, new Object[] { id , ym },this.usercntMapper);
		
		} catch (Exception e) {
		// TODO: handle exception
			return null;
		}
		
	}
	
	public UserCnt getUserCntList(String id) {
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_CNT "
				+ "	WHERE user_id = ? "
				+ "	order by cnt_seq desc"
				+ " LIMIT 1 ";
		
		try {
			return (UserCnt)this.jdbcTemplate.queryForObject(query, new Object[] { id },this.usercntMapper);
		
		} catch (Exception e) {
		// TODO: handle exception
			return new UserCnt();
		}
		
	}
	

	//해당아이디 데이터삭제
	public void deleteUserCnt(String id) {
		String query = "DELETE FROM T_NF_USER_CNT WHERE user_id = ? ";
		try {
				this.jdbcTemplate.update(query, new Object[] { id });
		} catch (Exception e) {
			
		}
		
	}

	public void addUserCnt(UserCnt usercnt) {
		String query = "" +
				"INSERT INTO T_NF_USER_CNT " +
				"	(user_id, bcnt, pcnt, reg_date, wcnt, blood_status, press_status) " +
				"VALUES " +
				"	(?, ?, ?, SYSDATE(), ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			usercnt.getUserId(),
			usercnt.getBcnt(),
			usercnt.getPcnt(),
			usercnt.getWcnt(),
			usercnt.getBloodStatus(),
			usercnt.getPressStatus()
		});
	}
	//혈당업데이트
	public void upUserbCnt(String id, int num) {
		String query = "" +
				"update T_NF_USER_CNT set bcnt=? , blood_status =1 where cnt_seq=(SELECT MAX(cnt_seq) FROM T_NF_USER_CNT) and user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			num,id
		});
	}
	//혈압업데이트
	public void upUserpCnt(String id, double num) {
		String query = "" +
				"update T_NF_USER_CNT set pcnt= ? , press_status =1 where cnt_seq=(SELECT MAX(cnt_seq) FROM T_NF_USER_CNT) and user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			num,id
		});
	}
	
/*	//기간데이터
	public List<UserBlood> getUserBlood(String id,String now,String before) { 
		String query = "" + 
				"select * from T_NF_USER_BLOOD where user_id = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ?"; 
		return (List<UserBlood>)this.jdbcTemplate.query(query, new Object[] { id,before,now }, this.userbloodMapper);
	}*/

	
	/*

	public void updateUserCnt(UserCnt usercnt) { 
		String query = "" + 
				"UPDATE T_NF_USER_CNT SET " +
				"	user_id = ?, " +
				"	bcnt = ?, " +
				"	pcnt = ?, " +
				"	reg_date = ?, " +
				"	cnt_seq = ?, " +
				"	wcnt = ? " +
				"WHERE cnt_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			usercnt.getUserId(),
			usercnt.getBcnt(),
			usercnt.getPcnt(),
			usercnt.getRegDate(),
			usercnt.getCntSeq(),
			usercnt.getWcnt()
		});
	}

	public UserCnt getUserCnt(String cnt_seq) {
		String query = "" + 
				"SELECT user_id, bcnt, pcnt, reg_date, cnt_seq, wcnt " +
				"FROM T_NF_USER_CNT " +
				"WHERE cnt_seq = ? ";
		return (UserCnt)this.jdbcTemplate.queryForObject(query, new Object[] { cnt_seq }, this.usercntMapper);
	}

	public List<UserCnt> getUserCntList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" user_id, bcnt, pcnt, reg_date, cnt_seq, wcnt " +
				"FROM T_NF_USER_CNT " +
				"WHERE cnt_seq <= ( " +
				"	SELECT MIN(cnt_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" cnt_seq " +
				"		FROM T_NF_USER_CNT " +
				"		ORDER BY cnt_seq DESC " +
				"	) AS A) " +
				"ORDER BY cnt_seq DESC";
		return (List<UserCnt>)this.jdbcTemplate.query(query, this.usercntMapper);
	}*/
	
	
	//////// 관리자
	
	public List<UserCnt> getUserCnt(String user_id) {
		String query = "" + 
				"SELECT *  " +
				"FROM T_NF_USER_CNT " +
				"WHERE user_id = ? ORDER BY reg_date DESC ";
		return (List<UserCnt>)this.jdbcTemplate.query(query, new Object[] { user_id }, this.usercntMapper);
	}
	
	//기간갯수
	public int getCount(String user_id) { 
				String query = "" 
						+ "SELECT count(*)  "
						+"from T_NF_USER_CNT where user_id = ?";
			
				
			return this.jdbcTemplate.queryForInt(query, new Object[] { user_id });
	}
	
	
	//해당 아이디 최근데이터 하나만 가지고 오기
	public UserCnt UserCnt(String userId) {
		String query = ""
				+ "	SELECT  * "
				+ "	FROM T_NF_USER_CNT "
				+ "	WHERE user_id = ? "
				+ "	ORDER BY reg_date desc"
				+ "	LIMIT 1 ";
		try {
			return (UserCnt)this.jdbcTemplate.queryForObject(query, new Object[] { userId } ,this.usercntMapper);
		} catch (Exception e) {
			 return new UserCnt();
		}
		
	}	
	



}
