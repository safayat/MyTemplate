package com.dtr.oas.controller;

import com.dtr.oas.bean.CodeAutoGeneratorPropertyBean;
import com.dtr.oas.module.ModuleInfo;
import com.dtr.oas.service.SourceCodeGeneratorService;
import com.dtr.oas.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ModuleController {
	@Autowired
	FileManager fileManager;

	@Autowired
	CodeAutoGeneratorPropertyBean propertyBean;
	
	@Autowired
	SourceCodeGeneratorService service;

	@RequestMapping(value="/module/create", method = RequestMethod.POST)
	public ResponseEntity moduleCreate(@RequestBody ModuleInfo moduleInfo) {

		createModel(moduleInfo);
		createController(moduleInfo);
		createFrontEndView(moduleInfo);
		return new ResponseEntity("OK", HttpStatus.OK);
	}

	private void createModel(ModuleInfo moduleInfo){
		fileManager.writeFile(propertyBean.getModelWriteFilePath(moduleInfo), service.getPojo(moduleInfo));
	}

	private void createController(ModuleInfo moduleInfo){
		fileManager.writeFile(propertyBean.getControllerWriteFilePath(moduleInfo),service.getMVCController(moduleInfo));
	}

	private void createFrontEndView(ModuleInfo moduleInfo){
		fileManager.mkdir(propertyBean.getPageDirectory(moduleInfo));
		createAddHtml(moduleInfo);
		createViewHtml(moduleInfo);
		createAddJs(moduleInfo);
		createViewJs(moduleInfo);
		updateIndexHtml(moduleInfo);
		updateAppJs(moduleInfo);
	}

	private void createAddHtml(ModuleInfo moduleInfo){
		fileManager.writeFile(propertyBean.getAddHtmlWriteFilePath(moduleInfo),service.getAddPage(moduleInfo));
	}

	private void createViewHtml(ModuleInfo moduleInfo){
		fileManager.writeFile(propertyBean.getViewListHtmlWriteFilePath(moduleInfo),service.getListPage(moduleInfo));
	}
	private void createAddJs(ModuleInfo moduleInfo){
		fileManager.writeFile(propertyBean.getAddJsWriteFilePath(moduleInfo), service.getAddJsController(moduleInfo));
	}
	private void createViewJs(ModuleInfo moduleInfo){
		fileManager.writeFile(propertyBean.getViewListJsWriteFilePath(moduleInfo),service.getViewJsController(moduleInfo));
	}
	private void updateIndexHtml(ModuleInfo moduleInfo){
		fileManager.modifyFile(propertyBean.getIndexHtmlPath(), propertyBean.getIndexHtmlPath() + ".tmp", service.getUpdatedIndexHtml(moduleInfo));
	}
	private void updateAppJs(ModuleInfo moduleInfo){
		fileManager.modifyFile(propertyBean.getAppJsPath(), propertyBean.getAppJsPath() + "tmp", service.getUpdatedAppJs(moduleInfo));
	}


}
