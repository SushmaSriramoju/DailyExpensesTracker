<%@page import="com.det.dao.CustDaoImpl"%>
<%@page import="org.eclipse.jdt.internal.compiler.lookup.InvocationSite.EmptyWithAstNode"%>
<%@page import="com.det.entity.Cust"%>
<%
if(request.getMethod().equals("POST"))
{
	String userId = (String) request.getServletContext().getAttribute("uid");
	Integer Recordid = Integer.parseInt(request.getParameter("recordid"));
	String Item = request.getParameter("item"); 
	String Date = request.getParameter("date"); 
	Integer Price=Integer.parseInt(request.getParameter("price"));
	String Description = request.getParameter("description");
	
	if(Price<=0)
	{
		out.println("<html><body>");
		out.println("<script>");
		out.println("alert('price must be greater than 0 .');");
		out.println("window.history.back();"); 
		out.println("</script>");
		out.println("</body></html>");
	}				        
	else {
	
	
	
	Cust cust = new Cust();
	cust.setEmail(userId);
	cust.setItem(Item);
	cust.setDate(Date);
	cust.setPrice(Price); 
	cust.setDescription(Description);
	cust.setRecordid(Recordid);
	

	CustDaoImpl custDao = new CustDaoImpl();
	
	custDao.UpdateRecord(cust,Recordid,userId);
	response.sendRedirect("Dashboard.html");
		}
	}
%>