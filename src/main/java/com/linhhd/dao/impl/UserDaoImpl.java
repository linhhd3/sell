package com.linhhd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.linhhd.dao.UserDao;
import com.linhhd.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	public void addUser(User user) {
		// With jbbc template
//		String sql = "INSERT INTO USER(TEN_KH, SO_DT) VALUE (?,?)";
//		jdbcTemplate.update(sql, user.getName(), user.getPhone());
		
		// With Hibernate
		sessionFactory.getCurrentSession().save(user);
	}

	public void updateUser(User user) {
		// With jbbc template
//		String sql = "UPDATE USER SET TEN_KH =? , SO_DT =? WHERE ID = ?";
//		jdbcTemplate.update(sql, user.getName(), user.getPhone(), user.getId());
		
		// With Hibernate
		sessionFactory.getCurrentSession().merge(user);
	}

	public void deleteUser(int id) {
		// With jbbc template
//		String sql = "DELETE FROM USER WHERE ID = ?";
//		jdbcTemplate.update(sql, id);
		
		// With Hibernate
		sessionFactory.getCurrentSession().delete(getUserById(id));
	}

	public User getUserById(int id) {
		// With jbbc template
//		String sql = "SELECT * FROM USER WHERE ID = ?";
//		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<User>(){
//
//			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//				User user = new User();
//				user.setId(rs.getInt("ID"));
//				user.setName(rs.getString("TEN_KH"));
//				user.setPhone(rs.getString("SO_DT"));
//				return user;
//			}
//			
//		});
		
		// With Hibernate
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		// With jbbc template
//		String sql = "SELECT * FROM USER";
//		return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<User>(){
//
//			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//				User user = new User();
//				user.setId(rs.getInt("ID"));
//				user.setName(rs.getString("TEN_KH"));
//				user.setPhone(rs.getString("SO_DT"));
//				return user;
//			}
//			
//		});
		
		// With Hibernate
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}

}
