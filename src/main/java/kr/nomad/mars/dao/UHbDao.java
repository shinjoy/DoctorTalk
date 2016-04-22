package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.UserCol;
import kr.nomad.mars.dto.Userhb;
import kr.nomad.util.T;


public class UHbDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userhbMapper = new RowMapper() {
		public Userhb mapRow(ResultSet rs, int rowNum) throws SQLException {
			Userhb userhb = new Userhb();
			userhb.setHbaSeq(rs.getInt("hba_seq"));
			userhb.setUserId(rs.getString("user_id"));
			userhb.setHbaNum(rs.getDouble("hba_num"));
			userhb.setRegDate(rs.getString("reg_date"));
			userhb.setComment(rs.getString("comment"));
			return userhb;
		}
	};
	private RowMapper userhbMapper2 = new RowMapper() {
		public Userhb mapRow(ResultSet rs, int rowNum) throws SQLException {
			Userhb userhb = new Userhb();
			userhb.setHbaSeq(rs.getInt("hba_seq"));
			userhb.setUserId(rs.getString("user_id"));
			userhb.setHbaNum(rs.getDouble("hba_num"));
			userhb.setRegDate(rs.getString("reg_date"));
		
			return userhb;
		}
	};
	//당화혈색소등록
	
	public void addUserhb(Userhb userhb) {
		String query = "" +
				"INSERT INTO T_NF_USER_HBA1C " +
				"	(user_id, hba_num, reg_date,comment) " +
				"VALUES " +
				"	(?, ?, SYSDATE(),?) ";
		this.jdbcTemplate.update(query, new Object[] {
		
			userhb.getUserId(),
			userhb.getHbaNum(),
			userhb.getComment()
		
		});
	}
	
	public Userhb getUserComment(int Seq) { 
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_HBA1C "
				+ "	WHERE hba_seq = ? ";
		try {
			return (Userhb)this.jdbcTemplate.queryForObject(query, new Object[] {Seq}, this.userhbMapper);
		} catch (Exception e) {
			return new Userhb();
		}
	}
	
	
	public void updateUserhb(Userhb userhb) {
		String query = "" + 
				"UPDATE T_NF_USER_HBA1C SET " +
				"	hba_num = ? " +
				"WHERE hba_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			userhb.getHbaNum(),
			userhb.getHbaSeq()
		});
	}
	public void updateUserComment(int seq,String comment) {
		String query = "" + 
				"UPDATE T_NF_USER_HBA1C SET " +
				"  comment= ? "+
				"WHERE hba_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {comment,seq});
	}
	
	//해당데이터삭제
	public void deleteUserhb(String id) {
		String query = "DELETE FROM T_NF_USER_HBA1C WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { id });
	}
	
	//해당 아이디 최근데이터 하나만 가지고 오기
	public Userhb Userhb(String userId) {
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_HBA1C "
				+ "	WHERE user_id = ? "
				+ "	ORDER BY reg_date desc "
				+ "	LIMIT 1 ";
		try {
			return (Userhb)this.jdbcTemplate.queryForObject(query, new Object[] { userId } ,this.userhbMapper);
		} catch (Exception e) {
			 return new Userhb();
		}
		
	}	
	

	public int getCount(String userId, String date) {
	    String query = " SELECT COUNT(*) FROM T_NF_USER_HBA1C WHERE user_id =  ? AND DATE_FORMAT(reg_date, '%Y-%m-%d') = ?  ";
	    return this.jdbcTemplate.queryForInt(query, new Object[] { userId, date });
	}
	public List<Userhb> getUserhb(String id) {
		String query = "" + 
				"SELECT *  " +
				"FROM T_NF_USER_HBA1C " +
				"WHERE user_id = ? ORDER BY reg_date DESC ";
		return (List<Userhb>)this.jdbcTemplate.query(query, new Object[] { id }, this.userhbMapper);
	}
	public Userhb getUserhb(String userId, String date) { 
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_HBA1C "
				+ "	WHERE user_id = ? AND DATE_FORMAT(reg_date, '%Y-%m-%d') = ? ";
		try {
			return (Userhb)this.jdbcTemplate.queryForObject(query, new Object[] { userId, date }, this.userhbMapper);
		} catch (Exception e) {
			return new Userhb();
		}
	}

	public Userhb getUserhbRecent(String userId) { 
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_USER_HBA1C "
				+ "	WHERE user_id = ? "
				+ "	ORDER BY reg_date DESC "
				+ " LIMIT 1 ";
		try {
			return (Userhb)this.jdbcTemplate.queryForObject(query, new Object[] { userId }, this.userhbMapper);
		} catch (Exception e) {
			return new Userhb();
		}
	}

	//해당seq 데이터삭제
	public void deleteUserhb(int seq) {
		String query = "DELETE FROM T_NF_USER_HBA1C WHERE hba_seq= ? ";
		this.jdbcTemplate.update(query, new Object[] { seq });
	}	
	
	//차트 데이터
	public List<Userhb> getUserHba(String id,int page,int itemCountPerPage) { 
			String query = ""  
					+ "	SELECT * FROM ("
					+ "		select"
					+ "			* "
					+ "		from T_NF_USER_HBA1C "
					+ "		where user_id = ? "
					+ "		ORDER BY reg_date DESC "
					+ ") AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
					
				 
		return (List<Userhb>)this.jdbcTemplate.query(query, new Object[] { id}, this.userhbMapper);
	}
	// 평균
	public Double getUseravg(String id,String now,String before) { 
			String query = "" + 
					"select avg(hba_num) from T_NF_USER_HBA1C where user_id = ?  and DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "; 
			return this.jdbcTemplate.queryForObject(query, new Object[] {id,before,now }, Double.class);
}
	
	//
	public Double getUserdesc(String id) { 
			String query = ""
					+ "	select hba_num "
					+ "	from T_NF_USER_HBA1C "
					+ "	where user_id = ? "
					+ "	order by reg_date desc "
					+ "	limit 1 ";
			return this.jdbcTemplate.queryForObject(query, new Object[] {id}, Double.class);
}
	//기간갯수
	public int getUserHbacnt(String id) { 
				String query = "" 
						+ "SELECT count(*)  "
						+"from T_NF_USER_HBA1C where user_id = ?";
			return this.jdbcTemplate.queryForInt(query, new Object[] { id });
	}
	
	///그래프
	

	public List<Userhb> getUserHba(String id,String now,String before) { 
				String query = ""
						+ "	select * "
						+ "	from T_NF_USER_HBA1C "
						+ "	where user_id = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
						+ "	limit 7 "; 
				return (List<Userhb>)this.jdbcTemplate.query(query, new Object[] { id,before,now },this.userhbMapper);
	}
	

	///그래프
	public int getUserHbaavg(String id,String now,String before) { 
				String query = "" + 
						"select avg(hba_num) from T_NF_USER_HBA1C where user_id = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "; 
				return this.jdbcTemplate.queryForInt(query, new Object[] { id,before,now });
	}
	
	public List<Userhb> getUserHba2(String id,String now,String before) { 
		String query = ""  
				+ "	select 0 as hba_seq, user_id,avg(hba_num) as hba_num, DATE_FORMAT(reg_date, '%Y-%m-%d') as reg_date"
				+ "	from T_NF_USER_HBA1C "
				+ "		where user_id = ? and DATE_FORMAT(reg_date, '%Y-%m-%d') between '"+before+"' and '"+now+"' "
				+ "	group by user_id, DATE_FORMAT(reg_date, '%Y-%m-%d') "
				+ "	ORDER BY reg_date ";   
		return (List<Userhb>)this.jdbcTemplate.query(query, new Object[] { id},this.userhbMapper2);
}
	

}
