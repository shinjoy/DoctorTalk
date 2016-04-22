package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.UserCvrisk;
import kr.nomad.mars.dto.UserMedi;
import kr.nomad.util.T;

public class UCvriskDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper usercvriskMapper = new RowMapper() {
		public UserCvrisk mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserCvrisk usercvrisk = new UserCvrisk();
			usercvrisk.setCvSeq(rs.getInt("cv_seq"));
			usercvrisk.setUserId(rs.getString("user_id"));
			usercvrisk.setCvNum(rs.getInt("cv_num"));
			usercvrisk.setRegDate(rs.getString("reg_date"));
			usercvrisk.setUserTage(rs.getInt("user_tage"));
			usercvrisk.setCol(rs.getInt("col"));
			usercvrisk.setHdl(rs.getInt("hdl"));
			usercvrisk.setSmoke(rs.getInt("smoke"));
			usercvrisk.setSplessure(rs.getInt("splessure"));
			usercvrisk.setDplessure(rs.getInt("dplessure"));
			usercvrisk.setUserAge(rs.getInt("userAge"));
			return usercvrisk;
		}
	};

	//해당아이디의 심혈관위험도리스트(cvrisk)
	
	public List<UserCvrisk> getUserCvriskList(String id) {
		String query = "SELECT * FROM T_NF_USER_CVRISK "
				+ " WHERE user_id = ? order by reg_date desc";
		
		try {
				return (List<UserCvrisk>)this.jdbcTemplate.query(query, new Object[] { id } ,this.usercvriskMapper);
		} catch (Exception e) {
			// TODO: handle exception
				return null;
		}
		
	}
	//가장 최근데이터
	public UserCvrisk getUserCvrisk(String id) {
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_CVRISK "
				+ "	WHERE user_id = ? "
				+ "	ORDER BY reg_date desc "
				+ "	LIMIT 1 ";
		
		try {
				return (UserCvrisk)this.jdbcTemplate.queryForObject(query, new Object[] { id } ,this.usercvriskMapper);
		} catch (Exception e) {
			// TODO: handle exception
				return null;
		}
		
	}

	

	//해당아이디 데이터삭제
	public void deleteUserCvrisk(String id) {
		String query = "DELETE FROM T_NF_USER_CVRISK WHERE user_id = ? ";
		try {
				this.jdbcTemplate.update(query, new Object[] { id });
		} catch (Exception e) {
			
		}
		
	}
	//등록
	public void addUserCvrisk(UserCvrisk usercvrisk) {
		String query = "" +
				"INSERT INTO T_NF_USER_CVRISK " +
				"	(user_id, cv_num, reg_date ,user_tage, col, hdl, splessure, dplessure, smoke, userAge) " +
				"VALUES " +
				"	( ?, ?, SYSDATE(),?,?,?,?,?,?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			usercvrisk.getUserId(),
			usercvrisk.getCvNum(),
			usercvrisk.getUserTage(),
			usercvrisk.getCol(),
			usercvrisk.getHdl(),
			usercvrisk.getSplessure(),
			usercvrisk.getDplessure(),
			usercvrisk.getSmoke(),
			usercvrisk.getUserAge()
		});
	}
	
	public int getUserCvcount(String id,String regDate){
		String query = "SELECT count(*) FROM T_NF_USER_CVRISK WHERE user_id = ? AND DATE_FORMAT(reg_date, '%Y-%m-%d') = ? ";
		
		try{
			return this.jdbcTemplate.queryForInt(query , new Object[] {id,regDate});
		}catch(Exception e){
			return 0;
		}
		
		
	}
	
	//유저의 전체 cv_risk 데이터
	public List<UserCvrisk> getUserCvriskAdmin(String userId) { 
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER_CVRISK " +
				"WHERE user_id = ? ORDER BY reg_date DESC ";
		return (List<UserCvrisk>)this.jdbcTemplate.query(query, new Object[] { userId }, this.usercvriskMapper);
	}
	
	
	
	
	/** 유저의 전체 cv_risk 데이터 갯수 **/
	public int getCount(String userId) {
	    String query = " SELECT COUNT(*) FROM T_NF_USER_CVRISK WHERE user_id =  ?  ";
	    return this.jdbcTemplate.queryForInt(query, new Object[] {userId});
	}
	
	
	
	
/*	public void deleteUserCvrisk(String cv_seq) {
		String query = "DELETE FROM T_NF_USER_CVRISK WHERE cv_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { cv_seq });
	}

	public void updateUserCvrisk(UserCvrisk usercvrisk) { 
		String query = "" + 
				"UPDATE T_NF_USER_CVRISK SET " +
				"	cv_seq = ?, " +
				"	user_id = ?, " +
				"	cv_num = ?, " +
				"	reg_date = ? " +
				"WHERE cv_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			usercvrisk.getCvSeq(),
			usercvrisk.getUserId(),
			usercvrisk.getCvNum(),
			usercvrisk.getRegDate()
		});
	}

	public UserCvrisk getUserCvrisk(String cv_seq) {
		String query = "" + 
				"SELECT cv_seq, user_id, cv_num, reg_date " +
				"FROM T_NF_USER_CVRISK " +
				"WHERE cv_seq = ? ";
		return (UserCvrisk)this.jdbcTemplate.queryForObject(query, new Object[] { cv_seq }, this.usercvriskMapper);
	}

	*/
	
	

}
