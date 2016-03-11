package commonrmi;

import java.io.Serializable;

/**
 * Extend this class to implement a specific request.
 * 
 * All objects passed around using RMI need to be serializable or implement Remote.
 */
public abstract class Request implements Serializable {

	private static final long serialVersionUID = -2759792949618967698L;

}
