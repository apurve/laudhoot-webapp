package laudhoot.core.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.GeoFenceTreeNode;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.repository.GeoFenceTreeRepository;
import laudhoot.core.util.validation.LaudhootExceptionUtils;
import laudhoot.core.util.validation.LaudhootValidator;
import laudhoot.web.domain.GeoFenceTreeNodeTO;
import laudhoot.web.util.ServiceRequest;

@Service
@Transactional
public class GeoFenceTreeServiceImpl implements GeoFenceTreeService {
	
	@Autowired
	private GeoFenceTreeRepository geoFenceTreeRepository;
	
	@Autowired
	private GeoFenceRepository geoFenceRepository;
	
	@Autowired
	private LaudhootValidator validator;

	@Override
	public GeoFenceTreeNode createGeoFenceNode(GeoFence geoFence) {
		GeoFenceTreeNode geoFenceTreeNode = new GeoFenceTreeNode(geoFence.getCode(), findNodeByCode(GeoFenceTreeNode.ROOT), null);
		return geoFenceTreeRepository.save(geoFenceTreeNode);
	}

	@Override
	public GeoFenceTreeNodeTO editGeoFenceNode(GeoFenceTreeNodeTO geoFenceTreeNodeTO) {
		LaudhootExceptionUtils.isNotNull(geoFenceTreeNodeTO,
				"Tree node cannot be null.");
		validator.validate(geoFenceTreeNodeTO, geoFenceTreeNodeTO.getValidation(),
				ServiceRequest.EditGeoFenceTreeNode.class);
		if (geoFenceTreeNodeTO.getValidation().hasErrors()) {
			return geoFenceTreeNodeTO;
		}
		
		// update the node
		GeoFenceTreeNode geoFenceTreeNode = findNodeByCode(geoFenceTreeNodeTO.getCode());
		if(geoFenceTreeNode == null) {
			geoFenceTreeNodeTO.getValidation().addError(new FieldError(GeoFenceTreeNodeTO.class.getCanonicalName(), "code", "This node does not exists."));
			return geoFenceTreeNodeTO;
		}
		GeoFenceTreeNode parentGeoFenceTreeNode = findNodeByCode(geoFenceTreeNodeTO.getParent());
		if(parentGeoFenceTreeNode == null) {
			geoFenceTreeNodeTO.getValidation().addError(new FieldError(GeoFenceTreeNodeTO.class.getCanonicalName(), "parent", "The parent node does not exists."));
			return geoFenceTreeNodeTO;
		}
		geoFenceTreeNode.setParent(parentGeoFenceTreeNode);
		if(geoFenceTreeNodeTO.getChildren() != null) {
			if(geoFenceTreeNode.getChildren() != null) {
				geoFenceTreeNode.setChildren(new HashSet<GeoFenceTreeNode>());
			}
			for(String child : geoFenceTreeNodeTO.getChildren()) {
				GeoFenceTreeNode childNode = findNodeByCode(child);
				parentGeoFenceTreeNode.getChildren().remove(childNode);
				childNode.setParent(geoFenceTreeNode);
				geoFenceTreeNode.getChildren().add(childNode);
			}
		}
		geoFenceTreeRepository.save(geoFenceTreeNode);
		
		// update the parent node
		if(parentGeoFenceTreeNode.getChildren() == null) {
			parentGeoFenceTreeNode.setChildren(new HashSet<GeoFenceTreeNode>());
		}
		parentGeoFenceTreeNode.getChildren().add(geoFenceTreeNode);
		geoFenceTreeRepository.save(parentGeoFenceTreeNode);
		
		return new GeoFenceTreeNodeTO(geoFenceTreeNode);
	}

	private GeoFenceTreeNode findNodeByCode(String code) {
		return geoFenceTreeRepository.findByCode(code);
	}
	
	@Override
	public List<String> getFenceableChildren(String parent) {
		List<String> children = new ArrayList<String>();
		GeoFence parentFence = geoFenceRepository.findByCode(parent);
		for(GeoFenceTreeNode child : findNodeByCode(parent).getChildren()) {
			if (parentFence.fences(geoFenceRepository.findByCode(child.getCode()))) {
				children.add(child.getCode());
			}
		}
		return children;
	}

	@Override
	public GeoFenceTreeNodeTO fetchNodeByCode(String code) {
		return new GeoFenceTreeNodeTO(findNodeByCode(code));
	}

}
