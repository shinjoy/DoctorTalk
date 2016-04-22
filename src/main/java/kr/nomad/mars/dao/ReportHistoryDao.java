package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ReportHistory;

public class ReportHistoryDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper reporthistoryMapper = new RowMapper() {
		public ReportHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReportHistory userreporthistory = new ReportHistory();
			userreporthistory.setReportSeq(rs.getInt("report_seq"));
			userreporthistory.setUserId(rs.getString("user_id"));
			userreporthistory.setReportDate(rs.getString("report_date"));
			userreporthistory.setReportType(rs.getInt("report_type"));
			userreporthistory.setReportIdx(rs.getInt("report_idx"));
			userreporthistory.setBloodCount(rs.getInt("blood_count"));
			userreporthistory.setBloodTargetCount(rs.getInt("blood_target_count"));
			userreporthistory.setBloodPercent(rs.getInt("blood_percent"));
			userreporthistory.setPressureCount(rs.getInt("pressure_count"));
			userreporthistory.setPressureTargetCount(rs.getInt("pressure_target_count"));
			userreporthistory.setPressurePercent(rs.getInt("pressure_percent"));
			userreporthistory.setWeightCount(rs.getInt("weight_count"));
			userreporthistory.setWeightTargetCount(rs.getInt("weight_target_count"));
			userreporthistory.setWeightPercent(rs.getInt("weight_percent"));
			userreporthistory.setRegDate(rs.getString("reg_date"));
			return userreporthistory;
		}
	};

	public void addReportHistory(final ReportHistory reporthistory) {
		String query = "" +
				"INSERT INTO T_NF_USER_REPORT_HISTORY " +
				"	( user_id, report_date, report_type, "
				+ " report_idx, blood_count, blood_target_count,"
				+ " blood_percent, pressure_count, pressure_target_count, "
				+ "pressure_percent, weight_count, weight_target_count,"
				+ " weight_percent, reg_date) " +
				"VALUES " +
				"	( ?, sysdate(), ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, sysdate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			reporthistory.getUserId(),
		
			reporthistory.getReportType(),
			reporthistory.getReportIdx(),
			reporthistory.getBloodCount(),
			reporthistory.getBloodTargetCount(),
			reporthistory.getBloodPercent(),
			reporthistory.getPressureCount(),
			reporthistory.getPressureTargetCount(),
			reporthistory.getPressurePercent(),
			reporthistory.getWeightCount(),
			reporthistory.getWeightTargetCount(),
			reporthistory.getWeightPercent()
		
		});
	}

	public void deleteReportHistory(String report_seq) {
		String query = "DELETE FROM T_NF_USER_REPORT_HISTORY WHERE report_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { report_seq });
	}

	public void updateReportHistory(ReportHistory reporthistory) { 
		String query = "" + 
				"UPDATE T_NF_USER_REPORT_HISTORY SET " +
				"	report_seq = ?, " +
				"	user_id = ?, " +
				"	report_date = ?, " +
				"	report_type = ?, " +
				"	report_idx = ?, " +
				"	blood_count = ?, " +
				"	blood_target_count = ?, " +
				"	blood_percent = ?, " +
				"	pressure_count = ?, " +
				"	pressure_target_count = ?, " +
				"	pressure_percent = ?, " +
				"	weight_count = ?, " +
				"	weight_target_count = ?, " +
				"	weight_percent = ?, " +
				"	reg_date = ? " +
				"WHERE report_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			reporthistory.getReportSeq(),
			reporthistory.getUserId(),
			reporthistory.getReportDate(),
			reporthistory.getReportType(),
			reporthistory.getReportIdx(),
			reporthistory.getBloodCount(),
			reporthistory.getBloodTargetCount(),
			reporthistory.getBloodPercent(),
			reporthistory.getPressureCount(),
			reporthistory.getPressureTargetCount(),
			reporthistory.getPressurePercent(),
			reporthistory.getWeightCount(),
			reporthistory.getWeightTargetCount(),
			reporthistory.getWeightPercent(),
			reporthistory.getRegDate()
		});
	}

	public ReportHistory getReportHistory(String report_seq) {
		String query = "" + 
				"SELECT report_seq, user_id, report_date, report_type, report_idx, blood_count, blood_target_count, blood_percent, pressure_count, pressure_target_count, pressure_percent, weight_count, weight_target_count, weight_percent, reg_date " +
				"FROM T_NF_USER_REPORT_HISTORY " +
				"WHERE report_seq = ? ";
		return (ReportHistory)this.jdbcTemplate.queryForObject(query, new Object[] { report_seq }, this.reporthistoryMapper);
	}

	/** 검색 목록 **/
	public List<ReportHistory> getReportHistoryList(String userId, int page, int itemCountPerPage) {
	    String query = ""
	            + "    SELECT * FROM ( "
	            + "         SELECT "
	            + "            * "
	            + "         FROM T_NF_USER_REPORT_HISTORY "
	            + "         WHERE user_id = ? "
	            + "			ORDER BY report_seq DESC "
	            + "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<ReportHistory>)this.jdbcTemplate.query(query, new Object[] { userId }, this.reporthistoryMapper);
	}
	public int getCount(String userId) {
	    String query = " SELECT COUNT(*) FROM T_NF_USER_REPORT_HISTORY WHERE user_id = ? ";
	    return this.jdbcTemplate.queryForInt(query, new Object[] { userId });
	}
}
