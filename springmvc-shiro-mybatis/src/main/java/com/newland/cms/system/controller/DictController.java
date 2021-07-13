package com.newland.cms.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.newland.cms.domain.Dict;
import com.newland.cms.system.service.DictService;

@RestController
@RequestMapping("/dict")
@CrossOrigin
public class DictController {

	private String message;

	@Autowired
	private DictService dictService;

	@GetMapping("/trim")
	public Map<String, List<Map<String, Object>>> DictTrimList(Dict dict) {
		IPage<Dict> dicts = this.dictService.findDicts(dict);
		Map<String, List<Map<String, Object>>> _map = new ConcurrentHashMap<>();
		dicts.getRecords().parallelStream().forEach(_dict -> {
			HashMap<String, Object> map = new HashMap<>();
			map.put("keyy", _dict.getKeyy());
			map.put("valuee", _dict.getValuee());
			map.put("otherKeyy", _dict.getOtherKeyy());
			String key = _dict.getTableName() + "_" + _dict.getFieldName();
			if (!_map.containsKey(key)) {
				_map.put(key, new ArrayList<>());
			}
			_map.get(key).add(map);
		});
		return _map;
	}

}
