package com.cg.lap.dao;

public interface QueryMapper {
	
	String userCheck="select login_id from users where login_id=? and password=?";
	String viewDetails = "select * from loanprogramsoffered";

}
