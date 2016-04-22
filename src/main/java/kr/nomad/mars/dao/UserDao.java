package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Counsel;
import kr.nomad.mars.dto.User;
import kr.nomad.util.T;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userMapper = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setUserType(rs.getInt("user_type"));
			user.setUserName(rs.getString("user_name"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setBirthday(rs.getString("birthday"));
			user.setGender(rs.getInt("gender"));
			user.setFileName(rs.getString("file_name"));
			user.setRegDate(rs.getString("reg_date"));
			user.setLastLogindate(rs.getString("last_logindate"));
			user.setLoginNaver(rs.getInt("login_naver"));
			user.setLoginKakao(rs.getInt("login_kakao"));
			user.setOsType(rs.getString("os_type"));
			user.setOsVersion(rs.getString("os_version"));
			user.setAppVersion(rs.getString("app_version"));
			user.setDeviceName(rs.getString("device_name"));
			user.setDeviceId(rs.getString("device_id"));
			user.setPushkey(rs.getString("pushkey"));
			user.setUsePushservice(rs.getInt("use_pushservice"));
			user.setStatus(rs.getInt("status"));
			user.setLoginStatus(rs.getString("login_status"));
			user.setUserMed(rs.getInt("user_med"));
			user.setBelongTo(rs.getString("belong_to"));
			user.setNoticeBadge(rs.getInt("notice_badge"));
			return user;
		}
	};
	
	private RowMapper userMapper2 = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setUserType(rs.getInt("user_type"));
			user.setUserName(rs.getString("user_name"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setBirthday(rs.getString("birthday"));
			user.setGender(rs.getInt("gender"));
			user.setFileName(rs.getString("file_name"));
			user.setRegDate(rs.getString("reg_date"));
			user.setLastLogindate(rs.getString("last_logindate"));
			user.setLoginNaver(rs.getInt("login_naver"));
			user.setLoginKakao(rs.getInt("login_kakao"));
			user.setOsType(rs.getString("os_type"));
			user.setOsVersion(rs.getString("os_version"));
			user.setAppVersion(rs.getString("app_version"));
			user.setDeviceName(rs.getString("device_name"));
			user.setDeviceId(rs.getString("device_id"));
			user.setPushkey(rs.getString("pushkey"));
			user.setUsePushservice(rs.getInt("use_pushservice"));
			user.setStatus(rs.getInt("status"));
			user.setLoginStatus(rs.getString("login_status"));
			user.setUserMed(rs.getInt("user_med"));
			user.setBelongTo(rs.getString("belong_to"));
			user.setBlood(rs.getString("blood"));
			user.setCol(rs.getString("col"));
			user.setPress(rs.getString("press"));
			user.setHeiwieght(rs.getString("heiwieght"));
			return user;
		}
	};
	
	public User getUser(String user_id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER " +
				"WHERE user_id = ? ";
		try {
			return (User)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.userMapper);
		} catch (Exception e) {
			return new User();
		}
	}
	
	/** 사용자 목록 **/
	public List<User> getUserList(String keyword, int gender, int age,  int page, int itemCountPerPage) {
	    
		String condition = "WHERE status=1 AND (user_type = 1 OR user_type =3) ";
	    
	    int year = Integer.parseInt(T.getTodayYear());
	    
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
	    
	    if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%' OR user_id like '%" + keyword + "%'";
		}
	    
		if (age == 1) {
			condition += " AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
	    
	 	String query = ""
	            + " SELECT * "
	            + "	FROM ( "
	            + " 	SELECT "
	            + " 		* "
	            + " 	FROM V_NF_USER "
	            + " " + condition
	    		+ " 	ORDER BY reg_date DESC "
	    		+ "	) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<User>)this.jdbcTemplate.query(query,this.userMapper2);
	}
	
	/** 일반회원 갯수 **/
	public int getCount(String keyword, int gender, int age) {
		
		String condition = "WHERE status=1  AND (user_type = 1 OR user_type =3) ";
	    
	    int year = Integer.parseInt(T.getTodayYear());
	    
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
	    
	    if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%' OR user_id like '%" + keyword + "%'";
		}
	    
		if (age == 1) {
			condition += " AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
	    
		
	    String query = " SELECT COUNT(*) FROM V_NF_USER "+ condition;
	    
	    return this.jdbcTemplate.queryForInt(query);
	}
	public int getCountDrop(String keyword, int gender, int age) {
		
		String condition = "WHERE status=4  AND (user_type = 1 OR user_type =3) ";
	    
	    int year = Integer.parseInt(T.getTodayYear());
	    
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
	    
	    if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%' OR user_id like '%" + keyword + "%'";
		}
	    
		if (age == 1) {
			condition += " AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
	    
		
	    String query = " SELECT COUNT(*) FROM V_NF_USER "+ condition;
	    
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
	/** 상담원 목록 **/
	public List<User> getCounselorList(String keyword, int gender, int age,  int page, int itemCountPerPage) {
	    
		String condition = "WHERE 1=1 AND user_type = 2 ";
	    
	    int year = Integer.parseInt(T.getTodayYear());
	    
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
	    
	    if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%' OR user_id like '%" + keyword + "%'";
		}
	    
		if (age == 1) {
			condition += " AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
	    
	 	String query = ""
	            + " SELECT * FROM ( "
	            + " 	SELECT "
	            + " 		* "
	            + " 	FROM T_NF_USER "
	            + " " + condition
	    		+ " 	ORDER BY reg_date DESC "
	    		+ " ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<User>)this.jdbcTemplate.query(query,this.userMapper);
	}
	
	/** 상담원 갯수 **/
	public int getCounselorCount(String keyword, int gender, int age) {
		
		String condition = "WHERE 1=1  AND user_type = 2  ";
	    
	    int year = Integer.parseInt(T.getTodayYear());
	    
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
	    
	    if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%' OR user_id like '%" + keyword + "%'";
		}
	    
		if (age == 1) {
			condition += " AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
	    
		
	    String query = " SELECT COUNT(*) FROM T_NF_USER "+ condition;
	    
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
	/**
	 * 아이디 패스워드 일치 확인
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean correctPw(String userId, String password) {		
	    String query = "SELECT count(*) AS id_cnt FROM T_NF_USER WHERE user_id = ? and password = ? ";
	    try {
		    return this.jdbcTemplate.queryForInt(query, new Object[] { userId, password}) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean correctNaver(String userId) {		
	    String query = "SELECT count(*) AS id_cnt FROM T_NF_USER WHERE user_id = ? and login_naver = 1";
	    try {
		    return this.jdbcTemplate.queryForInt(query, new Object[] { userId}) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean correctKakao(String userId) {		
	    String query = "SELECT count(*) AS id_cnt FROM T_NF_USER WHERE user_id = ? and login_kakao = 1";
	    try {
		    return this.jdbcTemplate.queryForInt(query, new Object[] { userId}) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	
	/**
	 * 로그인성공시 업데이트
	 * 
	 * 
	 */
	
	public void updateUserData(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	last_logindate = SYSDATE() , " +
				"	os_type = ?, " +
				"	os_version = ?, " +
				"	app_version = ?, " +
				"	device_name = ?, " +
				"	device_id = ?, " +
				"	pushkey = ?, " +
				"	login_status = 1 " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			
			user.getOsType(),
			user.getOsVersion(),
			user.getAppVersion(),
			user.getDeviceName(),
			user.getDeviceId(),
			user.getPushkey(),
			user.getUserId()
		});
	}
	

	/**
	 * 아이디 중복 확인
	 * @param userId
	 * @return
	 */
	public boolean correctId(String userId) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_id = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userId }) == 1);
	}
	
	/**
	 * 전화번호중복 확인
	 * 
	 * 
	 */
	
	
	public boolean correctPhone(String phoneNumber) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE phone_number = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { phoneNumber }) == 1);
	}
	

	/**
	 * 아이디 찾기
	 * @param phoneNumber
	 * @return
	 */
	
	public User findId(String phoneNumber,String userName) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER " +
				"WHERE phone_number = ? and user_name = ? ";
		
		try {
			return (User)this.jdbcTemplate.queryForObject(query, new Object[] {phoneNumber ,userName }, this.userMapper);
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 비밀번호 찾기
	 * @param userId
	 * @return
	 */
	public boolean findPw(String userId,String phoneNumber) {
		String query = "SELECT count(*) AS id_cnt FROM T_NF_USER WHERE user_id = ? and phone_number = ?";
	   try {
		    return this.jdbcTemplate.queryForInt(query, new Object[] { userId, phoneNumber}) > 0;
		    } catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 비밀번호 재설정
	 * @param userId
	 * @return
	 */
	public void updatePw(String userId,String password) {
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	password = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			password,
			userId
		});
		
	}
	


	/**
	 * 유저정보 가져오기
	 * 
	 * 
	 */
	
	public User getUsers(String userId) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER " +
				"WHERE user_id = ?  ";
		
		try {
			return (User)this.jdbcTemplate.queryForObject(query, new Object[] { userId }, this.userMapper);
			
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 탈퇴
	 * 
	 * 
	 */
	
	public void deleteUser(String id) {
		
		String query = "DELETE FROM T_NF_USER WHERE user_id = ? ";
		try {
				this.jdbcTemplate.update(query, new Object[] { id  });
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//회원가입
	public void addUser(final User user) {
		String query = "" +
				"INSERT INTO T_NF_USER"+ 
				" (user_id, password, user_type, user_name, phone_number, birthday, gender, reg_date,"
				+ " last_logindate, os_type, os_version, app_version, device_name, device_id, pushkey, use_pushservice, "
				+ " status, file_name, login_naver, login_kakao,user_med,belong_to, aimmed_id, group_code, group_name ) "+
				" VALUES " +
				" ( ?, ?, ?, ?, ?, ?, ?, SYSDATE(), "
				+ " SYSDATE(), ?, ?, ?, ?, ?, ?, ?, "
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?) " ;
		this.jdbcTemplate.update(query, new Object[] {
			user.getUserId(),
			user.getPassword(),
			user.getUserType(),
			user.getUserName(),
			user.getPhoneNumber(),
			user.getBirthday(),
			user.getGender(),
			user.getOsType(),
			user.getOsVersion(),
			user.getAppVersion(),
			user.getDeviceName(),
			user.getDeviceId(),
			user.getPushkey(),
			user.getUsePushservice(),
			user.getStatus(),
			user.getFileName(),
			user.getLoginNaver(),
			user.getLoginKakao(),
			user.getUserMed(),
			user.getBelongTo(),
			user.getAimmedId(),
			user.getGroupCode(),
			user.getGroupName()			
		});
	}
	
	//전화번호변경
	public void editPhone(String id,String ph ) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
			    "	phone_number = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {ph,id});
	}
	
	/**
	 * 프로필 사진 등록
	 * @param user
	 */
	public void updateProfileImgAdd(String id,String filename) {
		String query = "" +
				"UPDATE T_NF_USER SET " +
				" file_name = ? " +
				"WHERE user_id = ?" ;
			this.jdbcTemplate.update(query,new Object[]{
				filename,id
			});
	}
	
	/**
	 * 프로필 사진 삭제
	 * @param userId
	 */
	public void updateProfileImgDel(String userId) {
		String query = "" +
				"UPDATE T_NF_USER SET " +
				" file_name = '' " +
				"WHERE user_id = ?" ;
			this.jdbcTemplate.update(query,new Object[]{
				userId
			});
	}
	
	public void updateUser(String userId) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	user_med = 1 " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				userId
		});
	}
	
	public void updatedrop(String userId) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	phone_number ='', " +
				"	pushkey ='', " +
				"	login_status = 0 , " +
				"	status = 4 " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				userId
		});
	}
	public void updateadmin(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	user_name = ?, " +
			
				"	birthday = ?, " +
				"	gender = ?, " +
				" belong_to = ? "+
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				user.getUserName(),
			
				user.getBirthday(),
				user.getGender(),
				user.getBelongTo(),
				user.getUserId()
				
		});
	}
	public void updateout(String userId) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
			    " login_status = 0, "+
				" pushkey ='' "+
				" WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {userId});
	}
	
	
	
