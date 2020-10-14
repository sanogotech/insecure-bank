package org.hdivsamples.dao;

import java.sql.ResultSet;
import java.util.List;

import org.hdivsamples.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.PreparedStatementSetter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.Validator;
import org.owasp.esapi.codecs.MySQLCodec;

 */
 import org.apache.log4j.Logger;
 
@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final static Logger log = Logger.getLogger(AccountDaoImpl.class);

	@Override
	public List<Account> findUsersByUsernameAndPassword(final String username, final String password) {
		
		log.info("   findUsersByUsernameAndPassword  ..");

		//TODO Bug  SQL Injection
		String str = "select * from account where username='" + username + "' AND password='" + password + "'";
		
		/*String str = "select * from account where username= ? AND password= ? ";
		
		PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
            }
        };*/
		

		RowMapper<Account> rowMapper = new RowMapper<Account>() {
			@Override
			public Account mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				Account localAccount = new Account();
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setName(paramResultSet.getString("name"));
				localAccount.setSurname(paramResultSet.getString("surname"));
				localAccount.setPassword(paramResultSet.getString("password"));
				return localAccount;
			}
		};

		return jdbcTemplate.query(str, rowMapper);
		//System.out.println("Call PreparedStatement ...");
		//return jdbcTemplate.query(str,preparedStatementSetter, rowMapper);
	}
	
	//ESAPI
	//@Override
	/*
	public List<Account> findUsersByUsernameAndPasswordESAPI(final String username, final String password) {

		String sqlclient = "select * from account where username='" + username + "' AND password='" + password + "'";
		 Encoder encoder = ESAPI.encoder();
		MySQLCodec codec = new   MySQLCodec(0);
		String sqlesapi = encoder.encodeForSQL(codec, sqlclient);
	

		
		PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
            }
        };
		

		RowMapper<Account> rowMapper = new RowMapper<Account>() {
			@Override
			public Account mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				Account localAccount = new Account();
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setName(paramResultSet.getString("name"));
				localAccount.setSurname(paramResultSet.getString("surname"));
				localAccount.setPassword(paramResultSet.getString("password"));
				return localAccount;
			}
		};


		System.out.println("Call ESAPI ...");
		return jdbcTemplate.query(sqlesapi, rowMapper);
	
	}*/

	@Override
	public List<Account> findUsersByUsername(final String username) {
		String str = "select * from account where username='" + username + "'";

		RowMapper<Account> rowMapper = new RowMapper<Account>() {
			@Override
			public Account mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				Account localAccount = new Account();
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setName(paramResultSet.getString("name"));
				localAccount.setSurname(paramResultSet.getString("surname"));
				localAccount.setPassword(paramResultSet.getString("password"));
				return localAccount;
			}
		};

		return jdbcTemplate.query(str, rowMapper);
	}

	@Override
	public List<Account> findAllUsers() {

		String str = "select * from account";

		RowMapper<Account> rowMapper = new RowMapper<Account>() {
			@Override
			public Account mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				Account localAccount = new Account();
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setName(paramResultSet.getString("name"));
				localAccount.setSurname(paramResultSet.getString("surname"));
				localAccount.setPassword(paramResultSet.getString("password"));
				return localAccount;
			}
		};

		return jdbcTemplate.query(str, rowMapper);
	}

	public void setJdbcTemplate(final JdbcTemplate paramJdbcTemplate) {
		jdbcTemplate = paramJdbcTemplate;
	}
}
