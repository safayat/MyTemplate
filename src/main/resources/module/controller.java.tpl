package com.dtr.oas.controller;

import com.dtr.oas.model.#cap_className#;
import com.dtr.oas.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import com.dtr.oas.util.PaginationParam;
import java.util.Map;


@RestController
public class #cap_className#Controller {

	@Autowired
	CommonService commonService;

	@RequestMapping(value="/private/api/#className#", method = RequestMethod.POST)
	public ResponseEntity addUpdate#cap_className#(@RequestBody #cap_className# #className#) {

		try {
			
			commonService.saveOrUpdate(#className#);
			return new ResponseEntity<#cap_className#>(#className#,HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/private/#className#/list", method = RequestMethod.GET)
	public ResponseEntity #className#List(
			@RequestParam(value = "limit" , required = false, defaultValue = "100") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset

	) {

		PaginationParam param =  new PaginationParam(limit, offset, "id", "asc");
		Map queryMap = new HashMap<>();
		try {
			List<#cap_className#> #className#List = commonService.search(#cap_className#.class, queryMap.entrySet().iterator() ,param);
			return new ResponseEntity<List<#cap_className#>>(#className#List,HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/admin/#className#/get", method = RequestMethod.GET)
	public ResponseEntity get#cap_className#(
			@RequestParam("id") int id

	) {

		try {
			#cap_className# #className# = commonService.get(#cap_className#.class,id);
			return new ResponseEntity<#cap_className#>(#className#,HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
