package com.newland.es.springdata.dao;

import com.newland.es.springdata.bean.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}
