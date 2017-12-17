package Demo;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;


public class UserList {
	public <main> void totalUser() throws NamingException
	{
		Properties initialProperties = new Properties();
		initialProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		initialProperties.put(Context.PROVIDER_URL,"ldap://localhost:10389");
		initialProperties.put(Context.SECURITY_PRINCIPAL,"uid=admin,ou=system");
		initialProperties.put(Context.SECURITY_CREDENTIALS,"secret");
		DirContext context = new InitialDirContext(initialProperties);
		String searchFilter="(ObjectClass=inetOrgPerson)";
		String[] requiredAttr= {"sn","cn","employeeNumber"};
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String[] requiredAttributes;
		controls.setReturningAttributes(requiredAttributes);
		NamingEnumeration<SearchResult> users=context.search("ou=users,o=Company", searchFilter, controls);
		SearchResult searchResult=null;
		String commonName=null;
		String surName=null;
		String employeeNumber=null;
		while(users.hasMore())
		{
			searchResult= (SearchResult) users.next();
			Attributes attr=searchResult.getAttributes();
			
			commonName=attr.get("cn").get(0).toString();
			surName=attr.get("sn").get(0).toString();
			employeeNumber=attr.get("employeeNumber").get(0).toString();
			System.out.println("Name ="+commonName);
			System.out.println("SurName ="+surName);
			System.out.println("Employee Number ="+employeeNumber);
		}
		public static main (String[], args)
		{
			UserList sample = new UserList();
			sample.totalUser();
			
		}
		
	}
}