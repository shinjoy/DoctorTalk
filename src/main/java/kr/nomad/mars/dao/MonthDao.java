package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Day;
import kr.nomad.mars.dto.Month;
import kr.nomad.mars.dto.Week;
import kr.nomad.util.T;
public class MonthDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper monthMapper = new RowMapper() {
		public Month mapRow(ResultSet rs, int rowNum) throws SQLException {
			Month month = new Month();
			month.setMonthSeq(rs.getInt("month_seq"));
			month.setSort(rs.getInt("sort"));
			month.setPseq(rs.getInt("pseq"));
			month.setAskind(rs.getInt("askind"));
			month.setComment(rs.getString("comment"));
			month.setMove(rs.getInt("move"));
			month.setAnsType(rs.getInt("ans_type"));
			month.setMonth(rs.getString("month"));
			month.setIsLast(rs.getInt("is_last"));
			return month;
		}
	};
	
	private RowMapper monthMapper2 = new RowMapper() {
		public Month mapRow(ResultSet rs, int rowNum) throws SQLException {
			Month month = new Month();
		
			month.setMonth(rs.getString("month"));
		
			return month;
		}
	};


	
	public List<Month> getmonthlist(String month) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_Month " +
				"WHERE askind = 1 and month = ? "+
				"ORDER BY sort ASC";
		try{
			return (List<Month>)this.jdbcTemplate.query(query, new Object[] { month }, this.monthMapper);
		}catch(Exception e){
			return null;
		}
	}
	
	public List<Month> getmonthAnswerList(int seq,String month) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_Month " +
				"WHERE askind = 2 and pseq = ? and month = ?"+ 
				"ORDER BY sort ASC ";
		return (List<Month>)this.jdbcTemplate.query(query, new Object[] { seq,month }, this.monthMapper);
	}			
	//그룹리스트
	public List<Month> getGroup() {
		String query = " select distinct(month) from T_NF_DOCTOR_Month order by month asc ";
		return (List<Month>)this.jdbcTemplate.query(query,this.monthMapper2);
	}
	//해당 월 리스트
	
	public List<Month> getmonthlist2(String month) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_Month " +
				"WHERE month = ? "+
				"ORDER BY sort ASC";
		try{
			return (List<Month>)this.jdbcTemplate.query(query, new Object[] { month }, this.monthMapper);
		}catch(Exception e){
			return null;
		}
	}
	
	public void addMonth(Month month) {
		String query = "" +
				"INSERT INTO t_nf_doctor_month " +
				"	(sort, pseq, askind, comment, move, ans_type, month, is_last) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			month.getSort(),
			month.getPseq(),
			month.getAskind(),
			month.getComment(),
			month.getMove(),
			month.getAnsType(),
			month.getMonth(),
			month.getIsLast()
		});
	}
	
	
	//순서 업데이트
	public void updatesort(int monthSeq,int sort) { 
		String query = "" + 
				"UPDATE t_nf_doctor_month SET " +
				
				"	sort = ? " +
			
				"WHERE month_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {sort,monthSeq});
	}
	
	//수정
	
	public void updateMonthdata(Month month) { 
		String query = "" + 
				"UPDATE t_nf_doctor_month SET " +
	
		
				"	pseq = ?, " +
				"	comment = ?, " +
				"	move = ?, " +
				"	ans_type = ?, " +
				"	month = ? " +
				"WHERE month_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			
				month.getPseq(),
			month.getComment(),
			month.getMove(),
			month.getAnsType(),
			month.getMonth(),
			month.getMonthSeq()
		});
	}
	//삭제
	public void deleteMonth(int month_seq) {
		String query = "DELETE FROM t_nf_doctor_month WHERE month_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { month_seq });
	}
	
	
	//isLast
	public void updateislast(int monthSeq,int num) { 
		String query = "" + 
				"UPDATE t_nf_doctor_month SET " +
				
				"	is_last = ? " +
			
				"WHERE month_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {num,monthSeq});
	}
	
	//////////////////////////
	/*public void addMonth(final Month month) {
		String query = "" +
				"INSERT INTO t_nf_doctor_month " +
				"	(month_seq, sort, pseq, askind, comment, move, ans_type, month, is_last) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			month.getMonthSeq(),
			month.getSort(),
			month.getPseq(),
			month.getAskind(),
			month.getComment(),
			month.getMove(),
			month.getAnsType(),
			month.getMonth(),
			month.getIsLast()
		});
	}
*/
/*	public void deleteMonth(String month_seq) {
		String query = "DELETE FROM t_nf_doctor_month WHERE month_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { month_seq });
	}*/

	public void updateMonth(Month month) { 
		String query = "" + 
				"UPDATE t_nf_doctor_month SET " +
				"	month_seq = ?, " +
				"	sort = ?, " +
				"	pseq = ?, " +
				"	askind = ?, " +
				"	comment = ?, " +
				"	move = ?, " +
				"	ans_type = ?, " +
				"	month = ?, " +
				"	is_last = ? " +
				"WHERE month_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			month.getMonthSeq(),
			month.getSort(),
			month.getPseq(),
			month.getAskind(),
			month.getComment(),
			month.getMove(),
			month.getAnsType(),
			month.getMonth(),
			month.getIsLast()
		});
	}

	public Month getMonth(String month_seq) {
		String query = "" + 
				"SELECT month_seq, sort, pseq, askind, comment, move, ans_type, month, is_last " +
				"FROM t_nf_doctor_month " +
				"WHERE month_seq = ? ";
		return (Month)this.jdbcTemplate.queryForObject(query, new Object[] { month_seq }, this.monthMapper);
	}

	/*
	public List<Month> getMonthList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" month_seq, sort, pseq, askind, comment, move, ans_type, month, is_last " +
				"FROM t_nf_doctor_month " +
				"WHERE month_seq <= ( " +
				"	SELECT MIN(month_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" month_seq " +
				"		FROM t_nf_doctor_month " +
				"		ORDER BY month_seq DESC " +
				"	) AS A) " +
				"ORDER BY month_seq DESC";
		return (List<Month>)this.jdbcTemplate.query(query, this.monthMapper);
	}
	*/
	

}
