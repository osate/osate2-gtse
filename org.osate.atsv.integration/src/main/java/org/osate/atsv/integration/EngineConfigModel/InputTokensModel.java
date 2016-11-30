package org.osate.atsv.integration.EngineConfigModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class InputTokensModel {

	@XmlJavaTypeAdapter(InputTokenAdapter.class)
	@XmlAnyElement
	private List<InputTokenModel> inputTokens = new ArrayList<>(); 
	
	public void addTokenModel(InputTokenModel itm){
		inputTokens.add(itm);
	}

	public void clear() {
		inputTokens.clear();
	}
}
