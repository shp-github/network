package com.shp.dev.network.controller;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("elasticsearch")
public class ElasticsearchControlle {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @CrossOrigin
    @PostMapping("select")
    public SearchResponse esSearch(String Fname) throws IOException {
        SearchRequest searchRequest = new SearchRequest("new");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //多条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder name = QueryBuilders.matchQuery("user",Fname);
        boolQueryBuilder.must(name);
        searchSourceBuilder.query(boolQueryBuilder);
//分页
//        searchSourceBuilder.size(size);
//        searchSourceBuilder.from(form);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return search;
    }

    @CrossOrigin
    @PostMapping("delete")
    public DeleteResponse delete(String id) throws IOException {
        DeleteRequest searchRequest = new DeleteRequest("new",id);
        DeleteResponse delete = restHighLevelClient.delete(searchRequest, RequestOptions.DEFAULT);
        return delete;
    }

    @CrossOrigin
    @PostMapping("insert")
    public IndexResponse insert(String id) throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "zhangdada");
        jsonMap.put("Date", new Date());
        jsonMap.put("message", "Elasticsearch");
        jsonMap.put("age",18);
        IndexRequest indexRequest = new IndexRequest("new")
                .id(id).source(jsonMap);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse;
    }

    @CrossOrigin
    @PostMapping("update")
    public UpdateResponse update(String id) throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "zhangdada");
        jsonMap.put("Date", new Date());
        jsonMap.put("message", "Elasticsearch");
        jsonMap.put("age",19);
        UpdateRequest updateRequest = new UpdateRequest("new",id).doc(jsonMap);
        UpdateResponse updateResponse =restHighLevelClient.update(updateRequest,RequestOptions.DEFAULT);
        return updateResponse;
    }



}
