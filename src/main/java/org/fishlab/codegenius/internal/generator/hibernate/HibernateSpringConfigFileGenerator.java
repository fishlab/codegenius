package org.fishlab.codegenius.internal.generator.hibernate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.TemplateBasedGenerator;
import org.fishlab.codegenius.util.prefs.Preference;
import org.fishlab.codegenius.util.prefs.PreferenceManager;

public class HibernateSpringConfigFileGenerator extends TemplateBasedGenerator{
	private static String outputName ="orm-hibernate4.xml";
	
	@Override
	protected String getOutputFileName(GenerationContext context) {
		Project project= context.getProject();
		return project.getConfigFolder()+"/spring/"+outputName;
	}

	@Override
	protected String getTemplateName() {
		return "orm-hibernate4.xml.vm";
	}

	@Override
	protected void setTemplateProperties(GenerationContext context,
			Map<String, Object> model) {
		InputStream in=this.getClass().getResourceAsStream("prefs/dialectMappings.prefs");
		try {
			Preference prefs=PreferenceManager.loadPreference(in);
			String dalect=prefs.getString(context.getProject().getDataSource().getName());
			model.put("dialect", dalect);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
