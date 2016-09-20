package me.jcala.blog.service;

import me.jcala.blog.domain.Project;
import me.jcala.blog.mapping.ProjectMapper;
import me.jcala.blog.service.inter.ProjectSerInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
@Service
public class ProjectSer implements ProjectSerInter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSer.class);
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public List<Project> getFivePros(int page) {
        List<Project> projectList=new ArrayList<>();
        int start=(page-1)*5;
        try {
            projectList=projectMapper.select(start);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        }
        return projectList;
    }
    @Override
    public void savePro(Project project) {
         Timestamp timestamp=new Timestamp(System.currentTimeMillis());
         project.setDate(timestamp);
        try {
            projectMapper.insert(project);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Project> adminGetPros(int page) {
        int start=(page-1)*10;
        List<Project> projectList=new ArrayList<>();
        try {
            projectList=projectMapper.adminSelect(start);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return projectList;
    }

    @Override
    public int getPageNum() {
        int count=0;
        try {
            count=projectMapper.count();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return count%10==0?count/10:count/10+1;
    }
}