package com.newland.cms.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.cms.domain.Dict;

public interface DictService extends IService<Dict> {
	IPage<Dict> findDicts(Dict dict);
}
