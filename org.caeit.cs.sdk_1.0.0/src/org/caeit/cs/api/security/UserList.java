package org.caeit.cs.api.security;

import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;

public class UserList extends CoreServiceAPI implements IUserList {

	public UserList(CSApiContainer container) {
		super(container);
	}

}
