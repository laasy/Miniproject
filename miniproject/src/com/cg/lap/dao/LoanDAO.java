package com.cg.lap.dao;

import java.util.List;

import com.cg.lap.exception.LAPException;
import com.cg.lap.model.Loan;
import com.cg.lap.model.LoanPrograms;

public interface LoanDAO {

	boolean validateFields(int loginId,String password) throws LAPException;

	List<LoanPrograms> viewDetails() throws LAPException;

}
