<%@page import="com.det.dao.CustDaoImpl"%>

<% 
if(request.getMethod().equals("GET"))
{
Integer Recordid = Integer.parseInt(request.getParameter("recordid"));
String userId = (String) request.getServletContext().getAttribute("uid");
CustDaoImpl custDao = new CustDaoImpl(); 

custDao.deleteRecord(Recordid,userId);

response.sendRedirect("Manageexpenses");
}
%>