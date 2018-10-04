package ph.inv.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ph.inv.entity.SystemCodes;
import ph.inv.service.SystemCodesService;

@Component("systemCodesEditor")
public class SystemCodesEditor extends PropertyEditorSupport {

	@Autowired
	private SystemCodesService systemCodesService;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.hasText(text)) {
			
			SystemCodes systemCode = systemCodesService.find(Long.parseLong(text));
			systemCode.setId(Long.parseLong(text));
			setValue(systemCode);
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		SystemCodes systemCode = (SystemCodes) getValue();
		if(systemCode != null) {
			return systemCode.getId()+"";
		} else {
			return "";
		}
	}
}
