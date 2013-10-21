package br.com.ejm.examples.helpdesk.view.managedbean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.ejm.examples.helpdesk.business.HelpDeskManager;

@ManagedBean(name = "managerLocatorMb")
@ApplicationScoped
public class HelpDeskManagerLocatorBean {

	private static final long serialVersionUID = 1L;

	private HelpDeskManager manager;

	public HelpDeskManagerLocatorBean() {
		manager = new HelpDeskManager();
	}

	public HelpDeskManager getManager() {
		return manager;
	}
}
