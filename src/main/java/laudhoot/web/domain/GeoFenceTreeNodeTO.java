package laudhoot.web.domain;

import java.util.HashSet;
import java.util.Set;

import laudhoot.core.domain.GeoFenceTreeNode;
import laudhoot.web.util.ServiceRequest;

import org.hibernate.validator.constraints.NotEmpty;

public class GeoFenceTreeNodeTO extends BaseTO {

	private static final long serialVersionUID = 7059718352352273304L;
	
	@NotEmpty(groups={ServiceRequest.EditGeoFenceTreeNode.class})
	private String code;
	
	@NotEmpty(groups={ServiceRequest.EditGeoFenceTreeNode.class})
	private String parent;
	
	private Set<String> children;
	
	public GeoFenceTreeNodeTO() {
		super();
	}
	
	public GeoFenceTreeNodeTO(String code) {
		super();
		this.code = code;
	}
	
	public GeoFenceTreeNodeTO(GeoFenceTreeNode geoFenceTreeNode) {
		super(geoFenceTreeNode.getId(), geoFenceTreeNode.getCreatedBy(), null);
		this.code = geoFenceTreeNode.getCode();
		this.parent = geoFenceTreeNode.getParent().getCode();
		this.children = new HashSet<String>();
		for(GeoFenceTreeNode node : geoFenceTreeNode.getChildren()) {
			children.add(node.getCode());
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Set<String> getChildren() {
		return children;
	}

	public void setChildren(Set<String> children) {
		this.children = children;
	}
	
}
