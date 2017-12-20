package LDAPlearn;
import javax.naming.directory.BasicAttribute;
import java.util.Hashtable;
import javax.naming.directory.BasicAttributes;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

public class Test1 {
	
	public static void main (String[] args)
	{
		try
		{
			Hashtable<String, String> ldapEnv = new Hashtable<>();
			ldapEnv.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			ldapEnv.put(Context.PROVIDER_URL, "ldap://localhost:10389");
			ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			ldapEnv.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
			ldapEnv.put(Context.SECURITY_CREDENTIALS, "secret");
			System.out.println("success");
			DirContext context = new InitialDirContext(ldapEnv);
			
			Attributes attributes = new BasicAttributes();
			Attribute attribute = new BasicAttribute("objectClass");
			attribute.add("inetOrgPerson");
			attributes.put(attribute);
			Attribute sn = new BasicAttribute("sn");
			sn.add("Kim");
			Attribute cn = new BasicAttribute("cn");
			cn.add("Jaehwan Kim");
			
			attributes.put(sn);
			attributes.put(cn);
			context.createSubcontext("employeeNumber=1,ou=users,ou=system",attributes);
			System.out.println("success");
		}
		catch (Exception e) {
		}
	}
}