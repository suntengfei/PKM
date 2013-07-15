package cn.edu.cust.common.util;

public class HqlSubUtil {
	
	public static String subHql(String hql, String prefix) {
		StringBuffer sb = new StringBuffer(100);

		int whereIndex = hql.indexOf("where");
		
		if(whereIndex == -1) {
			return sb.toString();
		} else {
			hql = hql.substring(whereIndex + 5);
			
			hql = hql.replace("(", "");
			hql = hql.replace(")", "");
			
			String[] andClauses = hql.split("and");
			
			
			for(int i=0; i<andClauses.length; i++) {
				sb.append(prefix).append('.').append(andClauses[i].trim()).append(" and ");
			}
			
		}
		
		return sb.toString();
	}
	
	public static void main(String args[]) {
		String hql = "from Scheme where ( name like ?  )  and sex = ?";
		System.out.println(subHql(hql, "s"));
	}
}
  