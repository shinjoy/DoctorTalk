package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.UserMedi;
import kr.nomad.mars.dto.UserPres;
import kr.nomad.util.T;

public class UPresDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userpresMapper = new RowMapper() {
		public UserPres mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserPres userpres = new UserPres();
			userpres.setPreSeq(rs.getInt("pre_seq"));
			userpres.setComment(rs.getString("comment"));
			userpres.setHosName(rs.getString("hos_name"));
			userpres.setUserId(rs.getString("user_id"));
			userpres.setFileName(rs.getString("file_name"));
			userpres.setRegDate(rs.getString("reg_date"));
			return userpres;
		}
	};
	
	//해당아이디의 처방리스트
	
	public List<UserPres> getUserPresList(String id,int page,int itemCountPerPage) {
				
		String query = ""
				+ "	SELECT * FROM ("
				+ "		SELECT "
				+ "			* "
				+ "		FROM T_NF_USER_PRESCRIPTION "
				+ "		WHERE user_id = ? "
				+ "		ORDER BY reg_date DESC "
				+ ") AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		try {
			return (List<UserPres>)this.jdbcTemplate.query(query, new Object[] { id } ,this.userpresMapper);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}		
	}
	
	//갯수
	public int getUserPresCnt(String id) {
				
		String query = ""
				+ "SELECT count(*) "
				+ "FROM T_NF_USER_PRESCRIPTION WHERE user_id = ? ";
			
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { id } );
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}		
		
	}
	
	//처방전 신규 등록

	public void addUserPres(UserPres userpres) {
		String query = "" +
				"INSERT INTO T_NF_USER_PRESCRIPTION " +
				"	(comment, hos_name, user_id, file_name, reg_date) " +
				"VALUES " +
				"	( ?, ?, ?, ?, SYSDATE()) ";
		this.jdbcTemplate.update(query, new Object[] {
			userpres.getComment(),
			userpres.getHosName(),
			userpres.getUserId(),
			userpres.getFileName()
			
		});
	}
	
	//처방전 수정
	
	public void updateUserPres(UserPres userpres) { 
		String query = "" + 
				"UPDATE T_NF_USER_PRESCRIPTION SET " +
	
				"	comment = ?, " +
				"	hos_name = ?, " +
				"	file_name = ? " +
			
				"WHERE pre_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
		
			userpres.getComment(),
		
			userpres.getHosName(),
			userpres.getFileName(),
			userpres.getPreSeq(),
		
		});
	}
	

	//처방전 삭제
	public void deleteUserPres(int pre_seq) {
		String query = "DELETE FROM T_NF_USER_PRESCRIPTION WHERE pre_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { pre_seq });
	}

	
	
	//하나의 처방전
	
	
	public UserPres UserPresOne(int pre_seq) {
				
		String query = "SELECT * FROM T_NF_USER_PRESCRIPTION "
						+ "WHERE pre_seq = ? ";
				
		return (UserPres)this.jdbcTemplate.queryForObject(query, new Object[] { pre_seq } ,this.userpresMapper);
	}
	
	//유저 데이터 삭제
	
	public void deleteUserPres2(String id) {
		String query = "DELETE FROM T_NF_USER_PRESCRIPTION WHERE user_id = ? ";
		try {
				this.jdbcTemplate.update(query, new Object[] { id });
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<UserPres> getUserPresList(String id) {
		
		String query = ""
			
				+"SELECT "
				+ "* "
				+ "FROM T_NF_USER_PRESCRIPTION WHERE user_id = ? ";
			
		try {
			return (List<UserPres>)this.jdbcTemplate.query(query, new Object[] { id } ,this.userpresMapper);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}		
		
	}
	
	/*public List<UserPres> getUserPresList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" pre_seq, comment, hos_name, user_id, file_name, reg_date " +
				"FROM T_NF_USER_PRESCRIPTION " +
				"WHERE pre_seq <= ( " +
				"	SELECT MIN(pre_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" pre_seq " +
				"		FROM T_NF_USER_PRESCRIPTION " +
				"		ORDER BY pre_seq DESC " +
				"	) AS A) " +
				"ORDER BY pre_seq DESC";
		return (List<UserPres>)this.jdbcTemplate.query(query, this.userpresMapper);
		
	}
	
	

	public UserPres getUserPres(String pre_seq) {
		String query = "" + 
				"SELECT pre_seq, comment, hos_name, user_id, file_name, reg_date " +
				"FROM T_NF_USER_PRESCRIPTION " +
				"WHERE pre_seq = ? ";
		return (UserPres)this.jdbcTemplate.queryForObject(query, new Object[] { pre_seq }, this.userpresMapper);
	}*/

}
