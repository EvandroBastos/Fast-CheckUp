package br.com.ejm.examples.helpdesk.view.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "helpDesk")
@SessionScoped
public class _HelpDeskBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@ManagedProperty(value = "#{managerLocatorMb}")
//	private HelpDeskManagerLocatorBean managerLocator;
//
//	private HelpDeskManager manager;
//
//	public void setManagerLocator(HelpDeskManagerLocatorBean managerLocator) {
//		this.managerLocator = managerLocator;
//	}

	// public boolean isAttendantAvailable() {
	//
	// return getManager().hasAvailableAttendant();
	// }

//	public void poll() {
//		System.out.println("Poll");
//		boolean result = (Math.random() * 100) < 80;
//		System.out.println("isAttendantAvailable:" + result);
//	}
//
//	private HelpDeskManager getManager() {
//
//		if (manager == null && managerLocator != null) {
//			manager = managerLocator.getManager();
//		}
//		return manager;
//	}

}
