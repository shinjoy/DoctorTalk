package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Analisys;


public class AnalisysDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper analisysMapper = new RowMapper() {
		public Analisys mapRow(ResultSet rs, int rowNum) throws SQLException {
			Analisys analisys = new Analisys();
			analisys.setAlnaSeq(rs.getInt("alna_seq"));
			analisys.setAskind(rs.getInt("askind"));
			analisys.setPeriod(rs.getInt("period"));
			analisys.setComment(rs.getString("comment"));
			analisys.setChartName(rs.getString("chart_name"));
			analisys.setPseq(rs.getInt("pseq"));
			analisys.setDiseaseId(rs.getString("disease_id"));
			return analisys;
		}
	};
	
	//분석결과뽑아옴
	
	public Analisys getAnalisys(int code) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_ANALISYS " +
				"WHERE code = ? ";
		return (Analisys)this.jdbcTemplate.queryForObject(query, new Object[] { code}, this.analisysMapper);
	}
	
	

	public void addAnalisys(final Analisys analisys) {
		String query = "" +
				"INSERT INTO T_NF_ANALISYS " +
				"	(alna_seq, askind, period, comment, chart_name, pseq, disease_id) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			analisys.getAlnaSeq(),
			analisys.getAskind(),
			analisys.getPeriod(),
			analisys.getComment(),
			analisys.getChartName(),
			analisys.getPseq(),
			analisys.getDiseaseId()
		});
	}

	public void deleteAnalisys(String pl_id) {
		String query = "DELETE FROM T_NF_ANALISYS WHERE pl_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { pl_id });
	}

	public void updateAnalisys(Analisys analisys) { 
		String query = "" + 
				"UPDATE T_NF_ANALISYS SET " +
				"	alna_seq = ?, " +
				"	askind = ?, " +
				"	period = ?, " +
				"	comment = ?, " +
				"	chart_name = ?, " +
				"	pseq = ?, " +
				"	disease_id = ? " +
				"WHERE pl_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			analisys.getAlnaSeq(),
			analisys.getAskind(),
			analisys.getPeriod(),
			analisys.getComment(),
			analisys.getChartName(),
			analisys.getPseq(),
			analisys.getDiseaseId()
		});
	}

	
}