//////////////////////////////////////////////////	
	
	
	public void updateUser(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	user_id = ?, " +
				"	password = ?, " +
				"	user_type = ?, " +
				"	user_name = ?, " +
				"	phone_number = ?, " +
				"	birthday = ?, " +
				"	gender = ?, " +
				"	reg_date = ?, " +
				"	last_logindate = ?, " +
				"	login_naver = ?, " +
				"	login_kakao = ?, " +
				"	os_type = ?, " +
				"	os_version = ?, " +
				"	app_version = ?, " +
				"	device_name = ?, " +
				"	device_id = ?, " +
				"	pushkey = ?, " +
				"	use_pushservice = ?, " +
				"	status = ?, " +
				"	login_status = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			user.getUserId(),
			user.getPassword(),
			user.getUserType(),
			user.getUserName(),
			user.getPhoneNumber(),
			user.getBirthday(),
			user.getGender(),
			user.getRegDate(),
			user.getLastLogindate(),
			user.getLoginNaver(),
			user.getLoginKakao(),
			user.getOsType(),
			user.getOsVersion(),
			user.getAppVersion(),
			user.getDeviceName(),
			user.getDeviceId(),
			user.getPushkey(),
			user.getUsePushservice(),
			user.getStatus(),
			user.getLoginStatus()
		});
	}

