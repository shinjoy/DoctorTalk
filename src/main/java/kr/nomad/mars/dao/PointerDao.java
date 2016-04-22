package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.MedExam;
import kr.nomad.mars.dto.Pointer;
import kr.nomad.mars.dto.UserBasic;

import kr.nomad.util.T;

public class PointerDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper pointerMapper = new RowMapper() {
		public Pointer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Pointer pointer = new Pointer();
			pointer.setComSeq(rs.getInt("com_seq"));
			pointer.setDiseaseId(rs.getString("disease_id"));
			pointer.setComment(rs.getString("comment"));
			pointer.setSort(rs.getInt("sort"));
			pointer.setAskind(rs.getString("askind"));
			pointer.setPseq(rs.getInt("pseq"));
			pointer.setMove(rs.getInt("move"));
			pointer.setValue(rs.getString("value"));
			pointer.setAnsType(rs.getInt("ans_type"));
			pointer.setQtype(rs.getString("qtype"));
			pointer.setAnsvalue(rs.getInt("ansvalue"));
			pointer.setIsLast(rs.getInt("is_last"));
			return pointer;
		}
	};
	
	public List<Pointer> getPointer(String diseaseId) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_POINTER " +
				"WHERE askind = 1 and disease_id = ? "+
				"ORDER BY sort ASC";
		return (List<Pointer>)this.jdbcTemplate.query(query, new Object[] { diseaseId }, this.pointerMapper);
	}

	public List<Pointer> getPointerAnswerList(int com_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_POINTER " +
				"WHERE pseq = ? " + 
				"ORDER BY sort ASC ";
		return (List<Pointer>)this.jdbcTemplate.query(query, new Object[] { com_seq }, this.pointerMapper);
	}	
	

	

}
