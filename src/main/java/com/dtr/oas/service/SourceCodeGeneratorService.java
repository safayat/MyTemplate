package com.dtr.oas.service;

import com.dtr.oas.bean.CodeAutoGeneratorPropertyBean;
import com.dtr.oas.module.ModuleInfo;
import com.dtr.oas.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

@Service
public class SourceCodeGeneratorService {
	@Autowired
	FileManager fileManager;

	@Autowired
	CodeAutoGeneratorPropertyBean propertyBean;

	public String getHtmlForm(ModuleInfo moduleInfo){
		Map propertyMap = moduleInfo.getPropertyMap();
		String formDataString = fileManager.readFile(propertyBean.getFormHtmlReadFilePath());
		StringBuilder formBuilder = new StringBuilder();
		Iterator<Map.Entry<String,String>> iterator = propertyMap.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry<String,String> entry = iterator.next();
			String fieldName = entry.getKey();
			formBuilder.append(formDataString.replaceAll("#ca_form_field#", StringUtils.capitalize(fieldName))
							.replaceAll("#module_name#", moduleInfo.getName())
							.replaceAll("#form_field#",fieldName)
			);
			formBuilder.append("\n");
		}
		return formBuilder.toString();

	}

	public String geHtmlTableHeaders(ModuleInfo moduleInfo){
		Map propertyMap = moduleInfo.getPropertyMap();
		StringBuilder headerBuilder = new StringBuilder();
		Iterator<String> iterator = propertyMap.keySet().iterator();
		while (iterator.hasNext()){
			String key = iterator.next();
			headerBuilder.append("<th>");
			headerBuilder.append(StringUtils.capitalize(key));
			headerBuilder.append("</th>\n");
		}
		return headerBuilder.toString();

	}
	public String getHtmlTableBody(ModuleInfo moduleInfo){
		Map propertyMap = moduleInfo.getPropertyMap();
		StringBuilder tableDataBuilder = new StringBuilder("<tr ng-repeat = \"" + moduleInfo.getName()  + " in " + moduleInfo.getName() +"List\">\n");
		Iterator<Map.Entry<String,String>> iterator = propertyMap.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry<String,String> entry = iterator.next();
			tableDataBuilder.append("<td>");
			tableDataBuilder.append("{{" + moduleInfo.getName() + "." + entry.getKey() + "}}");
			tableDataBuilder.append("</td>\n");
		}
		tableDataBuilder.append("</tr>\n");
		return tableDataBuilder.toString();

	}
	public String getAddPage(ModuleInfo moduleInfo){
		String readFile = fileManager.readFile(propertyBean.getAddHtmlReadFilePath());
		readFile = readFile.replaceAll("#formData#", getHtmlForm(moduleInfo));
		return readFile.replaceAll("#ca_module_name#", moduleInfo.getCapilalName());

	}
	public String getAddJsController(ModuleInfo moduleInfo){
		return convertToValidCode(fileManager.readFile(propertyBean.getAddJsReadFilePath()),moduleInfo);
	}
	public String getViewJsController(ModuleInfo moduleInfo){
		return convertToValidCode(fileManager.readFile(propertyBean.getListJsReadFilePath()),moduleInfo);
	}
	public String getListPage(ModuleInfo moduleInfo){
		String readFile = fileManager.readFile(propertyBean.getListHtmlReadFilePath());
		readFile = readFile.replaceAll("#module_headers#", geHtmlTableHeaders(moduleInfo));
		readFile = readFile.replaceAll("#module_list#", getHtmlTableBody(moduleInfo));
		return readFile.replaceAll("#ca_module_name#", moduleInfo.getCapilalName()).replaceAll("#module_name#",moduleInfo.getName());
	}

	public String getMVCController(ModuleInfo moduleInfo){
		String readFile = fileManager.readFile(propertyBean.getControllerReadFilePath());
		return readFile.replaceAll("#className#",moduleInfo.getName())
				.replaceAll("#cap_className#", StringUtils.capitalize(moduleInfo.getName()));
	}
	public String getMenuEntry(ModuleInfo moduleInfo){
		return  "\t\t\t\t<li class=\"sub-menu\">\n" +
				"\t\t\t\t\t<a href=\"javascript:;\" class=\"\">\n" +
				"\t\t\t\t\t\t<i class=\"icon_table\"></i>\n" +
				"\t\t\t\t\t\t<span>" +
				moduleInfo.getCapilalName() +
				"</span>\n" +
				"\t\t\t\t\t\t<span class=\"menu-arrow arrow_carrot-right\"></span>\n" +
				"\t\t\t\t\t</a>\n" +
				"\t\t\t\t\t<ul class=\"sub\">\n" +
				"\t\t\t\t\t\t<li><a class=\"\" href=\"#/" +
				moduleInfo.getName() +
				"/add\">Add</a></li>\n" +
				"\t\t\t\t\t\t<li><a class=\"\" href=\"#/" +
				moduleInfo.getName() +
				"/list\">List</a></li>\n" +
				"\t\t\t\t\t</ul>\n" +
				"\t\t\t\t</li>\n";

	}
	public String getJsControllerScriptIncludeBlock(ModuleInfo moduleInfo){
		StringBuilder sb = new StringBuilder();
		sb.append("\t<script src=\"pages/");
		sb.append(moduleInfo.getName());
		sb.append("/" +moduleInfo.getName());
		sb.append("ListController.js\"></script>\n");
		sb.append("\t<script src=\"pages/");
		sb.append(moduleInfo.getName());
		sb.append("/" +moduleInfo.getName());
		sb.append("AddUpdateController.js\"></script>\n");
		return sb.toString();
	}
	public String getAppJsRouteBlock(ModuleInfo moduleInfo){
		StringBuilder sb = new StringBuilder();
		sb.append("\t\t\t.when('/"+moduleInfo.getName()+"/add', {\n" +
				"\t\t\t\ttemplateUrl: 'pages/"+moduleInfo.getName()+"/" + moduleInfo.getName() + "AddUpdate.html',\n" +
				"\t\t\t\tcontroller: '" + moduleInfo.getName() + "Controller'\n" +
				"\t\t\t})\n" +
				"\t\t\t.when('/" + moduleInfo.getName() +"/list', {\n" +
				"\t\t\t\ttemplateUrl: 'pages/" + moduleInfo.getName() +"/" + moduleInfo.getName() +"List.html',\n" +
				"\t\t\t\tcontroller: '" + moduleInfo.getName() + "ListController'\n" +
				"\t\t\t})\n");

		return sb.toString();
	}
	public String getUpdatedAppJs(ModuleInfo moduleInfo){
		String oldAppJs = fileManager.readFile(propertyBean.getAppJsPath());
		return oldAppJs.replaceAll("//#route_list#",  getAppJsRouteBlock(moduleInfo) + "//#route_list#\n");
	}
	public String getUpdatedIndexHtml(ModuleInfo moduleInfo){
		String oldIndexHtml = fileManager.readFile(propertyBean.getIndexHtmlPath());
		return oldIndexHtml.replaceAll("<!--#script_path#-->",  getJsControllerScriptIncludeBlock(moduleInfo) + "<!--#script_path#-->\n").replaceAll("<!--#menu_block#-->", getMenuEntry(moduleInfo) + "<!--#menu_block#-->\n");
	}
	public String getPojo(ModuleInfo moduleInfo){
		StringBuilder sb = new StringBuilder();
		Map propertyMap = moduleInfo.getPropertyMap();
		sb.append("package com.dtr.oas.model;\n");
		sb.append("import javax.persistence.*;\n");
		sb.append("import java.util.Date;\n");
		sb.append("@Entity\n");
		sb.append("@Table(name=\"" +
				moduleInfo.getTableName() +
				"\")\n");
		sb.append("public class " +
				moduleInfo.getCapilalName() +
				" extends BaseEntity{\n");

		//variables declaration
		Iterator<Map.Entry<String,String>> iterator = propertyMap.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry<String,String> entry = iterator.next();
			sb.append("\tprivate " +
					entry.getValue() +
					" " +
					entry.getKey() +
					";\n");

		}
		//variables setter getter
		iterator = propertyMap.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry<String,String> entry = iterator.next();
			sb.append("\tpublic " +
					entry.getValue() +
					" " +
					"get" +
					StringUtils.capitalize(entry.getKey()) +
					"() {\n" +
					"\t\treturn " +
					entry.getKey()  +
					";\n" +
					"\t}\n");

			sb.append("\tpublic void set" +
					StringUtils.capitalize(entry.getKey()) +
					"(" +
					entry.getValue()  +
					" " +
					entry.getKey() +
					") {\n" +
					"\t\tthis." +
					entry.getKey() +
					" = " +
					entry.getKey() +
					";\n" +
					"\t}\n");
		}
		sb.append("}\n");
		return sb.toString();
	}

	private String convertToValidCode(String text,ModuleInfo moduleInfo){
		return text.replaceAll("#ca_module_name#", moduleInfo.getCapilalName()).replaceAll("#module_name#",moduleInfo.getName());
	}
	


}
