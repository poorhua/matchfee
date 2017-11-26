/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.common.supcan.treelist;

import java.util.List;

import com.google.common.collect.Lists;
import org.wxjs.matchfee.common.supcan.annotation.common.fonts.SupFont;
import org.wxjs.matchfee.common.supcan.annotation.treelist.SupTreeList;
import org.wxjs.matchfee.common.supcan.common.Common;
import org.wxjs.matchfee.common.supcan.common.fonts.Font;
import org.wxjs.matchfee.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正TreeList
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("TreeList")
public class TreeList extends Common {

	/**
	 * 列集合
	 */
	@XStreamAlias("Cols")
	private List<Object> cols;

	public TreeList() {
		super();
	}
	
	public TreeList(Properties properties) {
		this();
		this.properties = properties;
	}
	
	public TreeList(SupTreeList supTreeList) {
		this();
		if (supTreeList != null){
			if (supTreeList.properties() != null){
				this.properties = new Properties(supTreeList.properties());
			}
			if (supTreeList.fonts() != null){
				for (SupFont supFont : supTreeList.fonts()){
					if (this.fonts == null){
						this.fonts = Lists.newArrayList();
					}
					this.fonts.add(new Font(supFont));
				}
			}
		}
	}
	
	public List<Object> getCols() {
		if (cols == null){
			cols = Lists.newArrayList();
		}
		return cols;
	}

	public void setCols(List<Object> cols) {
		this.cols = cols;
	}

}
