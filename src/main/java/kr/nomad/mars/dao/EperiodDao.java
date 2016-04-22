package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Eperiod;
import kr.nomad.mars.dto.EperiodGroup;
import kr.nomad.mars.dto.Week;
import kr.nomad.mars.dto.WeekGroup;

public class EperiodDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper eperiodMapper = new RowMapper() {
		public Eperiod mapRow(ResultSet rs, int rowNum) throws SQLException {
			Eperiod eperiod = new Eperiod();
			eperiod.setEperSeq(rs.getInt("eper_seq"));
			eperiod.setKcase(rs.getInt("kcase"));
			eperiod.setSort(rs.getInt("sort"));
			eperiod.setDiseaseId(rs.getString("disease_id"));
			eperiod.setComment(rs.getString("comment"));
			eperiod.setPseq(rs.getInt("pseq"));
			eperiod.setAskind(rs.getInt("askind"));
			eperiod.setMove(rs.getInt("move"));
			eperiod.setValue(rs.getString("value"));
			eperiod.setAnsType(rs.getInt("ans_type"));
			eperiod.setIsLast(rs.getInt("is_last"));
			return eperiod;
		}
	};
	
	private RowMapper eperiodMapper2 = new RowMapper() {
		public Eperiod mapRow(ResultSet rs, int rowNum) throws SQLException {
			Eperiod eperiod = new Eperiod();
			
			eperiod.setKcase(rs.getInt("kcase"));
		
			return eperiod;
		}
	};
	private RowMapper eperiodGroupMapper = new RowMapper() {
		public EperiodGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
			EperiodGroup eperiod = new EperiodGroup();
			
			eperiod.setKcase(rs.getInt("kcase"));
			eperiod.setCount(rs.getInt("count"));
		
			return eperiod;
		}
	};

	
	public List<Eperiod> getEperiod(int kind) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_EPERIOD " +
				"WHERE askind = 1 and kcase=? "+
				"ORDER BY sort ASC";
		return (List<Eperiod>)this.jdbcTemplate.query(query, new Object[] { kind }, this.eperiodMapper);
	}
	
	public List<Eperiod> getEperiodAnswerList(int seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_EPERIOD " +
				"WHERE askind = 2 and pseq = ? "+ 
				"ORDER BY sort ASC ";
		return (List<Eperiod>)this.jdbcTemplate.query(query, new Object[] { seq }, this.eperiodMapper);
	}		
	
	public int getkcaseCount() {
		String query = " select count(distinct(kcase)) from T_NF_DOCTOR_EPERIOD ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public List<Eperiod> getkcase() {
		String query = " select distinct(kcase) from T_NF_DOCTOR_EPERIOD ";
		
		return (List<Eperiod>)this.jdbcTemplate.query(query, this.eperiodMapper2);
	}
	
	
	/////////////////////////////  ����
	
	public void addEperiod(final Eperiod eperiod) {
		String query = "" +
				"INSERT INTO T_NF_DOCTOR_EPERIOD " +
				"	(kcase, sort, disease_id, comment, pseq, askind, move, value, is_last, ans_type) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			eperiod.getKcase(),
			eperiod.getSort(),
			eperiod.getDiseaseId(),
			eperiod.getComment(),
			eperiod.getPseq(),
			eperiod.getAskind(),
			eperiod.getMove(),
			eperiod.getValue(),
			eperiod.getIsLast(),
			eperiod.getAnsType()
		});
	}

	public void deleteEperiod(int eper_seq) {
		String query = "DELETE FROM T_NF_DOCTOR_EPERIOD WHERE eper_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { eper_seq });
	}

	public void updateEperiod(Eperiod eperiod) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_EPERIOD SET " +
				"	kcase = ?, " +
				"	sort = ?, " +
				"	disease_id = ?, " +
				"	comment = ?, " +
				"	pseq = ?, " +
				"	askind = ?, " +
				"	move = ?, " +
				"	value = ?, " +
				"	is_last = ?, " +
				"	ans_type = ? " +
				"WHERE eper_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			eperiod.getEperSeq(),
			eperiod.getKcase(),
			eperiod.getSort(),
			eperiod.getDiseaseId(),
			eperiod.getComment(),
			eperiod.getPseq(),
			eperiod.getAskind(),
			eperiod.getMove(),
			eperiod.getValue(),
			eperiod.getIsLast(),
			eperiod.getAnsType()
		});
	}
	
	public void updateEperiodTable(Eperiod eperiod) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_EPERIOD SET " +
				"	comment = ?, " +
				"	pseq = ?, " +
				"	move = ?, " +
				"	ans_type = ? " +
				"WHERE eper_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			eperiod.getComment(),
			eperiod.getPseq(),
			eperiod.getMove(),
			eperiod.getAnsType(),
			eperiod.getEperSeq()
		});
	}

	public Eperiod getEperiodCheck(int eper_seq) {
		String query = "" + 
				"SELECT eper_seq, kcase, sort, disease_id, comment, pseq, askind, move, value, is_last, ans_type " +
				"FROM T_NF_DOCTOR_EPERIOD " +
				"WHERE eper_seq = ? ";
		return (Eperiod)this.jdbcTemplate.queryForObject(query, new Object[] { eper_seq }, this.eperiodMapper);
	}
	
	// ���̺� ���� ���� ó�� 
	public void updateEperiod(String comment, int eper_seq) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_EPERIOD SET " +
				"	comment = ? " +
				"WHERE eper_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {comment ,eper_seq});
	}
	
	// ���İ� ����
	public void updateEperiod(int sort ,int eper_seq) { 
		
		String query = "" + 
				"UPDATE T_NF_DOCTOR_EPERIOD SET " +
				"	sort = ? " +
				" WHERE eper_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {sort,eper_seq});
	}
	
	// üũ�� ����
	public void updateEperiodIsLast(int is_last ,int eper_seq) { 
		
		String query = "" + 
				"UPDATE T_NF_DOCTOR_EPERIOD SET " +
				"	is_last = ? " +
				" WHERE eper_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {is_last,eper_seq});
	}
	
	
	
	public List<Eperiod> getEperiodList(int page, int itemCountPerPage) {
		
		List<Eperiod> result = null;

		String query = ""
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			* "
				+ "		FROM T_NF_DOCTOR_EPERIOD "
				+ "		ORDER BY sort asc " 
				+ ") AS row LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		result = (List<Eperiod>)this.jdbcTemplate.query(query, this.eperiodMapper);
		
		return result;
	}
	
	
	public int getCount() {
		int result = 0;		
		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_DOCTOR_EPERIOD" ;
		result = this.jdbcTemplate.queryForInt(query);
				
		return result;
	}
	
	
	
	
	
	public List<Eperiod> getEperiodList(int kcase) {
		
		List<Eperiod> result = null;

		String query = ""
				//+ "	SELECT * FROM ( "
				+ "	SELECT "
				//+ "		ROW_NUMBER() OVER(ORDER BY sort asc ) AS row_seq, "
				+ "		* "
				+ "	FROM T_NF_DOCTOR_EPERIOD "
				+ " WHERE kcase = ? order by sort" ;
				//+ ") AS row WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") + 1 AND "+page+" * "+itemCountPerPage+" ";
				
		result = (List<Eperiod>)this.jdbcTemplate.query(query, new Object[] { kcase }, this.eperiodMapper);
		
		return result;
	}
	
	
	public int getCount(int kcase) {
		int result = 0;		
		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_DOCTOR_EPERIOD WHERE kcase = ? " ;
		result = this.jdbcTemplate.queryForInt(query, new Object[] { kcase });
				
		return result;
	}
	
	public List getcaseList() {
		String query = "SELECT DISTINCT kcase FROM T_NF_DOCTOR_EPERIOD ";
		return this.jdbcTemplate.queryForList(query);
	}
	

	public List<EperiodGroup> eperiodGroup(){
		String query = "" + 
				"select kcase, count(*) as count from T_NF_DOCTOR_EPERIOD group by kcase order by kcase asc";
		return (List<EperiodGroup>)this.jdbcTemplate.query(query,this.eperiodGroupMapper);
	}

	public List<Eperiod> getEperiodlistTop1(int kcase) {
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_DOCTOR_EPERIOD "
				+ "	WHERE kcase = ? "
				+ "	ORDER BY sort ASC "
				+ "	LIMIT 1 ";
		try{
			return (List<Eperiod>)this.jdbcTemplate.query(query, new Object[] { kcase }, this.eperiodMapper);
		}catch(Exception e){
			return null;
		}
	}
	
}
