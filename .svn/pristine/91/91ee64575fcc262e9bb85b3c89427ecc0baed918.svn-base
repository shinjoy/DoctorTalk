package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.UserBlood;
import kr.nomad.mars.dto.UserPress;
import kr.nomad.util.T;

public class UPressDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userpressMapper = new RowMapper() {
		public UserPress mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserPress userpress = new UserPress();
			userpress.setPreSeq(rs.getInt("pre_seq"));
			userpress.setUserId(rs.getString("user_id"));
			userpress.setPulse(rs.getInt("pulse"));
			userpress.setSplessure(rs.getInt("splessure"));
			userpress.setDplessure(rs.getInt("dplessure"));
			userpress.setEquip(rs.getInt("equip"));
			userpress.setRegDate(rs.getString("reg_date"));
			userpress.setComment(rs.getString("comment"));
			return userpress;
		}
	};
	private RowMapper userpressMapper2 = new RowMapper() {
		public UserPress mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserPress userpress = new UserPress();
			userpress.setPreSeq(rs.getInt("pre_seq"));
			userpress.setUserId(rs.getString("user_id"));
			userpress.setPulse(rs.getInt("pulse"));
			userpress.setSplessure(rs.getInt("splessure"));
			userpress.setDplessure(rs.getInt("dplessure"));
			userpress.setEquip(rs.getInt("equip"));
			userpress.setRegDate(rs.getString("reg_date"));
	
			return userpress;
		}
	};
	
	//해당 아이디의 해당 년월에  측정 수 
	
	public int getUserPressList(String id,String ym) {
		String query = "SELECT COUNT(*) FROM T_NF_USER_PRESSURE "
					+ "WHERE user_id = ? and DATE_FORMAT(reg_date, '%Y-%m') = ?";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { id , ym });
		
		} catch (Exception e) {
		// TODO: handle exception
		return -1;
		}
	}

		
	//해당 아이디 데이터삭제
	
	public void deleteUserPress(String id) {
		String query = "DELETE FROM T_NF_USER_PRESSURE WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { id  });
		
		
	}
	
	//혈압등록
	
	public void addUserPress(final UserPress userpress) {
		String query = "" +
				"INSERT INTO T_NF_USER_PRESSURE " +
				"	( user_id, pulse, splessure, dplessure, equip, reg_date,comment) " +
				"VALUES " +
				"	( ?, ?, ?, ?, ?, SYSDATE(),?) ";
		this.jdbcTemplate.update(query, new Object[] {
		
			userpress.getUserId(),
			userpress.getPulse(),
			userpress.getSplessure(),
			userpress.getDplessure(),
			userpress.getEquip(),
			userpress.getComment()
		});
	}
	public void updateUserPress(UserPress userpress) {
		String query = "" + 
				"UPDATE T_NF_USER_PRESSURE SET " +
				"	pulse = ?, " +
				"	splessure = ?, " +
				"	dplessure = ?, " +
				"	equip = ? " +
				"WHERE pre_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			userpress.getPulse(),
			userpress.getSplessure(),
			userpress.getDplessure(),
			userpress.getEquip(),
			userpress.getPreSeq()
		});
	}
	//코멘트 입력
	public void updateUserComment(int seq,String comment) {
		String query = "" + 
				"UPDATE T_NF_USER_PRESSURE SET " +
			
				"	comment = ? " +
				"WHERE pre_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {comment,seq});
	}
	//리스트
	
	public List<UserPress> getUserPress(String id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER_PRESSURE " +
				"WHERE user_id = ? order by pre_seq desc";
		return ( List<UserPress>)this.jdbcTemplate.query(query, new Object[] { id }, this.userpressMapper);
	}
	public UserPress getUserPress(String userId, String date) { 
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_PRESSURE "
				+ "	WHERE user_id = ? AND DATE_FORMAT(reg_date, '%Y-%m-%d') = ? ";
		try {
			return (UserPress)this.jdbcTemplate.queryForObject(query, new Object[] { userId, date }, this.userpressMapper);
		} catch (Exception e) {
			return new UserPress();
		}
	}
	public UserPress getUserComment(int Seq) { 
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_PRESSURE "
				+ "	WHERE pre_seq = ? ";
		try {
			return (UserPress)this.jdbcTemplate.queryForObject(query, new Object[] {Seq}, this.userpressMapper);
		} catch (Exception e) {
			return new UserPress();
		}
	}
	/** 유저의 전체 혈압 데이터 갯수 **/
	public int getCount(String userId) {
	    String query = " SELECT COUNT(*) FROM T_NF_USER_PRESSURE WHERE user_id =  ?  ";
	    try{
	    	  return this.jdbcTemplate.queryForInt(query, new Object[] {userId});
	    }catch(Exception e){
	    	return 0;
	    }
	  
	}
	public int getCount(String userId, String date) {
	    String query = " SELECT COUNT(*) FROM T_NF_USER_PRESSURE WHERE user_id = ? AND DATE_FORMAT(reg_date, '%Y-%m-%d') = ? ";
	    return this.jdbcTemplate.queryForInt(query, new Object[] {userId, date});
	}
	
	
	//해당 seq 데이터삭제
	
	public void deleteUserPress(int seq) {
		String query = "DELETE FROM T_NF_USER_PRESSURE WHERE pre_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { seq  });
		
		
	}
	
	//차트 데이터
	public List<UserPress> getUserPress(String id,int page,int itemCountPerPage) { 
			String query = "" 
					+ "	SELECT * FROM ("
					+ "		select "
					+ "			*"
					+ "		from T_NF_USER_PRESSURE "
					+ "		where user_id = ? "
					+ "		ORDER BY reg_date DESC "
					+ ") AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<UserPress>)this.jdbcTemplate.query(query, new Object[] { id}, this.userpressMapper);
	}
	//차트데이터 갯수
	public int getUserPresscnt(String id) { 
			String query = "" + 
					"select COUNT(*) from T_NF_USER_PRESSURE where user_id = ?"; 
			return this.jdbcTemplate.queryForInt(query, new Object[] { id});
	}
	
	//1주 지표 갯수 ///관리주기변경시 확인 
	public int getUserPresscnt(String id,String now,String before) { 
		String query = ""  
					+ " select count(distinct(DATE_FORMAT(reg_date, '%Y-%m-%d'))) "
					+ " from T_NF_USER_PRESSURE  where user_id = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { id,before,now });
	}
	
	//1개월 지표갯수
	
	public int getmonthCount(String userId, String date,String after) {
	    String query = " SELECT count(distinct(DATE_FORMAT(reg_date, '%Y-%m-%d'))) FROM T_NF_USER_PRESSURE WHERE user_id = ?"
	    		+ " AND DATE_FORMAT(reg_date, '%Y-%m-%d') between ? and ? ";
	    return this.jdbcTemplate.queryForInt(query, new Object[] {userId, date,after});
	}
	
	
	
	//타입별 내지표 평균
	public int getUserPressavg(String id,String now,String before,String kind) { 
			String query = "" + 
					"select avg("+kind+") from T_NF_USER_PRESSURE where user_id = ?  and DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ?"; 
			return this.jdbcTemplate.queryForInt(query, new Object[] {id,before,now });
	}
	//타입별 내지표 평균
	public int getUserPressavg(String id,String ym,String kind) { 
			String query = "" + 
					"select avg("+kind+") from T_NF_USER_PRESSURE where user_id = ?  and DATE_FORMAT(reg_date, '%Y-%m') = ?"; 
			return this.jdbcTemplate.queryForInt(query, new Object[] {id,ym });
	}
	
	
		
	//타입별 타인지표 평균
	public int getotherPressavg(String now,String before,String kind) { 
			String query = "" + 
					"select avg("+kind+") from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ?"; 
			return this.jdbcTemplate.queryForInt(query, new Object[] {before,now });
	}
	
	
	
	/*//그래프
	public List<UserPress> getUserPress(String id,String now,String before) { 
			String query = "" + 
					"select top 7 * from T_NF_USER_PRESSURE where user_id = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "; 
			return (List<UserPress>)this.jdbcTemplate.query(query, new Object[] { id,before,now },this.userpressMapper);
	}*/
	
	
	public List<UserPress> getUserPress(String id,String now,String before) { 
		//String query = "select top 7 * from T_NF_USER_BLOOD where user_id = ? and blood_kind = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? ORDER BY reg_date "; 
		String query = ""
				+ "	select 0 as pre_seq, user_id,avg(pulse) as pulse, avg(splessure) as splessure, avg(dplessure) as dplessure, DATE_FORMAT(reg_date, '%Y-%m-%d') as reg_date, 0 as equip "
				+ "	from T_NF_USER_PRESSURE "
				+ "		where user_id = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') between  '"+before+"' and  '"+now+"' "
				+ "	group by user_id, DATE_FORMAT(reg_date, '%Y-%m-%d') "
				+ "	ORDER BY reg_date "; 
		return (List<UserPress>)this.jdbcTemplate.query(query, new Object[] { id },this.userpressMapper2);
	}



}
