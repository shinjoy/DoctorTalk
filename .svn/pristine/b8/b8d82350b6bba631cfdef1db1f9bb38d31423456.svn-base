package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Day;
import kr.nomad.mars.dto.DayGroup;
import kr.nomad.mars.dto.Week;
import kr.nomad.mars.dto.WeekGroup;

public class WeekDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper weekMapper = new RowMapper() {
		public Week mapRow(ResultSet rs, int rowNum) throws SQLException {
			Week week = new Week();
			week.setWeekSeq(rs.getInt("week_seq"));
			week.setSort(rs.getInt("sort"));
			week.setPseq(rs.getInt("pseq"));
			week.setAskind(rs.getInt("askind"));
			week.setComment(rs.getString("comment"));
			week.setDiseaseId(rs.getString("disease_id"));
			week.setMove(rs.getInt("move"));
			week.setFileName(rs.getString("file_name"));
			week.setAnsType(rs.getInt("ans_type"));
			week.setWeekgroup(rs.getInt("weekgroup"));
			week.setIsLast(rs.getInt("is_last"));
			return week;
		}
	};
	
	private RowMapper randomMapper = new RowMapper() {
		public Week mapRow(ResultSet rs, int rowNum) throws SQLException {
			Week week = new Week();
		
			week.setWeekgroup(rs.getInt("weekgroup"));
			
			return week;
		}
	};

	private RowMapper weekGroupMapper = new RowMapper() {
		public WeekGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
			WeekGroup weekGroup = new WeekGroup();
			weekGroup.setWeekgroup(rs.getInt("weekgroup"));
			weekGroup.setDiseaseId(rs.getString("disease_id"));
			weekGroup.setCount(rs.getInt("count"));
			return weekGroup;
		}
	};
	
	
	
	public List<Week> getWeeklist(int group) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_WEEK " +
				"WHERE askind = 1 and weekgroup= ? "+
				"ORDER BY sort ASC";
		try{
			return (List<Week>)this.jdbcTemplate.query(query, new Object[] { group }, this.weekMapper);
		}catch(Exception e){
			return null;
		}
	}
	
	public List<Week> getWeeklist(int group,String did) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_WEEK " +
				"WHERE askind = 1 and weekgroup= ? and disease_id = ?  "+
				"ORDER BY sort ASC";
		try{
			return (List<Week>)this.jdbcTemplate.query(query, new Object[] { group,did }, this.weekMapper);
		}catch(Exception e){
			return new ArrayList();
		}
	}

	public List<Week> getWeeklistTop1(int group, String diseaseId) {
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_DOCTOR_WEEK "
				+ "	WHERE weekgroup = ? AND disease_id = ? "
				+ "	ORDER BY sort ASC "
				+ "	limit 1 ";
		try{
			return (List<Week>)this.jdbcTemplate.query(query, new Object[] { group, diseaseId }, this.weekMapper);
		}catch(Exception e){
			return null;
		}
	}
	
	public List<Week> getWeekAnswerList(int seq,int group) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_WEEK " +
				"WHERE askind = 2 and pseq = ? and  weekgroup= ?" +
				"ORDER BY sort ASC ";
		return (List<Week>)this.jdbcTemplate.query(query, new Object[] { seq,group }, this.weekMapper);
	}				
	
	public List<Week> getWeeklist2(int group,String did) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_WEEK " +
				"WHERE weekgroup= ? and disease_id = ?  "+
				"ORDER BY sort ASC";
		try{
			return (List<Week>)this.jdbcTemplate.query(query, new Object[] { group,did }, this.weekMapper);
		}catch(Exception e){
			return null;
		}
	}
	public int getMaxGroup() {
		String query = " select max(weekgroup) from T_NF_DOCTOR_WEEK ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
/*	
	/////////////////////////
	
	public void addWeek(final Week week) {
		String query = "" +
				"INSERT INTO T_NF_DOCTOR_WEEK " +
				"	(week_seq, sort, pseq, askind, comment, disease_id, move, file_name, ans_type) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			week.getWeekSeq(),
			week.getSort(),
			week.getPseq(),
			week.getAskind(),
			week.getComment(),
			week.getDiseaseId(),
			week.getMove(),
			week.getFileName(),
			week.getAnsType()
		});
	}

	public void deleteWeek(String week_seq) {
		String query = "DELETE FROM T_NF_DOCTOR_WEEK WHERE week_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { week_seq });
	}

	public void updateWeek(Week week) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_WEEK SET " +
				"	week_seq = ?, " +
				"	sort = ?, " +
				"	pseq = ?, " +
				"	askind = ?, " +
				"	comment = ?, " +
				"	disease_id = ?, " +
				"	move = ?, " +
				"	file_name = ?, " +
				"	ans_type = ? " +
				"WHERE week_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			week.getWeekSeq(),
			week.getSort(),
			week.getPseq(),
			week.getAskind(),
			week.getComment(),
			week.getDiseaseId(),
			week.getMove(),
			week.getFileName(),
			week.getAnsType()
		});
	}
*/
	public Week getWeek(String week_seq) {
		String query = "" + 
				"SELECT week_seq, sort, pseq, askind, comment, disease_id, move, file_name, ans_type " +
				"FROM T_NF_DOCTOR_WEEK " +
				"WHERE week_seq = ? ";
		return (Week)this.jdbcTemplate.queryForObject(query, new Object[] { week_seq }, this.weekMapper);
	}

	/*
	public List<Week> getWeekList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" week_seq, sort, pseq, askind, comment, disease_id, move, file_name, ans_type " +
				"FROM T_NF_DOCTOR_WEEK " +
				"WHERE week_seq <= ( " +
				"	SELECT MIN(week_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" week_seq " +
				"		FROM T_NF_DOCTOR_WEEK " +
				"		ORDER BY week_seq DESC " +
				"	) AS A) " +
				"ORDER BY week_seq DESC";
		return (List<Week>)this.jdbcTemplate.query(query, this.weekMapper);
	}
	*/
	
	public int getGroupCount() {
		String query = " select count(distinct(weekgroup)) from T_NF_DOCTOR_WEEK ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public List<Week> getGroupCount(String did) {
		String query = " select distinct(weekgroup) from T_NF_DOCTOR_WEEK where disease_id = ? order by weekgroup asc ";
		return (List<Week>)this.jdbcTemplate.query(query,new Object[] { did },this.randomMapper);
	}
	
	public List<Week> getGroupCount2(String did) {
		String query = " select distinct(weekgroup) from T_NF_DOCTOR_WEEK where askind=1 and disease_id = ? order by weekgroup asc ";
		return (List<Week>)this.jdbcTemplate.query(query,new Object[] { did },this.randomMapper);
	}
	
	//컨텐츠 수정
	public void updateWeek(Week week) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_WEEK SET " +
				"	move = ?, " +
				"	pseq = ?, " +
				
				"	comment = ?, " +
				"	ans_type = ? " +
				"WHERE week_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			week.getMove(),
			week.getPseq(),
			
			week.getComment(),
			week.getAnsType(),
			week.getWeekSeq()
			
		});
	}
	
	public void deleteWeek(int week_seq) {
		String query = "DELETE FROM T_NF_DOCTOR_WEEK WHERE week_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { week_seq });
	}
	public void deleteWeekGroup(int seq) {
		String query = "DELETE FROM T_NF_DOCTOR_WEEK WHERE weekgroup = ? ";
		this.jdbcTemplate.update(query, new Object[] { seq });
	}
	//정렬
	public void updatesortWeek(int seq,int num) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_WEEK SET " +
			
				"	sort = ? " +
		
				"WHERE week_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {num,seq});
	}
	//추가
	public void addWeek(final Week week) {
		String query = "" +
				"INSERT INTO T_NF_DOCTOR_WEEK " +
				"	(sort, pseq, askind, comment, disease_id, move, ans_type, weekgroup, is_last) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
		
			week.getSort(),
			week.getPseq(),
			week.getAskind(),
			week.getComment(),
			week.getDiseaseId(),
			week.getMove(),
			week.getAnsType(),
			week.getWeekgroup(),
			week.getIsLast()
		});
	}
	
	//islast
	public void updateislastWeek(int seq,int num) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_WEEK SET " +
				"	is_last = ? " +
				"WHERE week_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {num,seq});
	}
	/*public int getGroupCount2(String did) {
		String query = " select count(distinct(weekgroup)) from T_NF_DOCTOR_WEEK where disease_id = ?";
		return this.jdbcTemplate.queryForInt(query,new Object[] { did });
	}*/

	public List<WeekGroup> weekGroup(){
		String query = ""
				+ "	select weekgroup, disease_id, count(*) as count "
				+ "	from T_NF_DOCTOR_WEEK "
				+ "	group by weekgroup, disease_id order by weekgroup, disease_id asc";
		return (List<WeekGroup>)this.jdbcTemplate.query(query,this.weekGroupMapper);
	}

	public List<WeekGroup> weekGroup(int page, int itemCountPerPage){
		String query = ""  
				+ "	SELECT * FROM ("
				+ "		SELECT"
				+ "			weekgroup, disease_id, count(*) as count "
				+ "		FROM "
				+ "			T_NF_DOCTOR_WEEK "
				+ "		group by weekgroup, disease_id "
				+ "		ORDER BY weekgroup, disease_id asc "
				+ ") AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<WeekGroup>)this.jdbcTemplate.query(query,this.weekGroupMapper);
	}

	public int weekGroupCount(){
		String query = "select count(distinct(weekgroup)) from T_NF_DOCTOR_WEEK ";
		return this.jdbcTemplate.queryForInt(query);
	}

	public List<WeekGroup> weekGroup(String diseaseId){
		String query = ""
				+ "	select weekgroup, disease_id, count(*) as count "
				+ "	from T_NF_DOCTOR_WEEK "
				+ "	where disease_id = ? "
				+ "	group by weekgroup, disease_id order by weekgroup, disease_id asc";
		return (List<WeekGroup>)this.jdbcTemplate.query(query, new Object[] {diseaseId}, this.weekGroupMapper);
	}
	public List<WeekGroup> weekGroup(int page, int itemCountPerPage, String diseaseId){
		String query = ""  
				+ "	SELECT * FROM ("
				+ "		SELECT"
				+ "			weekgroup, disease_id, count(*) as count "
				+ "		FROM "
				+ "			T_NF_DOCTOR_WEEK "
				+ "		where disease_id = ? "
				+ "		group by weekgroup, disease_id "
				+ "		ORDER BY weekgroup, disease_id asc "
				+ ") AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<WeekGroup>)this.jdbcTemplate.query(query, new Object[] {diseaseId}, this.weekGroupMapper);
	}

	public int weekGroupCount(String diseaseId){
		String query = "select count(distinct(weekgroup)) from T_NF_DOCTOR_WEEK where disease_id = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] {diseaseId});
	}

	public int getLastId() {
	    String query = "SELECT max(weekgroup) FROM T_NF_DOCTOR_WEEK; ";
	    return this.jdbcTemplate.queryForInt(query );
	}
	
	
}