/*	public User getUser(String user_id) {
		String query = "" + 
				"SELECT user_id, password, user_type, user_name, phone_number, birthday, gender, reg_date, last_logindate, login_naver, login_kakao, os_type, os_version, app_version, device_name, device_id, pushkey, use_pushservice, status, login_status " +
				"FROM T_NF_USER " +
				"WHERE user_id = ? ";
		try {
			return (User)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.userMapper);
			
		} catch (Exception e) {
			return null;
		}
	}
*/

	/*
	public List<User> getUserList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" user_id, password, user_type, user_name, email, nick_name, phone_number, intro, address, latitude, longitude, birth_year, gender, area, reg_date, last_logindate, login_facebook, login_kakao, os_type, os_version, app_version, device_name, device_id, pushkey, use_pushservice, status, point, income, picture, user_level, level_point " +
				"FROM T_NF_USER " +
				"WHERE user_id <= ( " +
				"	SELECT MIN(user_id) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" user_id " +
				"		FROM T_NF_USER " +
				"		ORDER BY user_id DESC " +
				"	) AS A) " +
				"ORDER BY user_id DESC";
		return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
	}
	*/

	public List<User> getUserList(int userType) {
		String query = ""
	            + "	SELECT * "
	            + "	FROM T_NF_USER "
	            + "	WHERE user_type = ? ";
		return (List<User>)this.jdbcTemplate.query(query, new Object[] { userType }, this.userMapper);
	}
	
	
	//복약 푸시
	public List<User> getUserListEatMedicine(String time, String week) {
		String con="";
		if(week.equals("일")){ //일요일
			con ="and mediweek1 =1";
		}else if(week.equals("월")){ //월요일
			con ="and mediweek2 =1";
		}else if(week.equals("화")){ //화요일
			con ="and mediweek3 =1";
		}else if(week.equals("수")){ //수요일
			con ="and mediweek4 =1";
		}else if(week.equals("목")){ //목요일
			con ="and mediweek5 =1";
		}else if(week.equals("금")){ //금요일
			con ="and mediweek6 =1";
		}else if(week.equals("토")){ //토요일
			con ="and mediweek7 =1";
		}
		String query = ""
	            + " SELECT "
	            + " 	* " 
	            + " FROM T_NF_USER "
	            + "	where user_id in (select user_id from T_NF_USER_MEDICINE where meditime= ? and medialert=1 " + con +") "
	            + "	ORDER BY user_id desc ";
				// 복약대상자 조건 : 복약해야할 요일, 시간이 맞고, pushkey가 저장된 사용자
		return (List<User>)this.jdbcTemplate.query(query, new Object[] {time}, this.userMapper);
	}
	//기간내 미등록 푸시
	public List<User> getUserListEperiod(String today,String bloodBefore,String pressBefore,String weigthBefore) {
		String con="";
		
		String query = ""
	            + " SELECT "
	            + " 	* " 
	            + " FROM T_NF_USER "
	            + "	where "
	            + "		user_type = 3 and "
	            + "		user_id not in ("
	            + "			select user_id from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
	            +"			union "
	            + "			select user_id from T_NF_USER_BlOOD where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
	            +"			union "
	            + "			select user_id from T_NF_USER_Weight where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? ) "
	            + " ORDER BY user_id desc ";

				
		return (List<User>)this.jdbcTemplate.query(query, new Object[] {pressBefore,today,bloodBefore,today,weigthBefore,today}, this.userMapper);
	}
	
	//cvrisk 
	public List<User> getUserListcvrisk(String today) {
		String con="";
		
		String query = ""
				+"SELECT "
				+" * " 
				+"FROM T_NF_USER where user_type =3 and  DATEDIFF(DD,reg_date,SYSDATE()) in(1 ,365) and "
				+"	user_id not in"
				+" (	SELECT distinct(user_id) FROM T_NF_USER_CVRISK WHERE DATE_FORMAT(reg_date, '%Y-%m-%d') = ? ) ";

				
		return (List<User>)this.jdbcTemplate.query(query, new Object[] {today}, this.userMapper);
	}
	
	//관리주기변경 푸시
	
	
	public List<User> getUserListCperiod(String today,String bloodBefore,String before,String after,String before2,String after2,
			String before3,String after3,String before4) {
		String con="";
		
		String query = ""
	            + "  SELECT "
	            + "        * " 
	            + "    FROM T_NF_USER where use_pushservice =1 and user_type = 3 and"
	            + "	user_id in "
	            + "(select c.user_id from ( "
	            + " select d.user_id, sum(d.cnt) as week_cnt from ("
	            + " select user_id, case when count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) > 0 then 1 else 0 end as cnt"
	            + " from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
	            + " group by user_id "
	            + "	union all "
	            + " select user_id, case when count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) > 0 then 1 else 0 end as cnt"
	            + " from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
	            + " group by user_id "
	            + "	union all "
	            + " select user_id, case when count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) > 0 then 1 else 0 end as cnt"
	            + " from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
	            + " group by user_id "
	            + "	union all "
	            + " select user_id, case when count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) > 0 then 1 else 0 end as cnt"
	            + " from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
	            + "	group by user_id) as d group by d.user_id having sum(cnt) =4 )as c  "
	            + "	union all"
	            + "	select user_id from (select user_id, count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) as cnt from T_NF_USER_BlOOD" 
	            + "	where reg_date DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? and blood_kind =3"
	            + "	group by user_id)"
	            + " as a where cnt >= 3 )";

				
		return (List<User>)this.jdbcTemplate.query(query, new Object[] {before,after,before2,after2,before3,after3,before4,today,bloodBefore,today}, this.userMapper);
	}
	
	//관리주기변경 푸시(혈압)
	
	
		public List<User> getUserpressListCperiod(String today,String before,String after,String before2,String after2,
				String before3,String after3,String before4) {
			String con="";
			
			String query = ""
		            + "  SELECT "
		            + "        * " 
		            + "    FROM T_NF_USER where user_type = 3 and"
		            + "	user_id in "
		            + "(select c.user_id from ( "
		            + " select d.user_id, sum(d.cnt) as week_cnt from ("
		            + " select user_id, case when count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) > 0 then 1 else 0 end as cnt"
		            + " from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
		            + " group by user_id "
		            + "	union all "
		            + " select user_id, case when count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) > 0 then 1 else 0 end as cnt"
		            + " from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
		            + " group by user_id "
		            + "	union all "
		            + " select user_id, case when count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) > 0 then 1 else 0 end as cnt"
		            + " from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
		            + " group by user_id "
		            + "	union all "
		            + " select user_id, case when count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) > 0 then 1 else 0 end as cnt"
		            + " from T_NF_USER_PRESSURE where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? "
		            + "	group by user_id) as d group by d.user_id having sum(cnt) =4 )as c )";

					
			return (List<User>)this.jdbcTemplate.query(query, new Object[] {before,after,before2,after2,before3,after3,before4,today}, this.userMapper);
		}

	//관리주기변경(혈당) 푸시
		
		public List<User> getUserbloodListCperiod(String today,String bloodBefore) {
			String con="";
			
			String query = ""
		            + "  SELECT "
		            + "        * " 
		            + "    FROM T_NF_USER where user_type = 3 and"
		            + "	user_id in "
		            + "(select c.user_id from ( "
		            + "	select user_id from (select user_id, count(distinct DATE_FORMAT(reg_date, '%Y-%m-%d')) as cnt from T_NF_USER_BlOOD" 
		            + "	where DATE_FORMAT(reg_date, '%Y-%m-%d') between  ? and  ? and blood_kind =3"
		            + "	group by user_id)"
		            + " as a where cnt >= 3 )";

					
			return (List<User>)this.jdbcTemplate.query(query, new Object[] {bloodBefore,today}, this.userMapper);
		}	
	
	
	/**
	 * 닉네임 중복 확인
	 * @param userId
	 * @return
	 */
	public boolean correctNick(String userName) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_name = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userName }) == 1);
	}
	
	



	

	
	
	
	/**
	 * 비밀번호 변경
	 * @param userId
	 * @param userPassword
	 */
	public void updateUserPassword(String userId, String password) { 
		String query = "" + 
				"	UPDATE T_NF_USER  " +
				"	SET password = ? " +
				"	WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { password, userId });
	}
	
	
	/** 일반회원 검색 목록 **/
	public List<User> getUserList(int gender, int age, String keyword, int page, int itemCountPerPage) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 ";
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		
		if (age == 1) {
			condition += " AND (CAST(birthday AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += " AND (CAST(birthday AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += " AND (CAST(birthday AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += " AND (CAST(birthday AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += " AND (CAST(birthday AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
		
		
		if (keyword.equals("") == false) {
			condition += " AND user_id like '%"+ keyword +"%'";
		}

		String query = ""
	            + " SELECT * FROM ( "
	            + " 	SELECT "
	            + " 		* "
	            + " 	FROM T_NF_USER "
	            + " "+condition+" "
	            + " 	ORDER BY user_id desc "
	            + "	) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
	}
	
	
	/** 일반회원 검색결과 갯수 **/
	public int getCount(int gender, int age, String keyword) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 ";
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
//		if (age == 1) {
//			condition += " AND (CAST(birthday AS INT) between "+(year-19)+" and "+(year-10)+")";
//		} else if (age == 2) {
//			condition += " AND (CAST(birthday AS INT) between "+(year-29)+" and "+(year-20)+" )";
//		} else if (age == 3) {
//			condition += " AND (CAST(birthday AS INT) between "+(year-39)+"  and "+(year-30)+" )";
//		} else if (age == 4) {
//			condition += " AND (CAST(birthday AS INT) between "+(year-49)+" and "+(year-40)+" )";
//		} else if (age == 5) {
//			condition += " AND (CAST(birthday AS INT) between "+(year-89)+" and "+(year-50)+"  )";
//		}
		if (keyword.equals("") == false) {
			condition += " AND user_id like '%"+ keyword +"%'";
		}

		
	    String query = " SELECT COUNT(*) FROM T_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
	/** 관리자 검색 목록 **/
	public List<User> getAdminList(String keyword,int page, int itemCountPerPage) {
		
		String condition = " WHERE 1=1 ";
		

		if (keyword.equals("") == false) {
			condition += " AND user_id like '%"+ keyword +"%'";
		}
//		if (areaSido.equals("") == false) {
//			condition += " AND area like '%"+ areaSido +"%'";
//		}
		
		if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%'";
		}
		
		String query = ""
	            + " SELECT * FROM ( "
	            + " 	SELECT "
	            + " 		* "
	            + " 	FROM T_NF_USER "
	            + " 	"+condition+" "
	            + " 	ORDER BY user_id desc "
	            + " ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
	}
	
	
	/** 일반회원 검색결과 갯수 **/
	public int getAdminCount(String keyword) {
		
		String condition = " WHERE 1=1 ";

		if (keyword.equals("") == false) {
			condition += " AND user_id like '%"+ keyword +"%'";
		}
//		if (areaSido.equals("") == false) {
//			condition += " AND area like '%"+ areaSido +"%'";
//		}
		
	    String query = " SELECT COUNT(*) FROM T_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	// 일반회원 엑셀 다운로드
	public List<User> getUser() {
		String query = ""
				+ "SELECT *  "
				+ "FROM V_NF_USER WHERE user_type = 1 OR user_type = 3  ORDER BY user_id  ";
		return (List<User>) this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	// 상담원 엑셀 다운로드
	public List<User> getCounselorUser() {
		String query = ""
				+ "SELECT *  "
				+ "FROM T_NF_USER WHERE user_type = 2 ORDER BY user_id  ";
		return (List<User>) this.jdbcTemplate.query(query, this.userMapper);
	}
	
	

	// 최근 담당 상담자
	public User getCounselor(String userId) {
		String query = ""
				+ " select * "
				+ "	from t_nf_user "
				+ "	where user_id = "
				+ "		( "
				+ "			select "
				+ "				user_id "
				+ "			from t_nf_chat_member "
				+ "			where "
				+ "				chat_room_seq = ( "
				+ "					select chat_room_seq "
				+ "					from t_nf_chat_member "
				+ "					where user_id = ? "
				+ "					order by reg_date desc "
				+ "					limit 1 "
				+ "				) "
				+ "				and user_id <> ? "
				+ "			order by reg_date desc "
				+ "			limit 1 "
				+ "		) ";
		try {
			return (User) this.jdbcTemplate.queryForObject(query, new Object[] { userId, userId }, this.userMapper);
		} catch (Exception e) {
			return null;
		}
	}

	public User getCounselorOne() {
		String query = ""
				+ "	select u.* "
				+ "	from t_nf_user as u where u.user_type = 2 "
				+ "	order by (select count(*) from t_nf_chat_member where user_id = u.user_id) asc "
				+ "	limit 1 ";
		/*
		String query = ""
				+ "	select * "
				+ "	from t_nf_user "
				+ "	where user_id = ( "
				+ "		select top 1 m.user_id "
				+ "		from t_nf_chat_member as m left outer join t_nf_user as u on u.user_id = m.user_id "
				+ "		where u.user_type = 2 "
				+ "		group by DATE_FORMAT(reg_date, '%Y-%m-%d'), m.user_id "
				+ "		order by count(*), newid() "
				+ "	) ";
		*/
		try {
			return (User) this.jdbcTemplate.queryForObject(query, this.userMapper);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/** 탈퇴회원 목록 **/
	public List<User> getUserDropList(String keyword, int gender, int age,  int page, int itemCountPerPage) {
	    
		String condition = "WHERE status=4 ";	//AND user_type = 5
	    
	    int year = Integer.parseInt(T.getTodayYear());
	    
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
	    
	    if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%' OR user_id like '%" + keyword + "%'";
		}
	    
		if (age == 1) {
			condition += " AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
	    
	 	String query = ""
	            + " SELECT * FROM ( "
	            + " 	SELECT "
	            + "			* "
	            + " 	FROM T_NF_USER "
	            + " 	" + condition
	    		+ " 	ORDER BY reg_date DESC "
	    		+ " ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
	    return (List<User>)this.jdbcTemplate.query(query,this.userMapper);
	}
	
	/** 사용자 갯수 **/
	public int getDropCount(String keyword, int gender, int age) {
		
		String condition = "WHERE 1=1  AND user_type = 5 ";
	    
	    int year = Integer.parseInt(T.getTodayYear());
	    
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
	    
	    if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%' OR user_id like '%" + keyword + "%'";
		}
	    
		if (age == 1) {
			condition += " AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += "  AND (CAST(SUBSTRING(birthday,1,4) AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
	    
		
	    String query = " SELECT COUNT(*) FROM T_NF_USER "+ condition;
	    
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	//푸시 사용자 리스트
	public List<User> getUserPushList(){
		
		String query = ""
				+ "SELECT *  "
				+ "FROM T_NF_USER WHERE user_type = 3 and use_pushservice=1 ";
		return (List<User>) this.jdbcTemplate.query(query, this.userMapper);
		
	}
	
	//푸시 뱃지 업데이트
	public void updateBadge(String userId,int num){
		
		String query = ""
				+ "update T_NF_USER set   "
				+ "notice_badge  = ? "
				+ "where user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {  num , userId});
		
	}
	
	//1개월레포트 유저리스트
	

	public List<User> getUserMonthReportList(String month) {

		String query = "" 
				+" SELECT * "   
				+" FROM T_NF_USER WHERE user_type = 3 "
				+" and  datediff(day,'"+month+"'+(select datename(dd,reg_date)),SYSDATE())=-3 ";
		
		return (List<User>) this.jdbcTemplate.query(query, this.userMapper);

	}
	
	
	//1주레포트 유저리스트
	

	public List<User> getUserWeekReportList() {

		String query = "" 
				+" SELECT * "   
				+" FROM T_NF_USER WHERE user_type = 3 "
				+" and  weekday(ADDDATE(reg_date,interval -1 day)) =weekday(SYSDATE()) ";
		
		return (List<User>) this.jdbcTemplate.query(query, this.userMapper);

	}

	
}