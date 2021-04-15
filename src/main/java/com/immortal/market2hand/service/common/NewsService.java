package com.immortal.market2hand.service.common;

import java.util.List;

import com.immortal.market2hand.bean.PageBean;
import com.immortal.market2hand.constant.SessionConstant;
import com.immortal.market2hand.dao.common.NewsDao;
import com.immortal.market2hand.entity.common.News;
import com.immortal.market2hand.entity.common.Student;
import com.immortal.market2hand.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


/**
 * 新闻公告service
 *
 * @author Administrator
 */
@Service
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private NewsService newsService;

    /**
     * 新闻信息添加/修改（id不为空则为修改）
     *
     * @param news
     * @return
     */
    public News save(News news) {
        return newsDao.save(news);
    }

    /**
     * 分页搜索新闻公告
     *
     * @param pageBean
     * @param news
     * @return
     */
    public PageBean<News> findList(PageBean<News> pageBean, News news) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("title", GenericPropertyMatchers.contains());
        exampleMatcher = exampleMatcher.withMatcher("student_id", GenericPropertyMatchers.contains());
        exampleMatcher = exampleMatcher.withIgnorePaths("sort", "viewNumber", "status");
        Example<News> example = Example.of(news, exampleMatcher);
        Sort sort = Sort.by(Direction.DESC, "sort");

        PageRequest pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
        Page<News> findAll = newsDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    public void updateNewsRead(Long id) {
        //将news中设置成已读
        newsDao.updateNewsRead(id);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public News find(Long id) {
        return newsDao.find(id);
    }

    /**
     * 新闻公告删除
     *
     * @param id
     */
    public void delete(Long id) {
        newsDao.deleteById(id);
    }

    /**
     * 获取制定数量的新闻列表
     *
     * @param size
     * @return
     */
    public List<News> findList(int size) {
        Student s = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        News news = new News();
        PageBean<News> pageBean = new PageBean<News>();
        pageBean.setPageSize(size);
        return findList(pageBean, news).getContent();
    }

    public Long findNewsCount(Long id) {
        //查询出总消息
        //查询出未读消息数
        return newsDao.findNewsCount(id);
    }
}
