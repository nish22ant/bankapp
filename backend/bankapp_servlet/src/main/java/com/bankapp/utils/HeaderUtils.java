package com.bankapp.utils;

import javax.servlet.http.HttpServletResponse;

public class HeaderUtils {
	public static void setCommonHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
