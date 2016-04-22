package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Cperiod;
import kr.nomad.mars.dto.Period;


public class CperiodDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper cperiodMapper = new RowMapper() {
		public Cperiod mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cperiod cperiod = new Cperiod();
			cperiod.setCperSeq(rs.getInt("cper_seq"));
			cperiod.setSort(rs.getInt("sort"));
			cperiod.setDiseaseId(rs.getString("disease_id"));
			cperiod.setComment(rs.getString("comment"));
			cperiod.setPseq(rs.getInt("pseq"));
			cperiod.setAskind(rs.getInt("askind"));
			cperiod.setMove(rs.getInt("move"));
			cperiod.setIsLast(rs.getInt("is_last"));
			cperiod.setAnsType(rs.getInt("ans_type"));
			cperiod.setValue(rs.getString("value"));
			return cperiod;
		}
	};
	
	public List<Cperiod> getCperiod(String diseaseId) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_CPERIOD " +
				"WHERE askind = 1 and disease_id= ? " +
				"ORDER BY sort ASC";
		return (List<Cperiod>)this.jdbcTemplate.query(query, new Object[] { diseaseId }, this.cperiodMapper);
	}
	
	public List<Cperiod> getCperiodAnswerList(int seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_CPERIOD " +
				"WHERE askind = 2 and pseq= ? "+ 
				"ORDER BY sort ASC ";
		return (List<Cperiod>)this.jdbcTemplate.query(query, new Object[] { seq }, this.cperiodMapper);
	}		
	
	///////////////
	
	
	

			

}
