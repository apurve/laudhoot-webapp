package laudhoot.core.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * A node of the tree formed by {@link GeoFence}(s).
 * 
 * @author apurve
 * */

@Entity(name="geofence_tree")
public class GeoFenceTreeNode extends BaseDomain {
	
	public static final String ROOT = "root";

	//unique code referencing to a geofence, cannot be changed once created
	@Column(updatable = false, unique = true)
	private String code;
	
	@OneToMany
	private Set<GeoFenceTreeNode> children;
	
	public GeoFenceTreeNode() {
		
	}
	
	public GeoFenceTreeNode(String code, Set<GeoFenceTreeNode> children) {
		super();
		this.code = code;
		this.children = children;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<GeoFenceTreeNode> getChildren() {
		return children;
	}

	public void setChildren(Set<GeoFenceTreeNode> children) {
		this.children = children;
	}
	
}
