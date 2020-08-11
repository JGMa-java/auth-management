package com.jgma.code.utils;

public class RetCode {
	public static final int RET_OK = 0;
	public static final int RET_ERROR = 1;
	
	public static final int RET_MISSING_SESSION = 1001;
	public static final int RET_TOKEN_NOT_EXISTS = 1002;
	public static final int RET_TOKEN_DISMATCH = 1003;
	public static final int RET_TOKEN_EXPIRED = 1004;
	public static final int RET_INVALID_TOKEN = 1005;
	public static final int RET_KICK_OFF = 1006;
	
	public static final int RET_SEND_SMS_FAILED = 1101;
	public static final int RET_VERIFY_CODE_DISMATCH = 1102;
	public static final int RET_USER_NOT_EXISTS = 1103;
	public static final int RET_PASSWORD_DISMATCH = 1104;
	public static final int RET_LOGIN_FAILED_EXCEED = 1105;
	public static final int RET_MISSING_VERIFYCODE = 1106;
	public static final int RET_USER_EXISTS = 1107;
	public static final int RET_VERIFY_CODE_EXPIRED = 1108;
	public static final int ACCOUNT_PASSWORD_ERROR = 1109;
	public static final int RET_ACCOUNT_LOCKED = 1110;
	public static final int RET_JC_LOGIN_ALREADY = 1111;
	
	public static final int RET_UPDATE_FAILED = 1201;
	public static final int RET_UPLOAD_FAILED = 1202;
	public static final int RET_FILE_NOT_EXISTS = 1203;
	public static final int RET_FILE_NOT_SUPPORT = 1204;
	
	public static final int RET_QUERY_FAILED = 1301;
	
	public static final int RET_INVALID_REQUEST_FORMAT = 2001;
	public static final int RET_MISSING_PARAM = 2002;
	public static final int RET_INVALID_URL = 2003;
	public static final int RET_USER_NOT_LOGIN = 2005;
	public static final int RET_SERVER_BUSY = 2007;
	public static final int RET_SERVER_ERROR = 2008;
	public static final int RET_SERVICE_NOT_FOUND = 2009;
	public static final int RET_FORWARD_TIMEOUT = 2010;
	public static final int RET_INVALID_PARAM = 2013;
	public static final int RET_DUPLICATE_RECORD = 2014;
	public static final int RET_RECORD_NOT_EXISTS = 2015;
	public static final int RET_NO_PERMISSION = 2016;
	public static final int RET_STATE_NOT_CORRECT = 2017;
	public static final int RET_DEPRECATED = 2018;
	public static final int RET_USERNAME_EXISTS = 2019;
	
	//1301~1305  2004 2006 2011 2012
	
	public static final int RET_ID_DISMATCH = 2020;
	public static final int RET_SMS_EXCEED = 2021;
	public static final int RET_STOCK_COUNT_EXCEED = 2022;
	public static final int RET_PAY_FAIL = 2023;
	
	public static final int RET_USER_ACCOUNT_EXISTS = 2030;
	public static final int RET_ACCOUNT_BIND_ALREADY = 2031;
	public static final int RET_NICKNAME_EXISTS = 2032;
	
	public static final int RET_POINT_NOT_ENOUGH = 2100;
	public static final int RET_MONEY_NOT_ENOUGH = 2101;
	public static final int RET_WALLET_NOT_EXISTS = 2102;
	public static final int RET_WALLET_INCREATE_MONEY_FAILED = 2103;
	
	//alarm specify error code.
	public static final int SUBSCRIBE_PARAMETER_ERROR = 3001;
	public static final int INVALID_SUBSCRIBE_TYPE = 3002;
	public static final int DATA_NOT_EXIST = 3003;
	public static final int SUBSCRIBE_EXIST=3004;
	
	//trade web specify error code.
	public static final int STOCK_NOT_EXIST = 5001;
	public static final int STOCK_DUPLICATE = 5002;
	public static final int BANNER_NOT_EXIST = 5003;
	public static final int BUSINESS_NOT_EXIST = 5004;
	
	//message center specify error code.
	public static final int MMC_NO_PUSH_ALL = 5101;
	public static final int MMC_BLACKLIST = 5102;
	public static final int MMC_NO_LEGAL_TIME=5103;
	public static final int MESSAGE_SENDED=5104;

	//组件
	public static final int RET_COMPONENT_NOT_EXIST = 6001;//组件不存在
	public static final int RET_SUB_COMPONENT_EXISTS = 6002;//子组件存在
	public static final int RET_COMPONENT_NOT_PUTAWAY = 6003;//组件未上架
	public static final int RET_COMPONENT_NOT_SOLD_OUT = 6004;//组件未下架
	//已预约
	public static final int RET_ALREADY_RESERVE=7001;
		
	public static String toString(int retCode) {
		return new String("{\"retCode\":" + retCode + "}");
	}
	
}
