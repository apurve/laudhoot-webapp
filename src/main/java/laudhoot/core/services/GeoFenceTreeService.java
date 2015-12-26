package laudhoot.core.services;

import java.util.List;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.GeoFenceTreeNode;
import laudhoot.web.domain.GeoFenceTreeNodeTO;

public interface GeoFenceTreeService {
	
	/**
	 * Create a {@link GeoFenceTreeNode}  and link to root node of geoFence tree
	 * 
	 * @return the linked node
	 * */
	public GeoFenceTreeNode createGeoFenceNode(GeoFence geoFence);
	
	/**
	 * Add a node to the tree.
	 * */
	public GeoFenceTreeNodeTO editGeoFenceNode(GeoFenceTreeNodeTO geoFenceTreeNodeTO);

	/**
	 * Get codes of all the fenceable children of a {@link GeoFenceTreeNode}.
	 * */
	public List<String> getFenceableChildren(String parent);

	/**
	 * Find a {@link GeoFenceTreeNode} by code.
	 * */
	public GeoFenceTreeNodeTO fetchNodeByCode(String code);

}
