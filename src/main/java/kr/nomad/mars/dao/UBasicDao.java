package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.UserBasic;
import kr.nomad.mars.dto.UserBlood;
import kr.nomad.util.T;

public class UBasicDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userbasicMapper = new RowMapper() {
		public UserBasic mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserBasic userbasic = new UserBasic();
			userbasic.setBasicSeq(rs.getInt("basic_seq"));
			userbasic.setUserId(rs.getString("user_id"));
			userbasic.setGender(rs.getInt("gender"));
			userbasic.setHeight(rs.getInt("height"));
			userbasic.setWeight(rs.getInt("weight"));
			userbasic.setWaist(rs.getInt("waist"));
			userbasic.setSmoke(rs.getInt("smoke"));
			userbasic.setBlood(rs.getString("blood"));
			userbasic.setCol(rs.getString("col"));
			userbasic.setPress(rs.getString("press"));
			userbasic.setRegDate(rs.getString("reg_date"));
			userbasic.setHeiwieght(rs.getString("heiwieght"));
			return userbasic;
		}
	};
	

	private RowMapper userbasicMapperV = new RowMapper() {
		public UserBasic mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserBasic userbasic = new UserBasic();
			userbasic.setBasicSeq(rs.getInt("basic_seq"));
			userbasic.setUserId(rs.getString("user_id"));
			userbasic.setGender(rs.getInt("gender"));
			userbasic.setHeight(rs.getInt("height"));
			userbasic.setWeight(rs.getInt("weight"));
			userbasic.setWaist(rs.getInt("waist"));
			userbasic.setSmoke(rs.getInt("smoke"));
			userbasic.setBlood(rs.getString("blood"));
			userbasic.setCol(rs.getString("col"));
			userbasic.setPress(rs.getString("press"));
			userbasic.setRegDate(rs.getString("reg_date"));
			userbasic.setBloodNum(rs.getInt("blood_num"));
			userbasic.setPulse(rs.getInt("pulse"));
			userbasic.setSplessure(rs.getInt("splessure"));
			userbasic.setDplessure(rs.getInt("dplessure"));
			userbasic.setWeightNum(rs.getInt("weight_num"));
			userbasic.setBmi(rs.getFloat("bmi"));
			userbasic.setHdl(rs.getInt("hdl"));
			userbasic.setLdl(rs.getInt("ldl"));
			userbasic.setColNum(rs.getInt("col_num"));
			userbasic.setTg(rs.getInt("tg"));
			userbasic.setHeiwieght(rs.getString("heiwieght"));
			return userbasic;
		}
	};
	
	
	private RowMapper userbasicMapper2 = new RowMapper() {
		public UserBasic mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserBasic userbasic = new UserBasic();
			userbasic.setBasicSeq(rs.getInt("basic_seq"));
			userbasic.setUserId(rs.getString("user_id"));
			userbasic.setGender(rs.getInt("gender"));
			userbasic.setHeight(rs.getInt("height"));
			userbasic.setWeight(rs.getInt("weight"));
			userbasic.setWaist(rs.getInt("waist"));
			userbasic.setSmoke(rs.getInt("smoke"));
			userbasic.setBlood(rs.getString("blood"));
			userbasic.setCol(rs.getString("col"));
			userbasic.setPress(rs.getString("press"));
			userbasic.setRegDate(rs.getString("reg_date"));
			userbasic.setHeiwieght(rs.getString("heiwieght"));
			userbasic.setHaveHistory(rs.getString("have_history"));
			userbasic.setFamilyHistory(rs.getString("family_history"));
			userbasic.setDrugHistory(rs.getString("drug_history"));
			userbasic.setOralKind(rs.getString("oral_kind"));
			userbasic.setOralAmount(rs.getString("oral_amount"));
			userbasic.setOralUse(rs.getString("oral_use"));
			userbasic.setInsulinKind(rs.getString("insulin_kind"));
			userbasic.setInsulinAmount(rs.getString("insulin_amount"));
			userbasic.setInsulinUse(rs.getString("insulin_use"));
			userbasic.setMedicalReserve(rs.getString("medical_reserve"));
			userbasic.setComment(rs.getString("comment"));
			return userbasic;
		}
	};
	

	private RowMapper userbasicMapperHistory = new RowMapper() {
		public UserBasic mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserBasic userbasic = new UserBasic();
			userbasic.setBasicSeq(rs.getInt("basic_seq"));
			userbasic.setUserId(rs.getString("user_id"));
			userbasic.setGender(rs.getInt("gender"));
			userbasic.setHeight(rs.getInt("height"));
			userbasic.setWeight(rs.getInt("weight"));
			userbasic.setWaist(rs.getInt("waist"));
			userbasic.setSmoke(rs.getInt("smoke"));
			userbasic.setBlood(rs.getString("blood"));
			userbasic.setCol(rs.getString("col"));
			userbasic.setPress(rs.getString("press"));
			userbasic.setRegDate(rs.getString("reg_date"));
			userbasic.setHeiwieght(rs.getString("heiwieght"));
			userbasic.setHaveHistory(rs.getString("have_history"));
			userbasic.setFamilyHistory(rs.getString("family_history"));
			userbasic.setDrugHistory(rs.getString("drug_history"));
			userbasic.setOralKind(rs.getString("oral_kind"));
			userbasic.setOralAmount(rs.getString("oral_amount"));
			userbasic.setOralUse(rs.getString("oral_use"));
			userbasic.setInsulinKind(rs.getString("insulin_kind"));
			userbasic.setInsulinAmount(rs.getString("insulin_amount"));
			userbasic.setInsulinUse(rs.getString("insulin_use"));
			userbasic.setMedicalReserve(rs.getString("medical_reserve"));
			return userbasic;
		}
	};
	
	
	//유저기본정보 가져오기
	public UserBasic getUserBasic(String id) {
		String query = ""
				+ "	SELECT * "
				+ "	FROM V_NF_USER_BASIC "
				+ "	WHERE user_id = ? "
				+ "	order by basic_seq DESC"
				+ "	LIMIT 1 ";
		try {
			return (UserBasic)this.jdbcTemplate.queryForObject(query, new Object[] {id}, this.userbasicMapperV);
		} catch (Exception e) {
			// TODO: handle exception
			return new UserBasic();
		}
	}
	
	// 관리자 유저정보 가지고 오기
	public UserBasic getUserBasicView(String id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER_BASIC " +
				"WHERE user_id = ?";
		try {
			return (UserBasic)this.jdbcTemplate.queryForObject(query, new Object[] {id}, this.userbasicMapper2);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	
	// 관리자 유저정보 가지고 오기
	public UserBasic getUserBasicEdit(String id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER_BASIC " +
				"WHERE user_id = ?";
		try {
			return (UserBasic)this.jdbcTemplate.queryForObject(query, new Object[] {id}, this.userbasicMapperHistory);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	
	
	//문진수정,등록
	public void addUserBasic(final UserBasic userbasic) {
		String query = "" +
				"INSERT INTO T_NF_USER_BASIC " +
				"	(user_id, gender, height, weight, waist, smoke, blood, col, press, reg_date, heiwieght, have_history, family_history, drug_history, oral_kind, oral_amount, oral_use, insulin_kind, insulin_amount, insulin_use, medical_reserve, comment ) " +
				"VALUES " +
				"	( ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE(), ? ,?,?,?,?,?,?,?,?,?,?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			userbasic.getUserId(),
			userbasic.getGender(),
			userbasic.getHeight(),
			userbasic.getWeight(),
			userbasic.getWaist(),
			userbasic.getSmoke(),
			userbasic.getBlood(),
			userbasic.getCol(),
			userbasic.getPress(),
			userbasic.getHeiwieght(),
			userbasic.getHaveHistory(),
			userbasic.getFamilyHistory(),
			userbasic.getDrugHistory(),
			userbasic.getOralKind(),
			userbasic.getOralAmount(),
			userbasic.getOralUse(),
			userbasic.getInsulinKind(),
			userbasic.getInsulinAmount(),
			userbasic.getInsulinUse(),
			userbasic.getMedicalReserve(),
			userbasic.getComment()
		});
	}
	public void updateUserBasic1(UserBasic userbasic) {
		String query = "" + 
				"UPDATE T_NF_USER_BASIC SET " +
				" gender = ?, "+
				" height = ?, "+
				" weight = ?, "+
				" waist = ?, "+
				" smoke = ?, "+
				" blood = ?, "+
				" press = ?, "+
				" col = ?, "+
				" heiwieght = ? "+
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				userbasic.getGender(),
				userbasic.getHeight(),
				userbasic.getWeight(),
				userbasic.getWaist(),
				userbasic.getSmoke(),
				userbasic.getBlood(),
				userbasic.getPress(),
				userbasic.getCol(),
				userbasic.getHeiwieght(),
				userbasic.getUserId()
		});
	}
	
	public void updateUserBasic(UserBasic userbasic) {
		String query = "" + 
				"UPDATE T_NF_USER_BASIC SET " +
				"	have_history = ?, " +
				"	family_history = ?, " +
				"	drug_history = ?, " +
				"	oral_kind = ?, " +
				"	oral_amount = ?, " +
				"	oral_use = ?, " +
				"	insulin_kind = ?, " +
				"	insulin_amount = ?, " +
				"	insulin_use = ?, " +
				"	medical_reserve = ?, " +
				"	comment = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				userbasic.getHaveHistory(),
				userbasic.getFamilyHistory(),
				userbasic.getDrugHistory(),
				userbasic.getOralKind(),
				userbasic.getOralAmount(),
				userbasic.getOralUse(),
				userbasic.getInsulinKind(),
				userbasic.getInsulinAmount(),
				userbasic.getInsulinUse(),
				userbasic.getMedicalReserve(),
				userbasic.getComment(),
				userbasic.getUserId()
		});
	}
	
	public void updateUserWeight(int weight,String userId) {
		String query = "" + 
				"UPDATE T_NF_USER_BASIC SET " +
				
				"	weight = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				weight,userId
		
		});
	}
	
	/** 유저의 데이터 갯수 **/
	public int getCount(String userId) {
	    String query = " SELECT COUNT(*) FROM T_NF_USER_BASIC WHERE user_id =  ? ";
	    try{
	    	return this.jdbcTemplate.queryForInt(query, new Object[] { userId });
	    }catch(Exception e){
	    	return 0;
	    }
	}
	
	//해당아이디 삭제
	
	public void deleteUserBasic(String id) {
		String query = "DELETE FROM T_NF_USER_BASIC WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { id });
	}
	
}
