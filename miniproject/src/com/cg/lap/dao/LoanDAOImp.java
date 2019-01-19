package com.cg.lap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.lap.exception.LAPException;
import com.cg.lap.model.Loan;
import com.cg.lap.model.LoanPrograms;
import com.cg.lap.utility.JdbcUtility;

public class LoanDAOImp implements LoanDAO {

	Connection connection=null;
	PreparedStatement  statement=null;
	
	@Override
	public boolean validateFields(int loginId,String password) throws LAPException {
		// TODO Auto-generated method stub
		
			List<Loan> list = new ArrayList<>();
			ResultSet resultSet = null;
			connection = JdbcUtility.getConnection();
			boolean flag = false;
			try {
				statement = connection.prepareStatement(QueryMapper.userCheck);
				statement.setInt(1, loginId);
				statement.setString(2, password);
				resultSet = statement.executeQuery();
				System.out.println(resultSet);
				while (resultSet.next()) {
					loginId = resultSet.getInt("login_id");
					Loan applicant = new Loan();
					applicant.setloginId(loginId);
					list.add(applicant);
				}
				for (Loan loan : list) {
					System.out.println(loan.getloginId());
				}
				if (list.isEmpty()) {
					flag = false;
				} else {
					flag = true;
				}
			} catch (SQLException e) {
				throw new LAPException("no data found ");
			}
			return flag;

	
	}

	@Override
	public List<LoanPrograms> viewDetails() throws LAPException {
		// TODO Auto-generated method stub
		connection=JdbcUtility.getConnection();
		
		LoanPrograms loanPrograms=new LoanPrograms();

		List<LoanPrograms> list=new ArrayList<>();	
		try {
			statement=connection.prepareStatement(QueryMapper.viewDetails);
			System.out.println("Query");
			ResultSet resultSet = statement.executeQuery();
			System.out.println("result set");
		
			while(resultSet.next())
			{
			String programName=resultSet.getString(1);
			System.out.println("Query1");
			String description=resultSet.getString(2);
			System.out.println("Query2");
			String type=resultSet.getString(3);
			int duration=resultSet.getInt(4);
			long minLoan=resultSet.getLong(5);
			long maxLoan=resultSet.getLong(6);
			int interest=resultSet.getInt(7);
			String proofs=resultSet.getString(8);
					
		    System.out.println("obj creation");
			
			loanPrograms.setProgramName(programName);
			loanPrograms.setDescription(description);
			loanPrograms.setType(type);
			loanPrograms.setDuration(duration);
			loanPrograms.setMinLoan(minLoan);
			loanPrograms.setMaxLoan(maxLoan);
			loanPrograms.setInterest(interest);
			loanPrograms.setProofs(proofs);
			}
			list.add(loanPrograms);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();

			} catch (SQLException e) {
				throw new LAPException("Statement not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new LAPException("connection not closed");
			}
		}
		
		
		return list;
	}

}
