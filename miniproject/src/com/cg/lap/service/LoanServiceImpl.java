package com.cg.lap.service;

import java.util.List;

import com.cg.lap.dao.LoanDAO;
import com.cg.lap.dao.LoanDAOImp;
import com.cg.lap.exception.LAPException;
import com.cg.lap.model.Loan;
import com.cg.lap.model.LoanPrograms;

public class LoanServiceImpl implements LoanService {

	LoanDAO loanDao=new LoanDAOImp();
	@Override
	public boolean validateFields(int loginId, String password) throws LAPException {
		// TODO Auto-generated method stub
		return loanDao.validateFields(loginId,password);
	}
	@Override
	public List<LoanPrograms> viewDetails() throws LAPException {
		// TODO Auto-generated method stub
		return loanDao.viewDetails();
	}
	
}
