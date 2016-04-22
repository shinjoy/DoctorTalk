package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.Confg;
import kr.nomad.mars.dto.Qna;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ConfgDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper medexamMapper = new RowMapper() {
		public Confg mapRow(ResultSet rs, int rowNum) throws SQLException {
			Confg confg = new Confg();
			confg.setAppSeq(rs.getInt("app_seq"));
			confg.setAppVersion(rs.getString("app_version"));
			return confg;
		}
	};

	public void addConfg(final Confg confg) {
		String query = "" +
				"INSERT INTO T_NF_CONFG " +
				"	(app_version) " +
				"VALUES " +
				"	(?) ";
		this.jdbcTemplate.update(query, new Object[] {
				confg.getAppVersion()
		});
	}

	public void deleteConfg(int app_seq) {
		String query = "DELETE FROM T_NF_CONFG WHERE app_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { app_seq });
	}

	public void updateConfg(Confg confg) { 
		String query = "" + 
				"UPDATE T_NF_CONFG SET " +
				"	app_version = ? " +
				"WHERE app_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			confg.getAppVersion(),
			confg.getAppSeq()
		});
	}

	public Confg getConfg(int app_seq) {
		String query = "" + 
				"SELECT app_seq, app_version " +
				"FROM T_NF_CONFG " +
				"WHERE app_seq = ? ";
		return (Confg)this.jdbcTemplate.queryForObject(query, new Object[] { app_seq }, this.medexamMapper);
	}
	
	public List<Confg> getConfgList(int page, int itemCountPerPage) {
		
		List<Confg> result = null;

		String query = ""
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			* "
				+ "		FROM T_NF_CONFG "
				+ "		ORDER BY app_seq desc "
				+ ") AS row LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
				
		result = (List<Confg>)this.jdbcTemplate.query(query,new Object[] {}, this.medexamMapper);
		
		return result;
	}

	public int getCount() {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_CONFG";
		result = this.jdbcTemplate.queryForInt(query,new Object[] {});
				
		return result;
	}

	
	
}
