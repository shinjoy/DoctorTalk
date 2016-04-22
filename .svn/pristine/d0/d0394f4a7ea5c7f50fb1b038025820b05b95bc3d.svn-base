package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.UserReport;
import kr.nomad.util.T;


public class UserReportDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userreportMapper = new RowMapper() {
		public UserReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserReport userreport = new UserReport();
			userreport.setRepSeq(rs.getInt("rep_seq"));
			userreport.setUserId(rs.getString("user_id"));
			userreport.setType(rs.getInt("type"));
			userreport.setBeforeEatBloodCnt(rs.getInt("before_eat_blood_cnt"));
			userreport.setAfterEatBloodCnt(rs.getInt("after_eat_blood_cnt"));
			userreport.setBeforeSleepBloodCnt(rs.getInt("before_sleep_blood_cnt"));
			userreport.setTotalBloodCnt(rs.getInt("total_blood_cnt"));
			userreport.setTotalPressureCnt(rs.getInt("total_pressure_cnt"));
			userreport.setTotalWeightCnt(rs.getInt("total_weight_cnt"));
			userreport.setBeforeEatBlood(rs.getInt("before_eat_blood"));
			userreport.setGoalBloodCnt(rs.getInt("goal_blood_cnt"));
			userreport.setGoalPressureCnt(rs.getInt("goal_pressure_cnt"));
			userreport.setGoalWeightCnt(rs.getInt("goal_weight_cnt"));
			userreport.setAfterEatBlood(rs.getInt("after_eat_blood"));
			userreport.setBeforeSleepBlood(rs.getInt("before_sleep_blood"));
			userreport.setSpressure(rs.getInt("spressure"));
			userreport.setBpressure(rs.getInt("bpressure"));
			userreport.setBmi(rs.getInt("bmi"));
			userreport.setGoalBeforeEatSblood(rs.getInt("goal_before_eat_sblood"));
			userreport.setGoalBeforeEatBblood(rs.getInt("goal_before_eat_bblood"));
			userreport.setGoalAfterEatSblood(rs.getInt("goal_after_eat_sblood"));
			userreport.setGoalAfterEatBblood(rs.getInt("goal_after_eat_bblood"));
			userreport.setGoalBeforeSleepSblood(rs.getInt("goal_before_sleep_sblood"));
			userreport.setGoalBeforeSleepBblood(rs.getInt("goal_before_sleep_bblood"));
			userreport.setGoalSpressure(rs.getInt("goal_spressure"));
			userreport.setGoalBpressure(rs.getInt("goal_bpressure"));
			userreport.setGoalSbmi(rs.getInt("goal_sbmi"));
			userreport.setGoalBbmi(rs.getInt("goal_bbmi"));
			userreport.setRegDate(rs.getString("reg_date"));
			return userreport;
		}
	};

	public void addUserReport(final UserReport userreport) {
		String query = "" +
				"INSERT INTO T_NF_USER_REPORT " +
				"	(user_id, type, before_eat_blood_cnt, "
				+ " after_eat_blood_cnt, before_sleep_blood_cnt, total_blood_cnt,"
				+ "  total_pressure_cnt, total_weight_cnt, before_eat_blood,"
				+ "  goal_blood_cnt, goal_pressure_cnt, goal_weight_cnt,"
				+ "  after_eat_blood, before_sleep_blood, spressure,"
				+ "  bpressure, bmi, goal_before_eat_sblood,"
				+ "  goal_before_eat_bblood, goal_after_eat_sblood, goal_after_eat_bblood,"
				+ "  goal_before_sleep_sblood, goal_before_sleep_bblood, goal_spressure,"
				+ "  goal_bpressure, goal_sbmi, goal_bbmi, reg_date) " +
				"VALUES " +
				"	(?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?, SYSDATE()) ";
		this.jdbcTemplate.update(query, new Object[] {
		
			userreport.getUserId(),
			userreport.getType(),
			userreport.getBeforeEatBloodCnt(),
			userreport.getAfterEatBloodCnt(),
			userreport.getBeforeSleepBloodCnt(),
			userreport.getTotalBloodCnt(),
			userreport.getTotalPressureCnt(),
			userreport.getTotalWeightCnt(),
			userreport.getBeforeEatBlood(),
			userreport.getGoalBloodCnt(),
			userreport.getGoalPressureCnt(),
			userreport.getGoalWeightCnt(),
			userreport.getAfterEatBlood(),
			userreport.getBeforeSleepBlood(),
			userreport.getSpressure(),
			userreport.getBpressure(),
			userreport.getBmi(),
			userreport.getGoalBeforeEatSblood(),
			userreport.getGoalBeforeEatBblood(),
			userreport.getGoalAfterEatSblood(),
			userreport.getGoalAfterEatBblood(),
			userreport.getGoalBeforeSleepSblood(),
			userreport.getGoalBeforeSleepBblood(),
			userreport.getGoalSpressure(),
			userreport.getGoalBpressure(),
			userreport.getGoalSbmi(),
			userreport.getGoalBbmi()
		
		});
	}

	public void deleteUserReport(String rep_seq) {
		String query = "DELETE FROM T_NF_USER_REPORT WHERE rep_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { rep_seq });
	}

	public void updateUserReport(UserReport userreport) { 
		String query = "" + 
				"UPDATE T_NF_USER_REPORT SET " +
				"	rep_seq = ?, " +
				"	user_id = ?, " +
				"	type = ?, " +
				"	before_eat_blood_cnt = ?, " +
				"	after_eat_blood_cnt = ?, " +
				"	before_sleep_blood_cnt = ?, " +
				"	total_blood_cnt = ?, " +
				"	total_pressure_cnt = ?, " +
				"	total_weight_cnt = ?, " +
				"	before_eat_blood = ?, " +
				"	goal_blood_cnt = ?, " +
				"	goal_pressure_cnt = ?, " +
				"	goal_weight_cnt = ?, " +
				"	after_eat_blood = ?, " +
				"	before_sleep_blood = ?, " +
				"	spressure = ?, " +
				"	bpressure = ?, " +
				"	bmi = ?, " +
				"	goal_before_eat_sblood = ?, " +
				"	goal_before_eat_bblood = ?, " +
				"	goal_after_eat_sblood = ?, " +
				"	goal_after_eat_bblood = ?, " +
				"	goal_before_sleep_sblood = ?, " +
				"	goal_before_sleep_bblood = ?, " +
				"	goal_spressure = ?, " +
				"	goal_bpressure = ?, " +
				"	goal_sbmi = ?, " +
				"	goal_bbmi = ?, " +
				"	reg_date = ? " +
				"WHERE rep_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			userreport.getRepSeq(),
			userreport.getUserId(),
			userreport.getType(),
			userreport.getBeforeEatBloodCnt(),
			userreport.getAfterEatBloodCnt(),
			userreport.getBeforeSleepBloodCnt(),
			userreport.getTotalBloodCnt(),
			userreport.getTotalPressureCnt(),
			userreport.getTotalWeightCnt(),
			userreport.getBeforeEatBlood(),
			userreport.getGoalBloodCnt(),
			userreport.getGoalPressureCnt(),
			userreport.getGoalWeightCnt(),
			userreport.getAfterEatBlood(),
			userreport.getBeforeSleepBlood(),
			userreport.getSpressure(),
			userreport.getBpressure(),
			userreport.getBmi(),
			userreport.getGoalBeforeEatSblood(),
			userreport.getGoalBeforeEatBblood(),
			userreport.getGoalAfterEatSblood(),
			userreport.getGoalAfterEatBblood(),
			userreport.getGoalBeforeSleepSblood(),
			userreport.getGoalBeforeSleepBblood(),
			userreport.getGoalSpressure(),
			userreport.getGoalBpressure(),
			userreport.getGoalSbmi(),
			userreport.getGoalBbmi(),
			userreport.getRegDate()
		});
	}

	public UserReport getUserReport(String rep_seq) {
		String query = "" + 
				"SELECT rep_seq, user_id, type, before_eat_blood_cnt, after_eat_blood_cnt, before_sleep_blood_cnt, total_blood_cnt, total_pressure_cnt, total_weight_cnt, before_eat_blood, goal_blood_cnt, goal_pressure_cnt, goal_weight_cnt, after_eat_blood, before_sleep_blood, spressure, bpressure, bmi, goal_before_eat_sblood, goal_before_eat_bblood, goal_after_eat_sblood, goal_after_eat_bblood, goal_before_sleep_sblood, goal_before_sleep_bblood, goal_spressure, goal_bpressure, goal_sbmi, goal_bbmi, reg_date " +
				"FROM T_NF_USER_REPORT " +
				"WHERE rep_seq = ? ";
		return (UserReport)this.jdbcTemplate.queryForObject(query, new Object[] { rep_seq }, this.userreportMapper);
	}

	/*
	public List<UserReport> getUserReportList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" rep_seq, user_id, type, before_eat_blood_cnt, after_eat_blood_cnt, before_sleep_blood_cnt, total_blood_cnt, total_pressure_cnt, total_weight_cnt, before_eat_blood, goal_blood_cnt, goal_pressure_cnt, goal_weight_cnt, after_eat_blood, before_sleep_blood, spressure, bpressure, bmi, goal_before_eat_sblood, goal_before_eat_bblood, goal_after_eat_sblood, goal_after_eat_bblood, goal_before_sleep_sblood, goal_before_sleep_bblood, goal_spressure, goal_bpressure, goal_sbmi, goal_bbmi, reg_date " +
				"FROM T_NF_USER_REPORT " +
				"WHERE rep_seq <= ( " +
				"	SELECT MIN(rep_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" rep_seq " +
				"		FROM T_NF_USER_REPORT " +
				"		ORDER BY rep_seq DESC " +
				"	) AS A) " +
				"ORDER BY rep_seq DESC";
		return (List<UserReport>)this.jdbcTemplate.query(query, this.userreportMapper);
	}
	*/
}
