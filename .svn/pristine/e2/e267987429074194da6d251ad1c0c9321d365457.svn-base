package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.UserMediLog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UMediLogDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper umedilogMapper = new RowMapper() {
		public UserMediLog mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserMediLog umedilog = new UserMediLog();
			umedilog.setMlogSeq(rs.getInt("mlog_seq"));
			umedilog.setMedSeq(rs.getInt("med_seq"));
			umedilog.setRegDate(rs.getString("reg_date"));
			return umedilog;
		}
	};

	public void addUMediLog(final UserMediLog umedilog) {
		String query = "" +
				"INSERT INTO T_NF_USER_MEDICINE_LOG " +
				"	(med_seq, reg_date) " +
				"VALUES " +
				"	(?, SYSDATE()) ";
		this.jdbcTemplate.update(query, new Object[] {
			umedilog.getMedSeq()
		});
	}

	public void deleteUMediLog(String mlog_seq) {
		String query = "DELETE FROM T_NF_USER_MEDICINE_LOG WHERE mlog_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { mlog_seq });
	}

	public void deleteUMediLog(int medSeq, String date) {
		String query = "DELETE FROM T_NF_USER_MEDICINE_LOG WHERE med_seq = ? AND DATE_FORMAT(SYSDATE(), '%Y-%m-%d') = ? ";
		this.jdbcTemplate.update(query, new Object[] { medSeq, date });
	}

	public void updateUMediLog(UserMediLog umedilog) { 
		String query = "" + 
				"UPDATE T_NF_USER_MEDICINE_LOG SET " +
				"	mlog_seq = ?, " +
				"	med_seq = ?, " +
				"	reg_date = ? " +
				"WHERE mlog_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			umedilog.getMlogSeq(),
			umedilog.getMedSeq(),
			umedilog.getRegDate()
		});
	}

	public UserMediLog getUMediLog(int mlog_seq) {
		String query = "" + 
				"SELECT mlog_seq, med_seq, reg_date " +
				"FROM T_NF_USER_MEDICINE_LOG " +
				"WHERE mlog_seq = ? ";
		return (UserMediLog)this.jdbcTemplate.queryForObject(query, new Object[] { mlog_seq }, this.umedilogMapper);
	}
	

	public int getUMediLogCount(int medSeq, String date) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_USER_MEDICINE_LOG " +
				"WHERE med_seq = ? AND DATE_FORMAT(SYSDATE(), '%Y-%m-%d') = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { medSeq, date });
	}

	/*
	public List<UserMediLog> getUMediLogList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" mlog_seq, med_seq, reg_date " +
				"FROM T_NF_USER_MEDICINE_LOG " +
				"WHERE mlog_seq <= ( " +
				"	SELECT MIN(mlog_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" mlog_seq " +
				"		FROM T_NF_USER_MEDICINE_LOG " +
				"		ORDER BY mlog_seq DESC " +
				"	) AS A) " +
				"ORDER BY mlog_seq DESC";
		return (List<UserMediLog>)this.jdbcTemplate.query(query, this.umedilogMapper);
	}
	*/
}
