/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context.http;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import za.co.sindi.oauth.server.context.SessionContext;

/**
 * @author Buhake Sindi
 * @since 17 April 2012
 *
 */
public class HttpSessionContext implements SessionContext {

	private HttpSession session;
	
	/**
	 * @param session
	 */
	public HttpSessionContext(HttpSession session) {
		super();
		if (session == null) {
			throw new IllegalArgumentException("HTTP Session may not be null.");
		}
		
		this.session = session;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return getSession().getAttribute(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#getAttributeNames()
	 */
	@Override
	public String[] getAttributeNames() {
		// TODO Auto-generated method stub
		Enumeration<String> enums = getSession().getAttributeNames();
		if (enums == null) {
			return null;
		}
		
		List<String> names = new ArrayList<String>();
		while (enums.hasMoreElements()) {
			names.add(enums.nextElement());
		}
		
		return names.toArray(new String[names.size()]);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#removeAttribute(java.lang.String)
	 */
	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub
		getSession().removeAttribute(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object object) {
		// TODO Auto-generated method stub
		getSession().setAttribute(name, object);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.SessionContext#getId()
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return getSession().getId();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.SessionContext#getCreationTime()
	 */
	@Override
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return getSession().getCreationTime();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.SessionContext#getLastAccessedTime()
	 */
	@Override
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return getSession().getLastAccessedTime();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.SessionContext#isNew()
	 */
	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return getSession().isNew();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.SessionContext#invalidate()
	 */
	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		getSession().invalidate();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.SessionContext#getMaxInactiveInterval()
	 */
	@Override
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return getSession().getMaxInactiveInterval();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.SessionContext#setMaxInactiveInterval(int)
	 */
	@Override
	public void setMaxInactiveInterval(int interval) {
		// TODO Auto-generated method stub
		getSession().setMaxInactiveInterval(interval);
	}
}
