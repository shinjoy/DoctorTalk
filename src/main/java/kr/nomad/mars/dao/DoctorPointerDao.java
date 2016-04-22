package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.DoctorPointer;
import kr.nomad.util.T;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DoctorPointerDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper doctorpointerMapper = new RowMapper() {
		public DoctorPointer mapRow(ResultSet rs, int rowNum) throws SQLException {
			DoctorPointer doctorpointer = new DoctorPointer();
			doctorpointer.setComSeq(rs.getInt("com_seq"));
			doctorpointer.setDiseaseId(rs.getString("disease_id"));
			doctorpointer.setComment(rs.getString("comment"));
			doctorpointer.setSort(rs.getInt("sort"));
			doctorpointer.setAskind(rs.getInt("askind"));
			doctorpointer.setPseq(rs.getInt("pseq"));
			doctorpointer.setMove(rs.getInt("move"));
			doctorpointer.setValue(rs.getString("value"));
			doctorpointer.setAnsType(rs.getInt("ans_type"));
			doctorpointer.setQtype(rs.getString("qtype"));
			doctorpointer.setAnsvalue(rs.getInt("ansvalue"));
			doctorpointer.setIsLast(rs.getInt("is_last"));
			return doctorpointer;
		}
	};

	public void addDoctorPointer(final DoctorPointer doctorpointer) {
		String query = "" +
				"INSERT INTO T_NF_DOCTOR_POINTER " +
				"	(disease_id, comment, sort, askind, pseq, move, value, ans_type, qtype, ansvalue,is_last) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			doctorpointer.getDiseaseId(),
			doctorpointer.getComment(),
			doctorpointer.getSort(),
			doctorpointer.getAskind(),
			doctorpointer.getPseq(),
			doctorpointer.getMove(),
			doctorpointer.getValue(),
			doctorpointer.getAnsType(),
			doctorpointer.getQtype(),
			doctorpointer.getAnsvalue(),
			doctorpointer.getIsLast()
		});
	}

	public void deleteDoctorPointer(int com_seq) {
		String query = "DELETE FROM T_NF_DOCTOR_POINTER WHERE com_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { com_seq });
	}

	public void updateDoctorPointer(DoctorPointer doctorpointer) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_POINTER SET " +
				"	disease_id = ?, " +
				"	comment = ?, " +
				"	sort = ?, " +
				"	askind = ?, " +
				"	pseq = ?, " +
				"	move = ?, " +
				"	value = ?, " +
				"	ans_type = ?, " +
				"	qtype = ?, " +
				"	ansvalue = ? " +
				"	is_last = ? " +
				"WHERE com_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			doctorpointer.getDiseaseId(),
			doctorpointer.getComment(),
			doctorpointer.getSort(),
			doctorpointer.getAskind(),
			doctorpointer.getPseq(),
			doctorpointer.getMove(),
			doctorpointer.getValue(),
			doctorpointer.getAnsType(),
			doctorpointer.getQtype(),
			doctorpointer.getAnsvalue(),
			doctorpointer.getIsLast(),
			doctorpointer.getComSeq()
		});
	}
	
	
	public void updateDoctorPointerTable(DoctorPointer doctorpointer) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_POINTER SET " +
				"	comment = ?, " +
				"	pseq = ?, " +
				"	move = ?, " +
				"	ans_type = ? " +
				"WHERE com_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			doctorpointer.getComment(),
			doctorpointer.getPseq(),
			doctorpointer.getMove(),
			doctorpointer.getAnsType(),
			doctorpointer.getComSeq()
		});
	}
	

	public DoctorPointer getDoctorPointer(int com_seq) {
		String query = "" + 
				"SELECT com_seq, disease_id, comment, sort, askind, pseq, move, value, ans_type, qtype, ansvalue, is_last " +
				"FROM T_NF_DOCTOR_POINTER " +
				"WHERE com_seq = ? ";
		try {
			return (DoctorPointer)this.jdbcTemplate.queryForObject(query, new Object[] { com_seq }, this.doctorpointerMapper);
		} catch (Exception e) {
			return new DoctorPointer();
		}
		
		
	}
	
	// 테이블 내용 수정 처리 
	public void updateDoctorPointer(String comment, int comSeq) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_POINTER SET " +
				"	comment = ? " +
				"WHERE com_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {comment ,comSeq});
	}
	
	
	// 정렬값 변경
	public void updateDoctorPointer(int sort ,int comSeq) { 
		
		String query = "" + 
				"UPDATE T_NF_DOCTOR_POINTER SET " +
				"	sort = ? " +
				" WHERE com_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {sort,comSeq});
	}

	// 체크값 변경
	public void updateDoctorPointerIsLast(int is_last ,int comSeq) { 
		
		String query = "" + 
				"UPDATE T_NF_DOCTOR_POINTER SET " +
				"	is_last = ? " +
				" WHERE com_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {is_last,comSeq});
	}
	
	
	
	/** 혈당 검색 목록 **/
	public List<DoctorPointer> getBloodList(int page, int itemCountPerPage) {

		String query = ""
	            + "    SELECT * FROM ( "
	            + "         SELECT "
	            + "            * "
	            + "         FROM T_NF_DOCTOR_POINTER "
	            + "  		WHERE disease_id = 'blood' "
	            + "			ORDER BY sort asc "
	            + "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<DoctorPointer>)this.jdbcTemplate.query(query, this.doctorpointerMapper);
	}
	
	/** 혈당 검색 목록 **/
	public List<DoctorPointer> getBloodList() {

		String query = ""
	         
	            + "        SELECT "
	 
	            + "            * "
	            + "        FROM T_NF_DOCTOR_POINTER "
	            + "  		WHERE disease_id = 'blood'  order by sort";
	  
	    return (List<DoctorPointer>)this.jdbcTemplate.query(query, this.doctorpointerMapper);
	}

	
	
	/** 혈당 검색결과 갯수 **/
	public int getBloodCount() {
		
	    String query = " SELECT COUNT(*) FROM T_NF_DOCTOR_POINTER WHERE disease_id = 'blood'";
	    
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	/** 혈압 검색 목록 **/
	public List<DoctorPointer> getPressList(int page, int itemCountPerPage) {

		String query = ""
	            + "    SELECT * FROM ( "
	            + "         SELECT "
	            + "            * "
	            + "         FROM T_NF_DOCTOR_POINTER "
	            + "  		WHERE disease_id = 'press' "
	            + "			ORDER BY sort asc "
	            + "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<DoctorPointer>)this.jdbcTemplate.query(query, this.doctorpointerMapper);
	}
	

	/** 혈압 검색 목록 **/
	public List<DoctorPointer> getPressList() {

		String query = ""
	            + "        SELECT "
	            + "            * "
	            + "        FROM T_NF_DOCTOR_POINTER "
	            + "  		WHERE disease_id = 'press' order by sort";
	        
	    return (List<DoctorPointer>)this.jdbcTemplate.query(query, this.doctorpointerMapper);
	}
	
	
	
	/** 혈압 검색결과 갯수 **/
	public int getPressCount() {
		
	    String query = " SELECT COUNT(*) FROM T_NF_DOCTOR_POINTER WHERE disease_id = 'press'";
	    
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	/** 콜레스테롤 검색 목록 **/
	public List<DoctorPointer> getColList(int page, int itemCountPerPage) {

		String query = ""
	            + "    SELECT * FROM ( "
	            + "         SELECT "
	            + "            * "
	            + "         FROM T_NF_DOCTOR_POINTER "
	            + "  		WHERE disease_id = 'col' "
	            + "			ORDER BY sort asc "
	            + "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<DoctorPointer>)this.jdbcTemplate.query(query, this.doctorpointerMapper);
	}
	
	
	/** 콜레스테롤 검색결과 갯수 **/
	public int getColCount() {
		
	    String query = " SELECT COUNT(*) FROM T_NF_DOCTOR_POINTER WHERE disease_id = 'col'";
	    
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	/** 체중,당화혈색소 검색 목록 **/
	public List<DoctorPointer> getWeightList(int page, int itemCountPerPage) {

		String query = ""
	            + "    SELECT * FROM ( "
	            + "         SELECT "
	            + "            * "
	            + "         FROM T_NF_DOCTOR_POINTER "
	            + "  		WHERE disease_id = 'weight' OR disease_id = 'hba' "
	            + "			ORDER BY sort asc "
	            + "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<DoctorPointer>)this.jdbcTemplate.query(query, this.doctorpointerMapper);
	}
	
	
	/** 체중,당화혈색소 검색결과 갯수 **/
	public int getWeightCount() {
		
	    String query = " SELECT COUNT(*) FROM T_NF_DOCTOR_POINTER WHERE disease_id = 'weight' OR disease_id = 'hba' ";
	    
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	

}
