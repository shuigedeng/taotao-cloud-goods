package com.taotao.cloud.goods.facade.github;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

// src/main/java/com/example/client/github/MilestoneService.java
public interface MilestoneService {
    
    @GetExchange("/repos/{org}/{repo}/milestones")
	List<Milestone> getMilestones(
        @PathVariable String org, 
        @PathVariable String repo,
        @RequestParam(required = false) String state
    );
    
    @PostExchange("/repos/{org}/{repo}/milestones")
    Milestone createMilestone(
        @PathVariable String org,
        @PathVariable String repo,
        @RequestBody CreateMilestoneRequest request
    );
}

