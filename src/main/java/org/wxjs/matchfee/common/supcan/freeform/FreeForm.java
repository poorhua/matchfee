/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.common.supcan.freeform;

import org.wxjs.matchfee.common.supcan.common.Common;
import org.wxjs.matchfee.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正FreeForm
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
