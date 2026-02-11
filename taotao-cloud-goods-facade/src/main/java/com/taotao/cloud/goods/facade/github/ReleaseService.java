package com.taotao.cloud.goods.facade.github;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

// src/main/java/com/example/client/github/ReleaseService.java
public interface ReleaseService {
    
    @GetExchange("/repos/{owner}/{repo}/releases")
	List<Release> listReleases(
        @PathVariable String owner,
        @PathVariable String repo
    );
    
    @GetExchange("/repos/{owner}/{repo}/releases/{releaseId}")
    Release getRelease(
        @PathVariable String owner,
        @PathVariable String repo,
        @PathVariable Long releaseId
    );
    
    @PostExchange("/repos/{owner}/{repo}/releases")
    Release createRelease(
        @PathVariable String owner,
        @PathVariable String repo,
        @RequestBody CreateReleaseRequest request
    );
}
